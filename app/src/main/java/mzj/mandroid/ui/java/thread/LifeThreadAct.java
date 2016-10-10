package mzj.mandroid.ui.java.thread;

import android.util.Log;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActLifeThreadBinding;

/**
 * creat by mzj on 2016/10/8 22:14
 * 线程5种状态：新建(New),就绪(Runnable),阻塞(Blocked),死亡(Dead)
 * 注意：
 *      1：当线程对象调用start()后，该线程处于就绪状态，线程并没有开始运行，只是表示可以运行了，何时运行需要取决于JVM调度；
 *      2：isAlive()测试线程是否死亡；
 *      3：如果计算机只有一个CPU，任何时刻只有一个线程处于运行状态
 *
 *
 */

public class LifeThreadAct extends BaseActivity<ActLifeThreadBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.act_life_thread;
    }

    @Override
    protected void initData() {
       binding.buttonThread.setOnClickListener(this);
        binding.joinThread.setOnClickListener(this);
      ImageLoader.getInstance().displayImage("http://image.lxway.com/upload/2/a5/2a5bfa77e2e7ed89eb2f9ad01ab64b26.png",binding.iv);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case  R.id.buttonThread:
               /**
                *由log看出当i=20时并没有立即执行,只是使线程进入就绪状态
                * */
               for (int i=0;i<30;i++){
                   Log.e("TAG",Thread.currentThread().getName()+"   "+i);
                   if (i==20){
                       new FirstThread().start();
                   }
               }
            break;

            case R.id.joinThread:
                getDialogLayout(R.layout.dialog_java_thread_join);
                for (int i=0;i<100;i++){
                    Log.e("TAG",Thread.currentThread().getName()+"   "+i);
                    if (i==20){
                      FirstThread joinThread=  new FirstThread();
                      joinThread.start();
                        /**
                         * 在主线程中调用了joinThread的join(),主线程将被阻塞，
                         * 必须等joinThread执行完才能继续执行
                         * */
                        try {
                            joinThread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
                break;
        }
    }


    class FirstThread extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i=0;i<30;i++){
                Log.e("TAG",Thread.currentThread().getName()+"   "+i);
            }
        }
    }
}
