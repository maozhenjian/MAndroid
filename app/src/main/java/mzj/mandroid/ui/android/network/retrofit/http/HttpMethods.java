package mzj.mandroid.ui.android.network.retrofit.http;

import android.util.Log;


import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import mzj.mandroid.ui.android.network.retrofit.api.BaseApiService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 振坚 on 16/8/5.
 *
 *
 */
public class HttpMethods {

    public static final String BASE_URL = "http://artist.beyondin.com/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private BaseApiService baseApiService;

    //构造方法私有,初始化Retrofit
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        // 开启Log 拦截器
        builder .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("CUSTOM_HEADER", "Yahoo")
                        .addHeader("ANOTHER_CUSTOM_HEADER", "Google")
                        .addHeader("Cookie", "PHPSESSION=4dlres5tnvdrd37q9gfibs2966")
                        .build();
                return chain.proceed(request);
            }
        });

        retrofit = new Retrofit.Builder()
                .client(builder.build())
//                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        baseApiService = retrofit.create(BaseApiService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static  HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }


    /**
    * @param subscriber 由调用者传过来的观察者对象
    */
    public <T> void getNetData(Map parameters,Subscriber<T> subscriber) {
        Observable observable = baseApiService.getResponse(parameters).map(new HttpResultFunc<T>());
        toSubscribe(observable, subscriber);
    }

    private <T> void toSubscribe(Observable<T> observable, Subscriber<T> subscriber){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<ResponseBody, T> {

        @Override
        public T call(ResponseBody httpResult) {

            return (T)(httpResult);
        }
    }



}
