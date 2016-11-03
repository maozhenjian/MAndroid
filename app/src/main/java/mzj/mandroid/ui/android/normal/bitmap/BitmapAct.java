package mzj.mandroid.ui.android.normal.bitmap;

import android.databinding.tool.util.L;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActBitmapBinding;
import mzj.mandroid.utils.BitmapUtil;

/**
 * creat by mzj on 2016/9/27 11:57
 */

public class BitmapAct extends BaseActivity<ActBitmapBinding> implements View.OnClickListener {
    private LruCache<String, Bitmap> mMemoryCache;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                  case 0:
                      Bitmap bitmap= (Bitmap) msg.obj;
                      Bitmap  bitmap2=bitmap;

//                      第一张图
                      binding.sizeTv1.setText("图片原始大小为："+BitmapUtil.getBitmapSize(bitmap)/1024+"KB");
                      binding.Ig.setImageBitmap(bitmap);

//                      第二张图
                      if (!bitmap.isMutable()){
                         bitmap2 = bitmap.copy(Bitmap.Config.RGB_565, true);
                      }
                      binding.sizeTv2.setText("图片修改为RGB_565后大小为："+BitmapUtil.getBitmapSize(bitmap2)/1024+"KB");
                      binding.Ig2.setImageBitmap(bitmap2);

//                      第三张图
                      Bitmap  bitmap3=BitmapUtil.changeBitmap(bitmap,2);
                      binding.Ig3.setImageBitmap(bitmap3);
                      binding.sizeTv3.setText("图片修改为RGB_565后大小为："+BitmapUtil.getBitmapSize(bitmap3)/1024+"KB");
                      Log.e("TAG","bitmap"+bitmap);
                      Log.e("TAG","bitmap3"+bitmap3);



                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.act_bitmap;
    }

    @Override
    protected void initData() {
        binding.loadLargeIv.setOnClickListener(this);
        // 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。
        // LruCache通过构造函数传入缓存值，以KB为单位。
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // 使用最大可用内存值的1/8作为缓存的大小。
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // 重写此方法来衡量每张图片的大小，默认返回1。
                return bitmap.getByteCount() / 1024;
            }
        };
    }


    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.load_large_iv:
                String url="http://h.hiphotos.baidu.com/zhidao/pic/item/35a85edf8db1cb13b61997f6df54564e93584be7.jpg";
                Bitmap bitmap = getBitmapFromMemCache(url);
                if ( bitmap!=null ){
                    Log.e("TAG",bitmap.toString());
                    binding.Ig3.setImageBitmap(bitmap);
                    Log.e("TAG","bitmap!=null");
                }else {
                    getBitmapFromURL(url);
                    Log.e("TAG","bitmap==null");
                }

                break;
        }
    }





    //从网络获取图片
    private void  getBitmapFromURL(final String urlString){

        new Thread(new Runnable() {
            @Override
            public void run() {

                InputStream is=null;
                try {
                    URL url=new URL(urlString);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    is=new BufferedInputStream(connection.getInputStream());
                    Bitmap bitmap1= BitmapFactory.decodeStream(is);

                    connection.disconnect();
                    addBitmapToMemoryCache(urlString,bitmap1);


                    Message message = Message.obtain();
                    message.what = 0;
                    message.obj = bitmap1;
                    handler.sendMessage(message);

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
            }
        }).start();

    }




    //设置图片圆角，返回设置后的BitMap
    public  Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap roundCornerBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(roundCornerBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        // 防止锯齿
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float roundPx = pixels;
        // 相当于清屏
        canvas.drawARGB(0, 0, 0, 0);
        // 先画了一个带圆角的矩形
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
        // 再把原来的bitmap画到现在的bitmap！！！注意这个理解
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return roundCornerBitmap;
    }

//    /**
//     *
//     * inJustDecodeBounds属性设置为true就可以让解析方法禁止为bitmap分配内存，返回值也不再是一个Bitmap对象，而是null。
//     * 虽然Bitmap是null了，但是BitmapFactory.Options的outWidth、outHeight和outMimeType属性都会被赋值。
//     * @param reqWidth
//     * @param reqHeight
//     * @return
//     */
//    public static Bitmap decodeSampledBitmapFromResource(InputStream is,
//                                                         int reqWidth, int reqHeight) {
//
//        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeStream(is, null, options);
//        // 调用上面定义的方法计算inSampleSize值
//        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//        // 使用获取到的inSampleSize值再次解析图片
//        options.inJustDecodeBounds = false;
//        return    BitmapFactory.decodeStream(is, null, options);
//    }
//
//    public static int calculateInSampleSize(BitmapFactory.Options options,
//                                            int reqWidth, int reqHeight) {
//        // 源图片的高度和宽度
//        final int height = options.outHeight;
//        final int width = options.outWidth;
//        int inSampleSize = 1;
//        if (height > reqHeight || width > reqWidth) {
//            // 计算出实际宽高和目标宽高的比率
//            final int heightRatio = Math.round((float) height / (float) reqHeight);
//            final int widthRatio = Math.round((float) width / (float) reqWidth);
//            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
//            // 一定都会大于等于目标的宽和高。
//            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
//        }
//        return inSampleSize;
//    }

}
