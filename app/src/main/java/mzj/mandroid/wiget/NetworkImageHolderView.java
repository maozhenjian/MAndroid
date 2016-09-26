package mzj.mandroid.wiget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

/**
 * Created by 振坚 on 2016/7/31.
 */
public class NetworkImageHolderView implements Holder<String> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        /**CENTER /center  按图片的原来size居中显示，当图片长/宽超过View的长/宽，则截取图片的居中部分显示

         CENTER_CROP / centerCrop  按比例扩大图片的size居中显示，使得图片长(宽)等于或大于View的长(宽)

         CENTER_INSIDE / centerInside  将图片的内容完整居中显示，通过按比例缩小或原来的size使得图片长/宽等于或小于View的长/宽

         FIT_CENTER / fitCenter  把图片按比例扩大/缩小到View的宽度，居中显示

         FIT_END / fitEnd   把图片按比例扩大/缩小到View的宽度，显示在View的下部分位置

         FIT_START / fitStart  把图片按比例扩大/缩小到View的宽度，显示在View的上部分位置

         FIT_XY / fitXY  把图片不按比例扩大/缩小到View的大小显示

         MATRIX / matrix 用矩阵来绘制*/
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String url) {
//        imageView.setImageResource(R.mipmap.ic_launcher);
        //若使用ImageLoader需现在Application中进行初始化
        ImageSize imageSize     =new ImageSize(300,300);
        ImageLoader.getInstance().displayImage(url, imageView,imageSize);
    }
}
