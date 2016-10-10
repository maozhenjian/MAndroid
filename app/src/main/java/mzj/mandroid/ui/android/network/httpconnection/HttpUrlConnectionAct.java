package mzj.mandroid.ui.android.network.httpconnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActHttpurlconnetionBinding;
import mzj.mandroid.ui.android.network.asynctask.BitmapAsyncTask;

/**
 * creat by mzj on 2016/9/27 09:56
 */

public class HttpUrlConnectionAct extends BaseActivity<ActHttpurlconnetionBinding> implements View.OnClickListener{

    @Override
    protected int getLayoutId() {
        return R.layout.act_httpurlconnetion;
    }

    @Override
    protected void initData() {
        binding.getBitmapBt.setOnClickListener(this);
        binding.getDateBt.setOnClickListener(this);


    }

    public void sendRequestWithHttpURLConnection() {
        //开启线程发送网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("HttpURLConnection", Thread.currentThread().getName().toString());
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://fanyi.youdao.com/openapi.do?keyfrom=httpgetmysddfr&key=1415591803&type=data&doctype=json&version=1.1&q=man");
                    connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");//请求方法
                    connection.setConnectTimeout(8000);//连接超时时间
                    connection.setReadTimeout(8000);//读取超时时间

                    //服务器返回的输入流
                    InputStream in = connection.getInputStream();
                    //对输入流进行读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    final StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.tv.setText(response.toString());
                        }
                    });
                    reader.close();
                    in.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getBitmapBt:
                new BitmapAsyncTask(binding.imageIv).execute("http://a2.att.hudong.com/38/59/300001054794129041591416974.jpg");
                break;
            case R.id.getDateBt:
                sendRequestWithHttpURLConnection();
                break;
        }
    }
}
