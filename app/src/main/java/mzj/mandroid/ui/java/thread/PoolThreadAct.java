package mzj.mandroid.ui.java.thread;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActPoolThreadBinding;

/**
 * creat by mzj on 2016/10/10 21:15
 */

public class PoolThreadAct extends BaseActivity<ActPoolThreadBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.act_pool_thread;
    }

    @Override
    protected void initData() {
        binding.detail.setOnClickListener(this);
        binding.newCachedThreadPool.setOnClickListener(this);
        binding.newFixedThreadPool.setOnClickListener(this);
        binding.newSingleThreadExecutor.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail:
                getDialogLayout(R.layout.dialog_java_thread_pool);

                break;
            case R.id.newCachedThreadPool:
                newCachedThreadPool();
                break;
            case R.id.newFixedThreadPool:
                newFixedThreadPool();
                break;
            case R.id.newSingleThreadExecutor:
                newSingleThreadExecutor();
                break;

}
    }
    /**
     * Java通过Executors提供四种线程池，分别为：
     *newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     *newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     *newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
     *newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     */

    /**
     * newCachedThreadPool
     */
//      创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
//      线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
    private void newCachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /**
             * execute(Runnable x) 没有返回值。可以执行任务，但无法判断任务是否成功完成。
             */
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Log.e("TAG",Thread.currentThread().getName()+index);
                }
            });
        }
    }

    /**
     * newFixedThreadPool
     */
//    创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
//    因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
//    定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()。
    private void newFixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            /**
             * submit(Runnable x) 返回一个future。可以用这个future来判断任务是否成功完成。
             */
            Future future =  fixedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.e("TAG",Thread.currentThread().getName()+index);
                        Log.e("TAG","Runtime.getRuntime().availableProcessors():::"+Runtime.getRuntime().availableProcessors()+"");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            try {
                if(future.get()==null){//如果Future's get返回null，任务完成
                    Log.e("TAG","任务完成");
                }
            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
                //否则我们可以看看任务失败的原因是什么
                Log.e("TAG",e.getCause().getMessage());
            }
        }

        fixedThreadPool.shutdown();
    }


    /**
     * newSingleThreadExecutor
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     * 结果依次输出，相当于顺序执行各个任务。
     * 现行大多数GUI程序都是单线程的。Android中单线程可用于数据库操作，文件操作，应用批量安装，应用批量删除等不适合并发但可能IO阻塞性及影响UI线程响应的操作。
     */
    private void newSingleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }


}
