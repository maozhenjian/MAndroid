package mzj.mandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import mzj.mandroid.base.App;


/**
 * Created by 振坚 on 2016/6/24.
 */
public class DeviceUtil {

    /**
     * 得到手机的密度
     * 0dpi ~ 120dpi	ldpi
     * 120dpi ~ 160dpi	mdpi
     * 160dpi ~ 240dpi	hdpi
     * 240dpi ~ 320dpi	xhdpi
     * 320dpi ~ 480dpi	xxhdpi
     * 480dpi ~ 640dpi	xxxhdpi
     **/
    void getDisplayMetrics(Context context){
        float xdpi = context.getResources().getDisplayMetrics().xdpi;
        float ydpi = context.getResources().getDisplayMetrics().ydpi;
    }




    /**
     * 获得屏幕的宽
     */
    public static int getScreenWidth() {
        // 方法1 Android获得屏幕的宽和高
        WindowManager wm = (WindowManager) App.getApplication().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        return screenWidth;
    }

    /**
     * 获得屏幕的高
     */
    public static int getScreenHeight() {
        // 方法1 Android获得屏幕的宽和高
        WindowManager wm = (WindowManager) App.getApplication().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenHeight = size.y;
        return screenHeight;
    }

    /**
     * 应用程序最高可用内存是多少。
     */
    public static int getMaxMemory() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.e("TAG", "(该应用程序最高可用内存)Max memory is " + maxMemory + "KB");
        return maxMemory;
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Activity context) {
        Rect frame = new Rect();
        context .getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        return statusBarHeight;
    }



    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return "当前版本： " + version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


}
