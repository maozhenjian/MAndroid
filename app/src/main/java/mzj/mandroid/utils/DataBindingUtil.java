package mzj.mandroid.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;


/**
 * Created by JRC on 16/4/15.
 * Databinding 的工具，主要用于处理界面上的一般问题
 */

//@SuppressWarnings (value={"unchecked"})
public class DataBindingUtil {
    @BindingAdapter({"imageUrl"})
    public static void
    loadImage(ImageView view, String imageUrl) {
//        ImageLoader.getInstance().displayImage(imageUrl,view);
    }
}

