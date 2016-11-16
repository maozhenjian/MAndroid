package mzj.mandroid.ui.android.network.retrofit.subscribers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;


import mzj.mandroid.ui.android.network.retrofit.progress.ProgressCancelListener;
import mzj.mandroid.ui.android.network.retrofit.progress.ProgressDialogHandler;
import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 * Created by mzj on 16/9/22
 */
public class ProgressSubscriber<T> extends Subscriber<ResponseBody> implements ProgressCancelListener {
    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;
    private Class<T> mClass;
    private Gson mGson;
    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context,Class<T> mClass) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        this.mClass = mClass;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    private void showProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
        Log.i("TAG","onCompleted");
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (mSubscriberOnNextListener!=null)
        mSubscriberOnNextListener.onFail(e.getMessage());

        dismissProgressDialog();

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     * @param responseBody 返回的数据
     */
    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            JSONObject  jsonObject = new JSONObject(responseBody.string());

            int code = jsonObject.getInt("code");
            if (code == 0) {
                mSubscriberOnNextListener.onSuccess(new Gson().fromJson(jsonObject.toString(), mClass));
            } else {
                mSubscriberOnNextListener.onFail(jsonObject.toString());
            }
            //对常见错误码 做统一处理
//            if (jsonObject.getInt("code") == 0) {

//            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}