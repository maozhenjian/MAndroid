package mzj.mandroid.ui.android.network.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActHandlerThreadBinding;


/**
 * creat by mzj on 2016/9/28 11:52
 *
 * http://blog.csdn.net/lmj623565791/article/details/47079737/
 */

public class HandlerThreadAct extends BaseActivity<ActHandlerThreadBinding> {


    private HandlerThread mCheckMsgThread;
    private Handler mCheckMsgHandler;
    private boolean isUpdateInfo;

    private static final int MSG_UPDATE_INFO = 0x110;

    //与UI线程管理的handler
    private Handler mHandler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.act_handler_thread;
    }

    @Override
    protected void initData() {
        initBackThread();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //开始查询
        isUpdateInfo = true;
        mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //停止查询
        isUpdateInfo = false;
        mCheckMsgHandler.removeMessages(MSG_UPDATE_INFO);
    }

    private void initBackThread() {
        mCheckMsgThread = new HandlerThread("check-message-coming");
        mCheckMsgThread.start();
        mCheckMsgHandler = new Handler(mCheckMsgThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.e("TAG","handleMessage:"+Thread.currentThread().getName());  //子线程（从服务器分析数据）
                checkForUpdate();
                if (isUpdateInfo) {
                    mCheckMsgHandler.sendEmptyMessageDelayed(MSG_UPDATE_INFO, 1000);
                }
            }
        };


    }

    /**
     * 模拟从服务器解析数据
     */
    private void checkForUpdate() {
        try {
            //模拟耗时
            Thread.sleep(1000);

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Log.e("TAG","run:"+Thread.currentThread().getName());  //主线程
                    String result = "实时更新中，当前大盘指数：<font color='red'>%d</font>";
                    result = String.format(result, (int) (Math.random() * 3000 + 1000));
                    binding.idTextview.setText(Html.fromHtml(result));
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mCheckMsgThread.quit();
    }


}