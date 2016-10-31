package mzj.mandroid.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by wzh on 2016/6/4.
 * 处理提示时间重复问题
 */
public class ToastUtil {

    private static Toast mToast;

    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
            mToast=null;//toast隐藏后，将其置为null
        }
    };

    public static void showShortToast(Context context, String message) {
        mHandler.removeCallbacks(r);
        if (mToast == null){//只有mToast==null时才重新创建，否则只需更改提示文字
            mToast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(message);
        }
        mHandler.postDelayed(r, 1000);//延迟1秒隐藏toast
        mToast.show();
    }
    public static void showShortToast(Context context, int resId) {
        mHandler.removeCallbacks(r);
        if (mToast == null){//只有mToast==null时才重新创建，否则只需更改提示文字
            mToast = Toast.makeText(context,context.getString(resId),Toast.LENGTH_SHORT);
        }else{
            mToast.setText(context.getString(resId));
        }
        mHandler.postDelayed(r, 1000);//延迟1秒隐藏toast
        mToast.show();
    }

}
