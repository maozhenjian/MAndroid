package mzj.mandroid.ui.android.network.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * creat by mzj on 2016/9/28 10:54
 */

public class MyHandler extends Handler {

    public MyHandler(Looper looper) {
        super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
        Bundle bundle=msg.getData();
        String s=bundle.getString("name");
        Log.e("TAG","msg:"+s);
        Log.e("TAG","msg:"+msg.arg1);
        Log.e("TAG","handleMessage:"+Thread.currentThread().getId());
        Log.e("TAG","handleMessage:"+Thread.currentThread().getName());
    }
}
