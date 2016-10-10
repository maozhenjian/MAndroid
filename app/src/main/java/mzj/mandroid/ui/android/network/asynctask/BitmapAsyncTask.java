package mzj.mandroid.ui.android.network.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * creat by mzj on 2016/9/27 10:34
 *
 *
 */

public class BitmapAsyncTask extends AsyncTask<String,Integer,Bitmap> {

    ImageView img;

    public BitmapAsyncTask(ImageView img) {
        this.img = img;
    }

    //首先执行，用于做准备工作
    // 该方法执行在主线程当中
    @Override
    protected void onPreExecute() {
        Log.e("onPreExecute","onPreExecute");
        Log.e("onPreExecute当前线程：",Thread.currentThread().getName());
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        publishProgress(3);

        Log.e("doInBackground当前线程：",Thread.currentThread().getName());
        return   getBitmapFromURL(params[0]);
    }

    //更新操作，主线程
    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.e("onProgressUpdate：",values[0]+"");
        Log.e("onProgressUpdate当前线程：",Thread.currentThread().getName());
    }


    //最后执行，在主线程中执行
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        Log.e("onPostExecute", bitmap.toString());
        Log.e("onPostExecute当前线程：",Thread.currentThread().getName());
        img.setImageBitmap(bitmap);
    }





    //从网络获取图片
    private Bitmap getBitmapFromURL(String urlString){
        Bitmap bitmap;
        InputStream is=null;
        try {
            URL url=new URL(urlString);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            is=new BufferedInputStream(connection.getInputStream());
            bitmap= BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (is!=null)
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}