package mzj.mandroid.ui.java.thread;

import android.util.Log;
import android.view.View;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActCreatThreadBinding;

/**
 * creat by mzj on 2016/9/28 22:01
 * 线程创建的3种方式
 *
 * 实现Runnable接口的
 * 优势：
 * 1：多个线程可以共享一个target，非常适合多个相同的线程来处理同一份资源的情况，从而将CPU，代码和数据分开，形成清晰额模型
 * 2：可继承其它类
 * 劣势：
 * 1：访问当前线程必须使用Thread.currentThread()
 *
 */

public class CreatThreadAct extends BaseActivity<ActCreatThreadBinding> implements View.OnClickListener{
    @Override
    protected int getLayoutId() {
        return R.layout.act_creat_thread;
    }

    @Override
    protected void initData() {
        binding.button1.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
        binding.button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                //创建一个线程
                new FirstThread().start();
                new FirstThread().start();
                binding.tv.setText("开启两个线程不共享实例变量i");
                break;
            case R.id.button2:
               SecondThread secondThread= new SecondThread();

                new Thread(secondThread,"线程1").start();
                new Thread(secondThread,"线程2").start();
                binding.tv.setText("开启两个线程共享实例变量i");
                break;
            case R.id.button3:
                new Thread(task,"线程3").start();

                try {
//                    通过get()得到call()的返回值，但会造成主线程阻塞，知道call()结束并返回
                    Log.e("TAG",task.get()+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    /**
     *  创建线程方法一：继承Thread
     */
    class FirstThread extends Thread{
        int i=0;

        @Override
        public void run() {
            String threadName=this.getName();
            for (;i<50;i++){
                Log.e("TAG","threadName:"+threadName+"   i:"+i);
            }
            super.run();
        }
    }

    /**
     *  创建线程方法二：实现Runnable接口
            *  注意：1：FirstThread可直接代表线程对象，而SecondThread为Runnable对象只能作为线程对象的target;
            *       2: 从Log的i循环可以看出，方式一不能共享实例变量（i）,而方式二(实现Runable接口)是可以共享实例变量（i）的，
            *          原因是：创建的Runnable对象只是线程的target,而多个线程可以共享同一个线程的target类的实例变量；
            */
            class SecondThread implements Runnable{
                int i=0;
                @Override
                public void run() {
                    String threadName=Thread.currentThread().getName();
                    for (;i<50;i++){
                        Log.e("TAG","threadName:"+threadName+"   i:"+i);
                    }
        }
    }


    /**
     * 创建线程方式3：使用Callable和Future创建线程
     *      实现Callable和实现Runnable接口并没有太大的差别，只是Callable的call()允许抛出异常，而且允许带返回值
     * 注意：1：FutureTask实现了Future和Runnable接口，可以作为Thread的target
     */
    FutureTask<Integer> task=new FutureTask<Integer>(
            new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    int i=0;
                    String threadName=Thread.currentThread().getName();
                    for (;i<50;i++){
                        Log.e("TAG","threadName:"+threadName+"   i:"+i);
                    }
                    return i;
                }
            }
    );


}
