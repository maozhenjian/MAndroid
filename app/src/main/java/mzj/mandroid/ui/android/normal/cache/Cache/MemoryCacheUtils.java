package mzj.mandroid.ui.android.normal.cache.Cache;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * Created by 振坚 on 2016/8/3.
 *
 * 三级缓存之内存缓存
 */

public class MemoryCacheUtils  {

    // private HashMap<String,Bitmap> mMemoryCache=new HashMap<>();//1.因为强引用,容易造成内存溢出，所以考虑使用下面弱引用的方法
    // private HashMap<String, SoftReference<Bitmap>> mMemoryCache = new HashMap<>();//2.因为在Android2.3+后,系统会优先考虑回收弱引用对象,官方提出使用LruCache
    private LruCache<String,Bitmap> mMemoryCache;

    public MemoryCacheUtils(){
        //得到手机最大允许内存的1/8,即超过指定内存,则开始回收
        long maxMemory = Runtime.getRuntime().maxMemory()/4;
        //需要传入允许的内存最大值,虚拟机默认内存16M,真机不一定相同
        mMemoryCache=new LruCache<String,Bitmap>((int) maxMemory){
            //用于计算每个条目的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int byteCount = value.getByteCount();
                return byteCount;
            }
        };

    }

    /**
     * 从内存中读图片
     * @param url
     */
    public Bitmap getBitmapFromMemory(String url) {
        //Bitmap bitmap = mMemoryCache.get(url);//1.强引用方法
            /*2.弱引用方法
            SoftReference<Bitmap> bitmapSoftReference = mMemoryCache.get(url);
            if (bitmapSoftReference != null) {
                Bitmap bitmap = bitmapSoftReference.get();
                return bitmap;
            }
            */

        Bitmap bitmap = mMemoryCache.get(url);
        Log.i("TAG","从内存中读图片....."+bitmap);
        return bitmap;

    }

    /**
     * 往内存中写图片
     * @param url
     * @param bitmap
     */
    public void setBitmapToMemory(String url, Bitmap bitmap) {
        //mMemoryCache.put(url, bitmap);//1.强引用方法
            /*2.弱引用方法
            mMemoryCache.put(url, new SoftReference<>(bitmap));
            */
        Log.i("TAG","setBitmapToMemory::往内存中写图片"+bitmap);
        if (getBitmapFromMemory(url) == null) {
            mMemoryCache.put(url, bitmap);
            Log.i("TAG","mMemoryCache.get(url);"+mMemoryCache.get(url));
        }
    }
}