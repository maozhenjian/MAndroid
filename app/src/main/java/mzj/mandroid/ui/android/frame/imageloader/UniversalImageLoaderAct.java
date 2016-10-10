package mzj.mandroid.ui.android.frame.imageloader;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActUniversalBinding;

/**
 * creat by mzj on 2016/9/26 10:53
 * 一：优秀文章：
 * http://blog.csdn.net/xiaanming/article/details/26810303/
 * http://blog.csdn.net/wei18359100306/article/details/41920677
 *
 * <p>特点：
 * 1.多线程下载图片，图片可以来源于网络，文件系统，项目文件夹assets中以及drawable中等
 * 2.支持随意的配置ImageLoader，例如线程池，图片下载器，内存缓存策略，硬盘缓存策略，图片显示选项以及其他的一些配置
 * 3.支持图片的内存缓存，文件系统缓存或者SD卡缓存
 * 4.支持图片下载过程的监听
 * 5.根据控件(ImageView)的大小对Bitmap进行裁剪，减少Bitmap占用过多的内存
 * 6.较好的控制图片的加载过程，例如暂停图片加载，重新开始加载图片，一般使用在ListView,GridView中，滑动过程中暂停加载图片，停止滑动的时候去加载图片
 * 7.提供在较慢的网络下对图片进行加载
 * <p>
 * <p>
 * 二：提示和技巧
 * 1、  只有在你需要让Image的尺寸比当前设备的尺寸大的时候，你才需要配置maxImageWidthForMemoryCach(...)和
 * maxImageHeightForMemoryCache(...)这两个参数，比如放大图片的时候。其他情况下，不需要做这些配置，因为默
 * 认的配置会根据屏幕尺寸以最节约内存的方式处理Bitmap。
 * <p>
 * 2、   在设置中配置线程池的大小是非常明智的。一个大的线程池会允许多条线程同时工作，但是也会显著的影响到UI
 * 线程的速度。但是可以通过设置一个较低的优先级来解决：当ImageLoader在使用的时候，可以降低它的优先级，这
 * 样UI线程会更加流畅。在使用List的时候，UI 线程经常会不太流畅，所以在你的程序中最好设置threadPoolSize(...)和
 * threadPriority(...)这两个参数来优化你的应用。
 * <p>
 * 3、   memoryCache(...)和memoryCacheSize(...)这两个参数会互相覆盖，所以在ImageLoaderConfiguration中使用一个就好了
 * <p>
 * 4、   diskCacheSize(...)、diskCache(...)和diskCacheFileCount(...)这三个参数会互相覆盖，只使用一个
 *
 * 5、   如果你的程序中使用displayImage（）方法时传入的参数经常是一样的，那么一个合理的解决方法是，把这些选项
 *       配置在ImageLoader的设置中作为默认的选项（通过调用defaultDisplayImageOptions(...)方法）。之后调用
 *       displayImage(...)方法的时候就不必再指定这些选项了，如果这些选项没有明确的指定给
 *       defaultDisplayImageOptions(...)方法，那调用的时候将会调用UIL的默认设置。
 */

public class UniversalImageLoaderAct extends BaseActivity<ActUniversalBinding> {
    private String imageUrl="http://d.hiphotos.baidu.com/zhidao/pic/item/d1160924ab18972b5702626be0cd7b899e510a52.jpg";
    @Override
    protected int getLayoutId() {
        return R.layout.act_universal;
    }

    @Override
    protected void initData() {
//        显示图片
      	ImageLoader.getInstance().displayImage(imageUrl, binding.imageIv, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                Log.e("TAG","onLoadingStarted");

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Log.e("TAG","onLoadingFailed");
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Log.e("TAG","onLoadingComplete");
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                Log.e("TAG","onLoadingCancelled");
            }
        });
//        1  ImageLoader.getInstance().displayImage(uri, imageView, options);
//        2、	ImageLoader.getInstance().displayImage(uri, imageView, options);
//        3、	ImageLoader.getInstance().displayImage(uri, imageView, listener);
//        4、	ImageLoader.getInstance().displayImage(uri, imageView, options, listener);
//        5、	ImageLoader.getInstance().displayImage(uri, imageView, options, listener, progressListener);
    }

    /**
     *默认的配置
     */
    private void initImageloaderDefault(){
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);

        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);
        ImageLoader.getInstance().displayImage(imageUrl,binding.imageIv);
    }

    /**
     *
     * 自定义配置（一般设置在Applicaiton，全局使用）
     *
     * ImageLoaderConfiguration是图片加载器ImageLoader的配置参数，使用了建造者模式，这里是直接使用了createDefault()方法创建一个默认的ImageLoaderConfiguration，
     * 当然我们还可以自己设置ImageLoaderConfiguration，设置如下
     * 在application中全局设置
     */
    private void creatImageLoader(){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
//                .taskExecutor(...)
//                .taskExecutorForCachedImages(...)
                 .threadPoolSize(3) // default  线程池内加载的数量  注意：减少配置的线程池的大小(.threadPoolSize(...))，建议1~5
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()

                /**
                 * Universal-Image-Loader内存缓存策略
                 * 1. 只使用的是强引用缓存
                 * LruMemoryCache（这个类就是这个开源框架默认的内存缓存类，缓存的是bitmap的强引用）
                 * 2.使用强引用和弱引用相结合的缓存有
                 * UsingFreqLimitedMemoryCache（如果缓存的图片总量超过限定值，先删除使用频率最小的bitmap）
                 * LRULimitedMemoryCache（这个也是使用的lru算法，和LruMemoryCache不同的是，他缓存的是bitmap的弱引用）
                 * FIFOLimitedMemoryCache（先进先出的缓存策略，当超过设定值，先删除最先加入缓存的bitmap）
                 * LargestLimitedMemoryCache(当超过缓存限定值，先删除最大的bitmap对象)
                 * LimitedAgeMemoryCache（当 bitmap加入缓存中的时间超过我们设定的值，将其删除）
                 * 3.只使用弱引用缓存
                 * WeakMemoryCache（这个类缓存bitmap的总大小没有限制，唯一不足的地方就是不稳定，缓存的图片容易被回收掉）
                 */
                .memoryCache(new WeakMemoryCache()) //这里可以配置自己的缓存类

                .memoryCacheSize(2 * 1024 * 1024)   // 内存缓存的最大值
                .memoryCacheSizePercentage(13) // default
//                .diskCache(new UnlimitedDiscCache(cacheDir)) // default 可以自定义缓存路径
                .diskCacheSize(50 * 1024 * 1024) //  50 Mb sd卡(本地)缓存的最大值
                .diskCacheFileCount(100)  // 可以缓存的文件数量
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(mContext)) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()  /// 打印debug log ,在release 版本中移除
                .build();

        //配置图片的选项
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_loading) // resource or drawable   下载时显示的图片
//                .showImageForEmptyUri(R.drawable.ic_empty) // // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_loadingfail) // resource or drawable     下载失败显示的图片
                .resetViewBeforeLoading(false)  //default 设置图片在加载前是否重置、复位
                .delayBeforeLoading(0) // 下载前的延迟时间
                .cacheInMemory(false) // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(false)  // default  设置下载的图片是否缓存在SD卡中
        //      .preProcessor(...)
        //      .postProcessor(...)
        //      .extraForDownloader(...)
                .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 设置图片以如何的编码方式显示
                /**
                 *  图片的解码设置 bitmapConfig(Bitmap.Config.RGB_565) . RGB_565模式消耗的内存比ARGB_8888模式少两倍.
                 * */
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
//              .decodingOptions(...)
//              .displayer(new SimpleBitmapDisplayer()) // default
                .displayer(new FadeInBitmapDisplayer(100))// 图片加载好后渐入的动画时间
                .handler(new Handler()) // default
                .build();
    }


    /**
     * 如果个别图片需要单独设置
     */
    private void loadImage(){
        String imageUrl = "https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg";
        //个体设置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_loading)
                .showImageOnFail(R.mipmap.ic_loadingfail)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().displayImage(imageUrl, binding.imageIv, options);
    }

    /**
     * 除了网络图片，还可以加载本地图片
     */
    private void getLocalImage(){
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_loading)
                .showImageOnFail(R.mipmap.ic_loadingfail)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        //Sd卡
        String imagePath = "/mnt/sdcard/image.png";

        String imageUrl = ImageDownloader.Scheme.FILE.wrap(imagePath);

//      String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";


        //图片来源于Content provider
        String contentprividerUrl = "content://media/external/audio/albumart/1";

        //图片来源于assets
        String assetsUrl = ImageDownloader.Scheme.ASSETS.wrap("image.png");

        //图片来源于
        String drawableUrl = ImageDownloader.Scheme.DRAWABLE.wrap("R.mipmap.bg_home");


        ImageLoader.getInstance().displayImage(imageUrl, binding.imageIv, options);
    }



    public void onClearMemoryClick(View view) {
        ImageLoader.getInstance().clearMemoryCache();  // 清除内存缓存
    }
    public void onClearDiskClick(View view) {
        ImageLoader.getInstance().clearDiskCache();  // 清除本地缓存
    }

}
