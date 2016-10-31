package mzj.mandroid.ui.android.network.okhttp;

import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActOkhttpBinding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 振坚 on 2016/8/4.
 *
 * http://www.imooc.com/api/teacher?type=4&num=30
 *
 *
 *
 * 文章：http://www.jianshu.com/p/aad5aacd79bf     OkHttp3源码分析[综述]
 * https://github.com/square/okhttp/wiki/Recipes    官方文档
 */
public class OkhttpAct extends BaseActivity<ActOkhttpBinding> implements View.OnClickListener {
    OkHttpClient mOkHttpClient;
    @Override
    protected int getLayoutId() {
        return R.layout.act_okhttp;
    }

    @Override
    protected void initData() {
        initOkhttpUtil();
        binding.setAct(this);
//        getInfo();
        binding.mProgressBar.setMax(100);
    }

    void post(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "4");
        params.put("num", "30");
        OkHttpUtils
                .get()
                .url("http://www.imooc.com/api/teacher?")
                .params(params)
                .build()
                .execute(new MyStringCallback());
    }



    void getInfo() {
        //创建okHttpClient对象
        mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url("http://www.imooc.com/api/teacher?type=4&num=30")
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            //还是需要使用handler等
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                response.body().byteStream();
                Log.i("TAG", s);
                Log.i("TAG", "当前线程：" + Thread.currentThread().toString());
            }
        });
    }
    private final OkHttpClient client = new OkHttpClient();

    /**
     * 同步
     * @throws Exception
     */
    public void run() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://publicobject.com/helloworld.txt")
                        .build();

                Response response;
                try {
                    response = client.newCall(request).execute();
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0; i < responseHeaders.size(); i++) {
                        Log.e(TAG,responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    Log.e(TAG,response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    /**
     * 异步
     *
     * @throws Exception
     */
    public void asynchronousGet() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();

                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    Log.e(TAG, responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                Log.e(TAG, response.body().string());
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hz:
                post();
                break;
            case R.id.okhttp:
                getInfo();
                break;

            case R.id.synchronous_get:
                try {
                    run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.asynchronous_get:
                try {
                    asynchronousGet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }


    public class MyStringCallback extends StringCallback {
        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            Log.i("TAG", "onError" + id);
            binding.Tv.setText("onError:" + e.getMessage());
        }


        @Override
        public void onResponse(String response, int id) {
            Log.i("TAG", "onResponse：complete" + response);
            binding.Tv.setText("onResponse:" + response);
            switch (id) {
                case 100:
                    Toast.makeText(OkhttpAct.this, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(OkhttpAct.this, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    //配置okHttp(鸿洋工具类库),在APP中初始化
    void initOkhttpUtil(){
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("TAG"))
                .hostnameVerifier(new HostnameVerifier()
                {
                    @Override
                    public boolean verify(String hostname, SSLSession session)
                    {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
