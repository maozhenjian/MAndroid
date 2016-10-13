package mzj.mandroid.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


/**
 * Created by 振坚 on 2016/8/1.
 */
public class BitmapUtil {


    /**
     * 获取Bitmap图片大小
     * @param bitmap
     * @return
     */
    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {     //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//API 12
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }


    /**
     * drawable转Bitamp
     *
     *     Bitmap---->Drawable
     * Drawable drawable =new BitmapDrawable(bmp);
     * @param drawable
     * @return
     */

    public static Bitmap drawableToBitamp(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();// 取drawable的长宽
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;// 取drawable的颜色格式
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);// 建立对应bitmap
        Canvas canvas = new Canvas(bitmap);// 建立对应bitmap的画布
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);// 把drawable内容画到画布中
        return bitmap;
    }

    /**
     * 将图片高宽和的大小kB压缩
     */
    public  static Bitmap changeBitmap(Bitmap rawBitmap,int times) {
        //得到图片原始的高宽
       final int rawHeight = rawBitmap.getHeight();
        final int rawWidth = rawBitmap.getWidth();


        // 设定图片新的高宽
        int newHeight = rawHeight/times;
        int newWidth = rawHeight/times;
        // 计算缩放因子
        float heightScale = ((float) newHeight) / rawHeight;
        float widthScale = ((float) newWidth) / rawWidth;
        // 新建立矩阵
        Matrix matrix = new Matrix();
        matrix.postScale(heightScale, widthScale);
        // 设置图片的旋转角度
        // matrix.postRotate(-30);
        // 设置图片的倾斜
        // matrix.postSkew(0.1f, 0.1f);
        // 将图片大小压缩
        // 压缩后图片的宽和高以及kB大小均会变化
        Bitmap newBitmap = Bitmap.createBitmap(rawBitmap, 0, 0, rawWidth, rawHeight, matrix, true);
        return newBitmap;
    }



    public static Bitmap getImage(String srcPath) {

        BitmapFactory.Options options = new BitmapFactory.Options();

//开始读入图片，此时把options.inJustDecodeBounds 设回true了

        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(srcPath, options); //此时返回bm为空

        options.inJustDecodeBounds = false;

        int w = options.outWidth;

        int h = options.outHeight;

//现在主流手机比较多是800*480分辨率，所以高和宽我们设置为

        float hh = 800f;//这里设置高度为800f

        float ww = 480f;//这里设置宽度为480f

//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可

        int be = 1;//be=1表示不缩放

        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放

            be = (int) (options.outWidth / ww);

        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放

            be = (int) (options.outHeight / hh);

        }

        if (be <= 0)

            be = 1;

        options.inSampleSize = be;//设置缩放比例

        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了

       Bitmap bitmap = BitmapFactory.decodeFile(srcPath, options);

        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩

    }


    /**
     * 一、质量压缩法：
     *
     * @param bitmap
     * @return
     */
    public static Bitmap compressImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中

        int options = 100;

        while (byteArrayOutputStream.toByteArray().length / 1024 > 100) {//循环判断如果压缩后图片是否大于100kb,大于继续压缩

            byteArrayOutputStream.reset();//重置baos即清空baos

            options -= 10;  //每次都减少10

            bitmap.compress(Bitmap.CompressFormat.JPEG, options, byteArrayOutputStream);//这里压缩options%，把压缩后的数据存放到baos中

        }

        return bitmap;
    }


    private Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出

            baos.reset();//重置baos即清空baos

            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中

        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());

        BitmapFactory.Options newOpts = new BitmapFactory.Options();

//开始读入图片，此时把options.inJustDecodeBounds 设回true了

        newOpts.inJustDecodeBounds = true;

        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

        newOpts.inJustDecodeBounds = false;

        int w = newOpts.outWidth;

        int h = newOpts.outHeight;

        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为

        float hh = 800f;//这里设置高度为800f

        float ww = 480f;//这里设置宽度为480f

//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可

        int be = 1;//be=1表示不缩放

        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放

            be = (int) (newOpts.outWidth / ww);

        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放

            be = (int) (newOpts.outHeight / hh);

        }

        if (be <= 0)

            be = 1;

        newOpts.inSampleSize = be;//设置缩放比例

        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;//降低图片从ARGB888到RGB565

//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了

        isBm = new ByteArrayInputStream(baos.toByteArray());

        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩

    }


}


