package mzj.mandroid.ui.android.network.volley;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActVolleyFirstBinding;
import mzj.mandroid.ui.android.network.retrofit.model.Weather;
import mzj.mandroid.ui.android.network.volley.customrequest.GsonRequest;

/**
 * Created by 振坚 on 2016/8/4.
 * 特点：
 * 自动调度网络请求
 * 多线程并发网络连接、请求优先级
 * 请求Cache和内存管理
 * 扩展性强 如：支持自定义重连等
 * 支持请求取消
 * 强大的Debug、tracing
 * 缺点：
 * 对于大数据量的网络操作糟糕， 如：下载文件等
 *
 * 设计目的：适合去进行数据量不大，但通信频繁的网络操作，如list加载等等
 */
public class VolleyFirstAct extends BaseActivity<ActVolleyFirstBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.act_volley_first;
    }

    @Override
    protected void initData() {
        /**
         获取到一个RequestQueue对象,注意这里拿到的RequestQueue是一个请求队列对象，它可以缓存所有的HTTP请求
         基本上在每一个需要和网络交互的Activity中创建一个RequestQueue对象就足够了。
         */
        RequestQueue mQueue = Volley.newRequestQueue(this);

    /*
        发出发出一条HTTP请求"Get"，创建一个StringRequest对象
        第一个参数就是目标服务器的URL地址，第二个参数是服务器响应成功的回调，第三个参数是服务器响应失败的回调
     */
        StringRequest stringRequest = new StringRequest("http://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("TAG", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });

        mQueue.add(stringRequest);


        //“POST”
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, "http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("返回", s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("错误", volleyError.getMessage(), volleyError);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("params1", "value1");
                map.put("params2", "value2");
                return map;
            }
        };

        mQueue.add(stringRequest2);

        //JsonRequest是一个抽象类，因此我们无法直接创建它的实例
        //       子类 JsonObjectRequest和JsonArrayRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                "http://fanyi.youdao.com/openapi.do?keyfrom=httpgetmysddfr&key=1415591803&type=data&doctype=json&version=1.1&q=man", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String value = response.getString("web");
                            JSONArray jsonArray = new JSONArray(value);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String value2 = jsonObject.getString("value");
                                String key = jsonObject.getString("key");
                                Log.e("JSONObject", value2);
                                Log.e("JSONObject", key);
                            }
                            Log.e("0000", value);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("3333", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("4444", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("params2", "value2");
                return super.getParams();
            }
        };

        mQueue.add(jsonObjectRequest);


/**
 * 可以看到，ImageRequest的构造函数接收六个参数，
 * 第一个参数就是图片的URL地址，
 * 第二个参数是图片请求成功的回调，这里我们把返回的Bitmap参数设置到ImageView中。
 * 第三第四个参数分别用于指定允许图片最大的宽度和高度，如果指定的网络图片的宽度或高度大于这里的最大值，
 * 则会对图片进行压缩，指定成0的话就表示不管图片有多大，都不会进行压缩。
 * 第五个参数用于指定图片的颜色属性，
 * Bitmap.Config下的几个常量都可以在这里使用，其中ARGB_8888可以展示最好的颜色属性，每个图片像素占据4个字节的大小，
 * 而RGB_565则表示每个图片像素占据2个字节大小。
 * 第六个参数是图片请求失败的回调，这里我们当请求失败时在ImageView中显示一张默认图片。
 * */
        ImageRequest imageRequest = new ImageRequest(
                "http://a1.att.hudong.com/51/59/300001054794129041591176305.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        binding.imageIv.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        mQueue.add(imageRequest);


/**
 *      ImageLoader的用法
 对图片进行缓存，还可以过滤掉重复的链接，避免重复发送请求
 1. 创建一个RequestQueue对象。
 2. 创建一个ImageLoader对象。
 3. 获取一个ImageListener对象。
 4. 调用ImageLoader的get()方法加载网络上的图片
 */

        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache() {
            @Override
            public void putBitmap(String url, Bitmap bitmap) {


            }

            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }
        });
        //，第一个参数指定用于显示图片的ImageView控件，第二个参数指定加载图片的过程中显示的图片，第三个参数指定加载图片失败的情况下显示的图片。
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(binding.imageIv, 0, 0);
        imageLoader.get("http://a2.att.hudong.com/38/59/300001054794129041591416974.jpg", listener, 0, 0);


        /**
         * NetworkImageView是一个自定义控件，它是继承自ImageView的，具备ImageView控件的所有功能，并且在原生的基础之上加入了加载网络图片的功能。
         * NetworkImageView控件的用法要比前两种方式更加简单，大致可以分为以下五步：
         1. 创建一个RequestQueue对象。
         2. 创建一个ImageLoader对象。
         3. 在布局文件中添加一个NetworkImageView控件。
         4. 在代码中获取该控件的实例。
         5. 设置要加载的图片地址。
         */


        binding.networkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        binding.networkImageView.setErrorImageResId(R.mipmap.ic_launcher);
        binding.networkImageView.setImageUrl("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",  imageLoader);



        /**
         * 自定义XMLRequest
         * */
    /*    XMLRequest xmlRequest = new XMLRequest(
                "http://flash.weather.com.cn/wmaps/xml/china.xml",
                new Response.Listener<XmlPullParser>() {
                    @Override
                    public void onResponse(XmlPullParser response) {
                        try {
                            int eventType = response.getEventType();
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                switch (eventType) {
                                    case XmlPullParser.START_TAG:
                                        String nodeName = response.getName();
                                        if ("city".equals(nodeName)) {
                                            String pName = response.getAttributeValue(0);
                                            Log.e("TAG", "pName is " + pName);
                                        }
                                        break;
                                }
                                eventType = response.next();
                            }
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(xmlRequest);
*/


        /**
         * 自定义GsonRequest
         * */
        GsonRequest<Weather> gsonRequest = new GsonRequest<>(
                "http://www.weather.com.cn/adat/sk/101010100.html", Weather.class,
                new Response.Listener<Weather>() {
                    @Override
                    public void onResponse(Weather weather) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(gsonRequest);


        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass();
        Log.e("heapSize", String.valueOf(heapSize));
    }


    //JSON解析
    private void parseJSONwithJSONObject(String jsondata) {
        try {
            JSONArray jsonArray = new JSONArray(jsondata);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String value = jsonObject.getString("value");
                Log.e("JSONObject", value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
