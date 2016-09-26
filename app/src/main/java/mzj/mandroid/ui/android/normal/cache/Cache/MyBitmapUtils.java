package mzj.mandroid.ui.android.normal.cache.Cache;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import mzj.mandroid.R;


/**
 * Created by 振坚 on 2016/8/3.
 * 自定义的BitmapUtils,实现三级缓存
 */
public class MyBitmapUtils {

    private NetCacheUtils mNetCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public MyBitmapUtils(){
        mMemoryCacheUtils=new MemoryCacheUtils();
        mNetCacheUtils=new NetCacheUtils(mMemoryCacheUtils);
    }

    public void disPlay(ImageView ivPic, String url) {
        ivPic.setImageResource(R.mipmap.bg_float);

        Bitmap bitmap;
        //内存缓存
        bitmap=mMemoryCacheUtils.getBitmapFromMemory(url);
        if (bitmap!=null){
            ivPic.setImageBitmap(bitmap);
            Log.i("TAG","从内存获取图片啦.....");
            return;
        }
//
//        //本地缓存
//        bitmap = mLocalCacheUtils.getBitmapFromLocal(url);
//        if(bitmap !=null){
//            ivPic.setImageBitmap(bitmap);
//            Log.i("TAG","从本地获取图片啦.....");
//            //从本地获取图片后,保存至内存中
//            mMemoryCacheUtils.setBitmapToMemory(url,bitmap);
//            return;
//        }
        //网络缓存
        mNetCacheUtils.getBitmapFromNet(ivPic,url);
    }
}