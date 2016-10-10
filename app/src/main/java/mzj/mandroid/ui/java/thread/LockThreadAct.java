package mzj.mandroid.ui.java.thread;

import android.util.Log;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.concurrent.locks.ReentrantLock;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActLifeThreadBinding;
import mzj.mandroid.databinding.ActLockThreadBinding;

/**
 * creat by mzj on 2016/10/8 22:14
 *
 */

public class LockThreadAct extends BaseActivity<ActLockThreadBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.act_lock_thread;
    }

    @Override
    protected void initData() {
    binding.lockMoney.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lock_money:
//                创建一个账户
                Account account=new Account("121",1000);
//                模拟两个线程同时取款
                new DrawThread("毛振坚",account,600).start();
                new DrawThread("杨丽慈",account,600).start();
                break;


        }
    }

    class DrawThread extends Thread{
//        模拟账户
        private Account account;
//        提取金额
        private double drawAmount;

        public DrawThread(String name, Account account, double drawAmount) {
            super(name);
            this.account = account;
            this.drawAmount = drawAmount;
        }

        @Override
        public void run() {
            super.run();
//            加锁--》修改--》释放锁
            synchronized (account){
                if (account.getBalance()>=drawAmount){
                    Log.e("TAG","取出金额:"+drawAmount);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    account.setBalance(account.getBalance()-drawAmount);
                    Log.e("TAG","当前余额为："+account.getBalance());
                }else {
                    Log.e("TAG","余额不足");
                }
            }
        }
    }

    class Account{
//        账户编号
        private String accountNo;
//        账户余额
        private double balance;

        public Account(String accountNo, double balance) {
            this.accountNo = accountNo;
            this.balance = balance;
        }


        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }



    }


    /**
     * 同步锁
     */
    class lock{
        private final ReentrantLock lock=new ReentrantLock();
        public void m(){
//            加锁
            lock.lock();
            try {
//                执行代码
            }finally {
//            解锁
            lock.unlock();
            }
        }
    }


}
