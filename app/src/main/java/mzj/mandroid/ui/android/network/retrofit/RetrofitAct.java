package mzj.mandroid.ui.android.network.retrofit;

import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.HashMap;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActRetrofitBinding;
import mzj.mandroid.ui.android.network.retrofit.api.BaseApiService;
import mzj.mandroid.ui.android.network.retrofit.api.WeatherApi;
import mzj.mandroid.ui.android.network.retrofit.http.HttpMethods;
import mzj.mandroid.ui.android.network.retrofit.model.Headlines;
import mzj.mandroid.ui.android.network.retrofit.model.Weather;
import mzj.mandroid.ui.android.network.retrofit.param.ArticleParam;
import mzj.mandroid.ui.android.network.retrofit.subscribers.ProgressSubscriber;
import mzj.mandroid.ui.android.network.retrofit.subscribers.SubscriberOnNextListener;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mzj on 16/9/22
 * <p>
 * 推荐文章：    http://blog.csdn.net/sk719887916/article/details/51597816  <Retrofit> 码小白
 * Square 开源RESTFUL API库， Retrofit的跟Volley是一个套路，但解耦的更彻底。同时自己内部对OkHtttp客户端做了封装， 用Retrofit+OkHttp基本上已经可以处理任何业务场景了。
 * 特点：
 * 简化了网络请求流程，支持注解请求
 * 支持多种Converter、还可以自定义， 如：Gson、Jackson、protobuf、xml
 * 可以配合RxJava使用
 */
public class RetrofitAct extends BaseActivity<ActRetrofitBinding> implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.act_retrofit;
    }

    @Override
    protected void initData() {
        binding.setAct(this);

    }

    /**
     * url:  http://artist.beyondin.com/?m=api&a=api&PHPSESSID=4dlres5tnvdrd37q9gfibs2966&api_name=artist.article.getArticleList&appid=1&type=1&is_rec=1&token=4026727a617ce1fbbfbff6302836eef8
     */
    //retrofit请求的一个最简单的例子
    public void retrofitSimple() {
        String API_URL = "http://artist.beyondin.com/";
        Retrofit retrofit2 = new Retrofit.Builder()
                //  开启Log
                .client(new OkHttpClient.Builder().addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())  //配置Gson
                .build();
// 创建 RequestBody，用于封装 请求RequestBody
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//// MultipartBody.Part is used to send also the actual file name
//        MultipartBody.Part body =
//                MultipartBody.Part.createFormData("image", file.getName(), requestFile);

//// 添加描述
//        String descriptionString = "hello, 这是文件描述";
//        RequestBody description =
//                RequestBody.create(
//                        MediaType.parse("multipart/form-data"), descriptionString);
//        // 执行请求
//        Call<ResponseBody> call = service.upload(description, body);


        Call<Weather> call = retrofit2.create(WeatherApi.class).getWeather("1", "4026727a617ce1fbbfbff6302836eef8");

        // 如果要调用同步请求，只需调用execute；而发起一个异步请求则是调用enqueue。
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
//                if (response.body().result.data.size() > positon) {
//                    binding.simpleBt.setText(response.body().result.data.get(positon).title);
//                    positon++;
//                } else {
//                    positon = 0;
//                    return;
//                }
                Log.e("TAG", response.body().data.article_list.get(0).base_pic);//主线程
                Log.i("TAG", response.raw().toString()); //URL
                Log.e("TAG", "当前线程:" + Thread.currentThread().getName());//主线程
                Log.i("TAG", response.body().toString());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });

    }

    //retrofit+RxJava
    public void retrofitRx() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 开启Log 拦截器
        builder .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();

        builder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("CUSTOM_HEADER", "Yahoo")
                        .addHeader("ANOTHER_CUSTOM_HEADER", "Google")
                        .addHeader("Cookie", "PHPSESSION=4dlres5tnvdrd37q9gfibs2966")
                        .build();
                return chain.proceed(request);
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl("http://api.artgoer.cn:8084/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        BaseApiService apiService = retrofit.create(BaseApiService.class);
//        ArticleParam articleParam = new ArticleParam();
//        articleParam.is_rec = "1";
//        articleParam.type = "1";
        HashMap<String,String> map=new HashMap<>();
        map.put("token","df68e038-143e-41cb-b554-456f78f184fc");
        map.put("pageIndex","1");

        apiService.getHeadliness("1","df68e038-143e-41cb-b554-456f78f184fc")
                .subscribeOn(Schedulers.io()) //子线程访问网络
                .observeOn(AndroidSchedulers.mainThread()) //回调到主线程
                .subscribe(new Subscriber<Headlines>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Headlines headlines) {
                        Log.e("TAG", "onNext:" + headlines.data.exhibitList.get(0).exhibitArtistList.get(0).exhibitCity);
                    }

                });
    }

    //Rx+retrofit简单封装后的例子
    public void retrofitPackge() {
        ArticleParam articleParam = new ArticleParam();
        articleParam.is_rec = "1";
        articleParam.type = "1";
        HttpMethods.getInstance().getNetData(articleParam.getMap(), new ProgressSubscriber<Weather>(new SubscriberOnNextListener<Weather>() {
            @Override
            public void onSuccess(Weather weather) {
                Log.e("TAG", "onSuccess:??");
                Log.e("TAG", "weather:" + weather.data.article_list.get(0).base_pic);
                Log.e("TAG", "weather:" + weather.data.article_list.get(0).author_name);
            }

            @Override
            public void onFail(String responseBody) {
                Log.e("TAG", "onFail:" + responseBody);
            }
        }, RetrofitAct.this, Weather.class));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.simpleBt:
                retrofitSimple();
                break;
            case R.id.rxBt:
                retrofitRx();
                break;
            case R.id.packBt:
                retrofitPackge();
                break;

        }
    }

}
