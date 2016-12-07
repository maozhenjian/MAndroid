package mzj.mandroid.ui.android.network.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.lang.ref.WeakReference;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActHandlerBinding;

/**
 * creat by mzj on 2016/9/27 10:49
 * <p>
 *     http://blog.csdn.net/guolin_blog/article/details/9991569  郭霖
 * <p>
 * Handler大概的原理就是：Handler发送Message到MessageQueue，Looper从MessageQueue中取出Message执行。
 * <p>
 * <p>
 * 1、首先Looper.prepare()在本线程中保存一个Looper实例，然后该实例中保存一个MessageQueue对象；
 *    因为Looper.prepare()在一个线程中只能调用一次，所以MessageQueue在一个线程中只会存在一个。
 * 2、Looper.loop()会让当前线程进入一个无限循环，不端从MessageQueue的实例中读取消息，然后回调msg.target.dispatchMessage(msg)方法。
 * 3、Handler的构造方法，会首先得到当前线程中保存的Looper实例，进而与Looper实例中的MessageQueue先关联。
 * 4、Handler的sendMessage方法，会给msg的target赋值为handler自身，然后加入MessageQueue中。
 * 5、在构造Handler实例时，我们会重写handleMessage方法，也就是msg.target.dispatchMessage(msg)最终调用的方法。
 * 在Activity中，我们并没有显示的调用Looper.prepare()和Looper.loop()方法，为啥Handler可以成功创建呢，
 * 这是因为在Activity的启动代码中，已经在当前UI线程调用了Looper.prepare()和Looper.loop()方法。
 */

public class HandlerAct extends BaseActivity<ActHandlerBinding> implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.act_handler;
    }

    @Override
    protected void initData() {
        ImageLoader.getInstance().displayImage(
                "http://img.blog.csdn.net/20130817090611984?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvZ3VvbGluX2Jsb2c=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center",binding.iv);
        binding.sendBt.setOnClickListener(this);
        binding.postTv.setOnClickListener(this);
        binding.postEndTv.setOnClickListener(this);
        binding.probt.setOnClickListener(this);
        binding.handlerTv.setOnClickListener(this);
        binding.handlerThread.setOnClickListener(this);
    }


    /**
     * 对发送过来的Message进行统一的管理
     */
    private MHandler handler=new MHandler(this);

    private static class MHandler extends Handler {
        private WeakReference<Context> reference;

         MHandler(Context context) {
            this.reference =new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
             HandlerAct activity = (HandlerAct)reference.get();

            switch (msg.what) {
                case 0:
                    activity.  binding.messageTv.setText(msg.obj + "");
                    break;
                case 1:
                    Log.e("TAG", "probar：" + msg.arg1);
                    Log.e("TAG", "probar:" + Thread.currentThread().getName());
                    activity. binding.probar.setProgress(msg.arg1);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        移除消息队列中的消息
        handler.removeCallbacksAndMessages(null);
    }

//    private Handler handler = new Handler() {
//
//        public void handleMessage(Message msg) {
//
//        }
//
//    };

    Runnable updateThread = new Runnable() {
        int i=0;
        //主线程运行，执行更新UI
        @Override
        public void run() {
            i+=1;
            binding.messageTv.setText(i+"");
//            handler.post(updateThread);
            handler.postDelayed(updateThread, 500); //延迟3s,将这个线程加入到队列中
        }
    };

    //    进度条
    Runnable proThread = new Runnable() {
        int i = 0;
        @Override
        public void run() {
            Log.e("TAG", "proThread:" + Thread.currentThread().getName());  //主线程
            i +=1;
            handler.postDelayed(this,50);
            binding.probar.setProgress(i);
            if (i==100){
                handler.removeCallbacks(this);
            }
        }
    };


    private void newHandler(){
        HandlerThread handlerThread=new HandlerThread("handler_thread");
        handlerThread.start();
        //不能在没有调用Looper.prepare() 的线程中创建Handler
        MyHandler myHandler=new MyHandler(handlerThread.getLooper());

        //主线程发送到子线程
        Message message=myHandler.obtainMessage();
        message.arg1=1;
        Bundle bundle=new Bundle();
        bundle.putString("name","mzj");
        message.setData(bundle);
        message.sendToTarget(); //将message发送到handler
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.handler_thread:
                startActivity(new Intent(mContext,HandlerThreadAct.class));
                break;
            case R.id.send_bt:
                //子线程中获取数据，发送到主线
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message =Message.obtain();
                        message.what = 0;
                        message.obj = "这是Handler传来的信息";
                        handler.sendMessage(message);
                    }
                }).start();

                break;

            case R.id.post_tv:
                handler.post(updateThread);  //放入到队列当中
                break;

            case R.id.post_end_tv:
                handler.removeCallbacks(updateThread);  //停止
                break;
            case R.id.probt:
                //进度条
                handler.post(proThread);
                break;
            case R.id.handler_tv:
                newHandler();
                break;
        }
    }








}
