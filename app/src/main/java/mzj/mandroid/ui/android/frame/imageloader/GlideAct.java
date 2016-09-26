package mzj.mandroid.ui.android.frame.imageloader;

import android.graphics.Bitmap;
import android.net.Uri;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActGlideBinding;

/**
 * creat by mzj on 2016/9/26 11:55
 * http://www.cnblogs.com/guilin-hu/p/5706916.html
 * http://blog.csdn.net/superbigcupid/article/details/51800194
 *
 * http://mrfu.me/2016/02/28/Glide_Series_Roundup/?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io   《Glide-系列综述》
 */

public class GlideAct extends BaseActivity<ActGlideBinding> {
    String imageUrl="http://g.hiphotos.baidu.com/zhidao/pic/item/37d3d539b6003af30404f193332ac65c1138b682.jpg";
    @Override
    protected int getLayoutId() {
        return R.layout.act_glide;
    }

    @Override
    protected void initData() {
//        ImageLoader.getInstance().displayImage(imageUrl,binding.imageIv2);
        Glide.with(mContext)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)  //下载等待时显示的图片
                .error(R.mipmap.ic_launcher)        //下载失败时显示的图片
//                .override(300,300)                 //裁剪
                .skipMemoryCache(true)                //是否跳过内存缓存
                //       DiskCacheStrategy.NONE 什么都不缓存
//        DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像
//        DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
//        DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
                .diskCacheStrategy(DiskCacheStrategy.SOURCE) //缓存策略
//                .centerCrop()                      //设置图片缩放centerCrop()和fitCenter()：
//                .priority( Priority.LOW )             //设置优先级
                .into(binding.imageIv);

//      5.显示gif动画,asGif()判断是否是gif动画
        Glide.with(mContext).load("http://i.kinja-img.com/gawker-media/image/upload/s--B7tUiM5l--/gf2r69yorbdesguga10i.gif").asGif().into(binding.imageIv2);

//        6.显示本地视频
//        String filePath = "/storage/emulated/0/Pictures/example_video.mp4";
//        Glide.with( mContext ).load( Uri.fromFile( new File( filePath ) ) ).into(imageViewGifAsBitmap );




    }


    private void getBitmap(){
        SimpleTarget target = new SimpleTarget<Bitmap>(300,600) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

            }
        };
        Glide.with(mContext)
                .load(imageUrl)
                .asBitmap()
                .into(target);
    }
}
