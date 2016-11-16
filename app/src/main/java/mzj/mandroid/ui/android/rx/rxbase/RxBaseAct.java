package mzj.mandroid.ui.android.rx.rxbase;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActRxBaseBinding;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * creat by mzj on 2016/9/20 11:07
 * 优秀文章：http://gank.io/post/560e15be2dca930e00da1083#toc_1 （《给 Android 开发者的 RxJava 详解》   作者：扔物线）
 * http://blog.csdn.net/shangmingchao/article/details/51125554   Glide用法
 * Rx基础知识介绍以及示例代码
 * 问题一：RxJava是什么？
 *          RxJava:一个实现异步操作的库
 * 问题2：为什么使用RxJava？
 *          能使代码更加简洁，复杂的逻辑，清晰的代码
 * 问题3：RxJava是通过什么实现的？
 *           被扩展的观察者模式
 *
 */

public class RxBaseAct extends BaseActivity<ActRxBaseBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.act_rx_base;
    }

    @Override
    protected void initData() {
        binding.basesBt.setOnClickListener(this);
        binding.unFullBt.setOnClickListener(this);
        binding.schedulerBt.setOnClickListener(this);
        binding.mapBt.setOnClickListener(this);
        binding.flatmapBt.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.bases_bt:
                    baseAction();
                    break;
                case R.id.unFull_bt:
                    unFullAction();
                    break;
                case R.id.scheduler_bt:
                    schedulerAction();
                    break;
                case R.id.map_bt:
                    mapAction();
                    break;
                case R.id.flatmap_bt:
                    flatMapAction();
                    break;
            }
    }
    /**
     * RxJava最基本的实现
     * create() 方法是 RxJava 最基本的创造事件序列的方法。
     */
    private void baseAction(){
        // 被观察者
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onCompleted();
            }
        });



        //(观察者)Observer
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.d("TAG", "Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.i("TAG", "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "Error!");
            }
        };


        //(观察者)Observer 的抽象类：Subscriber。 Subscriber 对 Observer 接口进行了一些扩展，但他们的基本使用方式是完全一样的：
        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                Log.v("TAG", "onStart");
                super.onStart();
            }
            @Override
            public void onNext(String s) {
                Log.d("TAG", "" + s);
            }

            @Override
            public void onCompleted() {
                Log.i("TAG", "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "Error!");
            }

        };

        observable.subscribe(subscriber);
    }


    /**
     * 不完整定义的回调
     */
    private void unFullAction(){
        // 被观察者
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onCompleted();
            }
        });
        //数字为0就表示0个带0个参数
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                Log.d("TAG", "completed");
            }
        };


        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {
                Log.d("TAG", s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };


// 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
// 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
// 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }

    /**
     * 线程控制
     * Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
     * Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
     * Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，
     *                  区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。
     *                  不要把计算工作放在 io() 中，可以避免创建不必要的线程。
     * Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。
     *                          这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
     * 另外， Android 还有一个专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
     *
     * 有了这几个 Scheduler ，就可以使用 subscribeOn() 和 observeOn() 两个方法来对线程进行控制了。
     * * subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
     * * observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
     */
    private void schedulerAction() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onNext(3);
                subscriber.onNext(4);
                Log.i("TAG", "Observable运行的线程：" + Thread.currentThread().getName());

            }
        }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        Log.i("TAG", "Subscriber运行的线程：" + Thread.currentThread().getName());
                        Log.d("TAG", "number:" + number);
                    }
                });


    }


    /**
     * 变换：就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列。
     *      (也就是将事件的参数类型转换，如下例：String-->Bitmap)
     * Func 和 Action 的区别在于， Func 包装的是有返回值的方法。
     * map(): 事件对象的直接变换。它是 RxJava 最常用的变换。
     */
    private void mapAction(){
        Observable.just("http://img1.imgtn.bdimg.com/it/u=2507713363,3849382690&fm=21&gp=0.jpg") // 输入类型 String
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String filePath) { // 参数类型 String

                        Log.i("TAG", "map运行的线程："+Thread.currentThread().getName());
                        try {
                        return  Glide.with(mContext)
                                .load(filePath)
                                .asBitmap()
                                .centerCrop()
                                .into(500, 500)
                                .get(); // 返回类型 Bitmap
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) { // 参数类型 Bitmap
                        Log.e("TAG","显示图片"+bitmap);
                        binding.iv.setImageBitmap(bitmap);
                    }
                });

    }


    /**
     * flatMap() 的原理是这样的：
     * 1. 使用传入的事件对象创建一个 Observable 对象；
     * 2. 并不发送这个 Observable, 而是将它激活，于是它开始发送事件；
     * 3. 每一个创建出来的 Observable 发送的事件，都被汇入同一个 Observable ，而这个 Observable 负责将这些事件统一交给 Subscriber 的回调方法。
     *      这三个步骤，把事件拆成了两级，通过一组新创建的 Observable 将初始的对象『铺平』之后通过统一路径分发了下去。而这个『铺平』就是 flatMap() 所谓的 flat。
     */
    private void flatMapAction() {
        //        学生1
        List<String> course1 = new ArrayList<>();
        course1.add("语文");
        course1.add("数学");
        Student student1 = new Student(course1);
        //        学生2
        List<String> course2 = new ArrayList<>();
        course2.add("音乐");
        course2.add("体育");
        Student student2 = new Student(course2);
        //        学生3
        List<String> course3 = new ArrayList<>();
        course3.add("物理");
        course3.add("化学");
        Student student3 = new Student(course3);


        Student[] students = {student1, student2, student3};
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<String>>() {
                    @Override
                    public Observable<String> call(Student student) {

                        return Observable.from(student.getCourse());
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("TAG", s);
                    }
                });

    }

}
