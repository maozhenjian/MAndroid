package mzj.mandroid.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/8/29.
 */
public class HttpUtils {
    public static byte[] getByteArrayFromNet(String path){
        try {
            URL url =new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200){
                InputStream is = httpURLConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer =new byte[1024*1024];
                int len=0;
                while ((len=is.read(buffer))!=-1){
                    baos.write(buffer,0,len);
                    baos.flush();
                }
                baos.close();
                is.close();
                return baos.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      return null;
    }

    public static String getJsonFromNet(String path){
        byte[] bytes = getByteArrayFromNet(path);
        return new String(bytes);
    }

    /**
     *从网络获取图片 压缩成指定大小.
     */
    public static Bitmap getBitmapFromNet(String path,int newHeight,int newWidth){
        //修改之后的
//        try {
//            URL url = new URL(path);
//            Log.i("MainActivity", "getBitmapFromNet: "+111);
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setConnectTimeout(10000);
//            httpURLConnection.setDoInput(true);
//            Log.i("MainActivity", "getBitmapFromNet: "+1110001);
//            httpURLConnection.connect();
//            Log.i("MainActivity", "getBitmapFromNet: "+1112221);
//            int code=httpURLConnection.getResponseCode();
//            Log.i("MainActivity", "getBitmapFromNet: "+code);
//            if(code==200){
//                InputStream is = httpURLConnection.getInputStream();
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inJustDecodeBounds=true;
//                BitmapFactory.decodeStream(is,null, options);
//                //获得图片原始尺寸
//                int oldWidth = options.outWidth;
//                int oldHeight = options.outHeight;
//                Log.i("MainActivity", "getBitmapFromNet: "+oldWidth+"=="+oldHeight);
//                //记录缩放比例
//                int size=1;
//                //计算比例
//                int scaleW = oldWidth / newWidth;
//                int scaleH = oldHeight / newHeight;
//                size=scaleH>scaleW?scaleH:scaleW;
//                //设置缩放比例
//                options.inSampleSize=size;
//                //不只解析边框
//                options.inJustDecodeBounds=false;
//                Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
//                Log.i("MainActivity", "getBitmapFromNet: "+222+bitmap);
//                return bitmap;
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.i("MainActivity", "getBitmapFromNet: "+333);
//        }
//
//
//        return  null;
        byte[] bytes = getByteArrayFromNet(path);
        Bitmap bitmap= twoScaleBitmapFromByte(bytes, newHeight, newWidth);
        return bitmap;
    }

    /**
     * 二次采样
     */

    public static Bitmap twoScaleBitmapFromByte(byte[] bytes,int newHeight,int newWidth){
        if (bytes==null) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        //获得图片原始尺寸
        int oldWidth=options.outWidth;
        int oldHeight = options.outHeight;
        //记录缩放比例
        int size=1;
        //计算比例
        int scaleW = oldWidth / newWidth;
        int scaleH = oldHeight / newHeight;
        size=scaleH>scaleW?scaleH:scaleW;
        //设置缩放比例
        options.inSampleSize=size;
        //不只解析边框
        options.inJustDecodeBounds=false;
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        return bitmap;
    }

    /**
     *  压缩图片
     */

    public static Bitmap compressBitmapSize(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int position=100;
        bitmap.compress(Bitmap.CompressFormat.JPEG,position,baos);
        while (bitmap.getByteCount()/1024>200){
            position-=10;
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG,position,baos);
        }
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length);
        return bitmap1;
    }

}
