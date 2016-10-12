package mzj.mandroid.ui.java.thread;

import android.util.Log;
import android.view.View;

import junit.framework.TestListener;

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

public class CommuThreadAct extends BaseActivity<ActCommunicationThreadBinding> implements View.OnClickListener {

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
    binding.test1.setOnClickListener(this);
        binding.test2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test1:
                Num num = new Num(0);
                Thread thOdd = new Thread(new ThOdd(num));
                Thread thEdd = new Thread(new ThEdd(num));
                Thread pdd = new Thread(new ThEdd(num));
                thOdd.setName("aaaaaaaaaa");
                thEdd.setName("bbbbbbbbbb");
                pdd.setName("cccccccccc");
                thOdd.start();
                thEdd.start();
                pdd.start();
                break;

            case R.id.test2:
                Thread NumThread = new Thread(new NumThread());
                Thread CharThread = new Thread(new CharThread());
                NumThread.start();
                CharThread.start();
                break;
        }
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

    class NumThread implements Runnable {

        @Override
        public void run() {
            while(!exit) {
                AddNum();
            }
        }
    }

    char charNumA='A'-1;
    class CharThread implements Runnable {
        @Override
        public void run() {
            while(!exit) {
                AddChar();

            }
        }
    }
    int numA=0;
    private synchronized void AddNum(){
        if (numA<100){
            try {
                notifyAll();
//                numA+=1;
                numA++;
                Log.e("TAG",""+numA);
//                numA+=1;
                numA++;
                Log.e("TAG",""+numA);
                wait();
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void AddChar(){
        if (charNumA<'Z'){
            try {
                notifyAll();
                charNumA+=1;
                Log.e("TAG",""+charNumA);
                wait();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        exit=true;
    }
}
