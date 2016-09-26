package mzj.mandroid.ui.android.network.retrofit.subscribers;

import org.json.JSONObject;

import okhttp3.ResponseBody;

/**
 * Created by mzj on 16/8/5.
 */
public interface SubscriberOnNextListener<T>{
    void onSuccess(T t);
    void onFail(String t);
}
