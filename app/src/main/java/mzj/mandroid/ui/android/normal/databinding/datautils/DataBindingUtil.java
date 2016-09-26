package mzj.mandroid.ui.android.normal.databinding.datautils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

/**
 * Created by 振坚 on 2016/8/2.
 */
public class DataBindingUtil {
    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String imageUrl) {
        ImageSize imageSize=new ImageSize(200,200);
        ImageLoader.getInstance().displayImage(imageUrl, view,imageSize);
    }

    public static String whatName(String index) {
        String realsex="";
        if (index.equals("0")){
            realsex="杰尼龟";
        }else if (index.equals("1")){
            realsex="小火龙";
        }else if (index.equals("2")){
            realsex="妙蛙草";
        }
        return realsex;
    }


}
