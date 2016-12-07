package mzj.mandroid.ui.android.network.volley.base;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mzj.mandroid.base.App;

/**
 * Created by JRC on 2015/1/7 0007.
 *
 */
public class BaseVolleyRequest extends Request<JSONObject> {
    private Map<String, String> mMap;
    private Response.Listener<JSONObject> mListener;
    public String cookieFromResponse;
    private String mHeader;
    private Map<String, String> sendHeader = new HashMap<String, String>(1);
    private Response.ErrorListener mErrorListener;
    private static final int SOCKET_TIMEOUT = 8000;
    private Context mContext = App.getApplication();

    //当http请求是post时，则需要该使用该函数设置往里面添加的键值对
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mMap;
    }

    public BaseVolleyRequest(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Map map) {
        super(Method.POST, url, errorListener);
        mListener = listener;
        mErrorListener = errorListener;
        mMap = map;
        for (Object s : map.keySet()) {
         Log.e("MAPkey:",s+"");
         Log.e("MAPvalues:",map.get(s)+"");
        }
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {

            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.e("TAG","response.data"+jsonString);

            mHeader = response.headers.toString();
            Log.e("TAG","mHeader"+mHeader);
            //使用正则表达式从reponse的头中提取cookie内容的子串
            Pattern pattern2 = Pattern.compile("Set-Cookie.*?;");
            Pattern pattern1 = Pattern.compile("set-cookie.*?;");
            Matcher m1 = pattern1.matcher(mHeader);
            Matcher m2 = pattern2.matcher(mHeader);

            if (m1.find()) {
                cookieFromResponse = m1.group();
            } else if (m2.find()) {
                cookieFromResponse = m2.group();
            }

            //去掉cookie末尾的分号
            cookieFromResponse = cookieFromResponse.substring(11, cookieFromResponse.length() - 1);
            //将cookie字符串添加到jsonObject中，该jsonObject会被deliverResponse递交，调用请求时则能在onResponse中得到
            JSONObject jsonObject = new JSONObject(jsonString);
            //在这里做一个session是否过期的检查，如果过期了，那就重新登录
            jsonObject.put("Cookie", cookieFromResponse);
//            //对常见错误码 做统一处理
//            if (jsonObject.getInt("code") == ErrorCodeExplain.ERROR_SESSION) {
//                login();
//            }



            return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }

    //JX add
    @Override
    public void deliverError(VolleyError error) {
        mErrorListener.onErrorResponse(error);
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> header = new HashMap<String, String>();
        return sendHeader;
        //here add set header
    }

    public void setSendCookie(String cookie) {
        sendHeader.put("Cookie", cookie);
    }

    public static String getErrorInfomation(VolleyError error) {
        Log.e("JXVolley", error.toString());
        if (error.getClass() == NoConnectionError.class) {
            return "网络不稳定,请检查你的网络";
        }
        return "网络异常";
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        RetryPolicy retryPolicy = new DefaultRetryPolicy(SOCKET_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return retryPolicy;
    }

}
