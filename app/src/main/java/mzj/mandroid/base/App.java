package mzj.mandroid.base;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import mzj.mandroid.R;


/**
 *
 */
public class App extends Application{
    private static App mInstance = null;
    public static synchronized App getApplication() {
        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initImageLoader();
    }
    //配置imageLoader------------------------------------
    void initImageLoader() {
        //创建默认的ImageLoader配置参数
//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
//                .createDefault(this);

        DisplayImageOptions defaultOoptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_loading)
                .showImageOnFail(R.mipmap.ic_loadingfail)
                // 设置下载的图片是否缓存在内存中
                .cacheInMemory(true)//启用内存缓存
                .cacheOnDisk(true)//启用外部缓存
                // 在DisplayImageOptions选项中配置bitmapConfig为Bitmap.Config.RGB_565，
                //  因为默认是ARGB_8888， 使用RGB_565会比使用ARGB_8888少消耗2倍的内存
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(1000))// 图片加载好后渐入的动画时间

                /**
                 *   缩放类型mageScaleType:
                 EXACTLY :图像将完全按比例缩小的目标大小
                 EXACTLY_STRETCHED:图片会缩放到目标大小完全
                 IN_SAMPLE_INT:图像将被二次采样的整数倍
                 IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小
                 NONE:图片不会调整
                 * */
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .build();


        ImageLoaderConfiguration configuration =
                new ImageLoaderConfiguration.Builder(this)
                        .threadPriority(Thread.NORM_PRIORITY - 2)//线程池内加载的数量
                        .denyCacheImageMultipleSizesInMemory()
                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                        .imageDecoder(new BaseImageDecoder(true))
                        .defaultDisplayImageOptions(defaultOoptions)
                        .imageDownloader(new BaseImageDownloader(mInstance)) // default

//                        .writeDebugLogs() //打印log信息
                        .build();
        ImageLoader.getInstance().init(configuration);
    }

}
