package mzj.mandroid.ui.android.network.retrofit.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import java.util.Map;
import java.util.concurrent.TimeUnit;

import mzj.mandroid.ui.android.network.retrofit.api.BaseApiService;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

/**
 * creat by mzj on 2016/9/23 11:39
 */

public class RetrofitClient {
    private static final int DEFAULT_TIMEOUT = 5;

    private BaseApiService apiService;

    private OkHttpClient okHttpClient;

    public static String baseUrl = "";

    private static Context mContext;

    private static RetrofitClient sNewInstance;
    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient(mContext);

    }



    public static RetrofitClient getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }


    public static RetrofitClient getInstance(Context context, String url) {
        if (context != null) {
            mContext = context;
        }
        sNewInstance = new RetrofitClient(context, url);
        return sNewInstance;
    }

    private RetrofitClient(Context context) {

        this(context, null);
    }

    private RetrofitClient(Context context, String url) {

        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

//                .cookieJar(new NovateCookieManger(context))
//                .addInterceptor(new BaseInterceptor(mContext))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
        apiService = retrofit.create(BaseApiService.class);
    }

    public void getData(Subscriber<ResponseBody> subscriber, String ip) {
//        apiService.getData(ip)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
    }

    public void get(String url, Map headers, Map parameters, Subscriber<ResponseBody> subscriber) {
//        apiService.executeGet(url, headers, parameters)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
    }

    public void post(String url, Map headers, Map parameters, Subscriber<ResponseBody> subscriber) {
//        apiService.executePost(url, headers, parameters)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
    }
}
