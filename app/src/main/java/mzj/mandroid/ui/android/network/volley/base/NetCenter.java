package mzj.mandroid.ui.android.network.volley.base;


import android.util.Log;

import org.apache.http.params.HttpParams;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import mzj.mandroid.ui.android.network.retrofit.http.BasicNameValuePair;
import mzj.mandroid.ui.android.network.retrofit.http.NameValuePair;


/**
 * @project: TIIS
 * @Description: HTTP请求factory
 * @author JiangRC
 * @date 2014-12-18 下午12:39:23
 * @version V1.0
 */
public class NetCenter {
    private static HttpParams httpParams;
    public static String PHPSESSID; // 定义一个静态的字段，保存sessionID

    //public static String BASEPATH = "http://artist.beyondin.com/?m=api&a=api";//接口的url//
    public static String BASEPATH = "http://artist.beyondin.com/?m=api&a=api";//接口的url//
    public static String URL_BASE = "http://artist.beyondin.com";//用于webview的一些调用 //
//    public static String SRCPATH = "http://artist.beyondin.com";//用于图片资源的相关//
    public static String SRCPATH = "";//用于图片资源的相关////
    private final static String key =  "@J4*A9N7&B^A9Y7j6sWv8m6%q_p+z-h=";
    public static String APPID = "artistandroidappid@U*NDd8vK1^2pKh";
    //以下上传图片相关(URL不能带特殊字符 要转换成带%的16进制数传递)
    public static final String UPLOAD_BASE_URL = "http://artist.beyondin.com/api/uploadImage/appid/artistandroidappid@U%2ANDd8vK1%5E2pKh/submit/submit";
    //public static final String UPLOAD_BASE_URL = "http://artist.beyondin.com/api/uploadImage/appid/artistandroidappid@U*NDd8vK1^2pKh/submit/submit";

    public static final String UPFILE_NAME="upfile";

    public static String cookies;//

    public static String IMAGE_SIZE_560_420="?imageView2/1/w/560/h/420";
    public static String IMAGE_SIZE_480_560="?imageView2/1/w/480/h/560";
    public static String IMAGE_SIZE_200_200="?imageView2/1/w/200/h/200";
    public static String IMAGE_W_SIZE_90="?imageView2/2/w/90";
    public static String IMAGE_W_SIZE_480="?imageView2/2/w/480";
    /**
     * 将字符串转成MD5值
     * @param string
     * @return
     */
    public static String stringToMD5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    // 对传进来的参数进行处理
    public static List<NameValuePair> paramEncrypt(List<NameValuePair> params) {
        // 首先对字符串进行拼接
        // 字典排序
        Collections.sort(params, new Comparator<NameValuePair>() {

            @Override
            public int compare(NameValuePair lhs, NameValuePair rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        String content = params.get(0).getName() + "="
                + params.get(0).getValue();
        for (int i = 1; i < params.size(); i++) {
            content += "&" + params.get(i).getName() + "="
                    + params.get(i).getValue();
        }
        content += key;

        Log.e("TAG","content"+content);
        String token = stringToMD5(content);
        params.add(new BasicNameValuePair("token", token));
        // 再添加上key的情况下，产生一个token
        return params;
    }

    public static String getAPPID() {
        return APPID;
    }


    // 对传进来的参数进行处理,产生一个token,返回一个String
    public static String paramEncryptStr(List<NameValuePair> params) {
        // 首先对字符串进行拼接
        // 字典排序
        Collections.sort(params, new Comparator<NameValuePair>() {
            @Override
            public int compare(NameValuePair lhs, NameValuePair rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        String content = params.get(0).getName() + "="
                + params.get(0).getValue();
        for (int i = 1; i < params.size(); i++) {
            content += "&" + params.get(i).getName() + "="
                    + params.get(i).getValue();
        }
        String temp=content+key;
        String token = stringToMD5(temp);
        content+="&"+"token="+token;
        params.add(new BasicNameValuePair("token", token));
        // 再添加上key的情况下，产生一个token
        return content;
    }

    public static String paramEncryptStrForPay(List<NameValuePair> params) {

        // 首先对字符串进行拼接
        // 字典排序
        Collections.sort(params, new Comparator<NameValuePair>() {
            @Override
            public int compare(NameValuePair lhs, NameValuePair rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        String content = params.get(0).getName() + "="
                + params.get(0).getValue();
        for (int i = 1; i < params.size(); i++) {
            content += "&" + params.get(i).getName() + "="
                    + params.get(i).getValue();
        }
        return content;
    }

    public List<NameValuePair> getDefaultParams(){

        List<NameValuePair> params = new LinkedList<NameValuePair>();
        params.add(new BasicNameValuePair("appid", getAPPID()));

        if (NetCenter.PHPSESSID == null) {
            params.add(new BasicNameValuePair("PHPSESSID", ""));
        } else
            params.add(new BasicNameValuePair("PHPSESSID", NetCenter.PHPSESSID));
        return params;
    }
}