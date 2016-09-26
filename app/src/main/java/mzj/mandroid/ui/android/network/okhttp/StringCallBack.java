package mzj.mandroid.ui.android.network.okhttp;

import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 振坚 on 2016/8/5.
 */
public class StringCallBack extends Callback<String> {

    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        return response.body().string();
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(String response, int id) {

    }
}
