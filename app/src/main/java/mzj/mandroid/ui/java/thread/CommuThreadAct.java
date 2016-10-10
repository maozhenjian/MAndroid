package mzj.mandroid.ui.java.thread;

import android.util.Log;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActCommunicationThreadBinding;

/**
 * creat by mzj on 2016/10/9 22:44
 *
 * wait():导致当前线程等待，直到其他线程调用该同步监视器的notify()或notifyAll()方法来唤醒该线程；
 *
 * notify():唤醒在此同步监视器上的单个线程
 *
 * notifyAll():唤醒所有在此同步监视器上的线程
 */

public class CommuThreadAct extends BaseActivity<ActCommunicationThreadBinding> {

    /**
     * 设置线程退出的标识符
     */
    public volatile boolean exit = false;
    @Override
    protected int getLayoutId() {
        return R.layout.act_communication_thread;
    }

    @Override
    protected void initData() {
        Num num = new Num(0);
        Thread thOdd = new Thread(new ThOdd(num));
        Thread thEdd = new Thread(new ThEdd(num));
        thOdd.setName("odd");
        thEdd.setName("edd");
        thOdd.start();
        thEdd.start();

    }

    class Num {
        public int num = 0;
        public Num(int num){
            this.num = num ;
        }
        public synchronized void printOdd(){
            Log.e("TAG",Thread.currentThread().getName()+"------->"+(num++));
            try{

                notifyAll();
                wait();
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        public synchronized void printEdd(){
            Log.e("TAG",Thread.currentThread().getName()+"------->"+(num++));
            try{
                notifyAll();
                wait();
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    class ThOdd implements Runnable{
        private Num num ;
        public ThOdd(Num num){
            this.num = num ;
        }
        public void run(){
            while(!exit){
                num.printOdd();
            }
        }
    }
    class ThEdd implements Runnable{
        private Num num ;
        public ThEdd(Num num){
            this.num = num ;
        }
        public void run(){
            while(!exit){
                num.printEdd();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exit=true;
    }
}
