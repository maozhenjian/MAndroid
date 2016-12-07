package mzj.mandroid.ui.android.system.pic;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import mzj.mandroid.ui.android.network.volley.base.NetCenter;
import mzj.mandroid.utils.ToastUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by mzj on 16/11/28.
 */
public class UpLoadImgFrg extends Fragment {

    private static final int INTENT_ACTION_PICTURE = 0;//打开相册并截图
    private static final int INTENT_ACTION_CAREMA = 1;// 打开相机照相
    private static final int INTENT_ACTION_CROP = 2;//照相后截图

    private static final int CAMERA_JAVA_REQUEST_CODE = 99;
    private static final int SDCARD_JAVA_REQUEST_CODE = 88;

    protected ProgressDialog progressDialog;
    private String uploadUrl = NetCenter.UPLOAD_BASE_URL + "/submit/submit/";//图片的地址
    private static final String PICTURE_NAME = "userIcon.jpg";

    private UpdateUIRunnable updateUIRunnable;//用于图片的上传
    Handler handler = new Handler();
    private String picturePath;//照片的本地位置
    private String netPath;//照片的网络位置
    private boolean isHeadImg = false;

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("utf-8");
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPath();
    }

    private void initPath() {
        //初始化照片保存地址
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String thumPicture = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getActivity().getPackageName() + "/download";
            File pictureParent = new File(thumPicture);
            File pictureFile = new File(pictureParent, PICTURE_NAME);

            if (!pictureParent.exists()) {
                if (pictureParent.mkdirs())
                    Log.i("TAG", "创建父目录成功！");
            }
            try {
                if (!pictureFile.exists()) {
                    if (pictureParent.createNewFile()) {
                        Log.i("TAG", "文件创建成功！");
                    } else {
                        Log.i("TAG", "文件已存在！");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            picturePath = pictureFile.getAbsolutePath();
            Log.i("picturePath ==>>", picturePath);
        } else {
            Log.i("change user icon ==>>", "there is not sdcard!");
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case INTENT_ACTION_PICTURE:
                    Cursor c = getActivity().getContentResolver().query(data.getData(), null, null, null, null);
                    if (c != null) {
                        c.moveToNext();
                        String path = c.getString(c.getColumnIndex(MediaStore.MediaColumns.DATA));
                        c.close();
                        if (new File(path).exists()) {
                            File file = new File(path);
                            openCrop(Uri.fromFile(file));
                        }
                    } else {
                        //如果选取的图片为云图片会因为图片格式不支持而报错
                        ToastUtil.showShortToast(getActivity(), "图片地址不正确,请选择本地图片");
                        Log.e("TAG", "图片地址不正确");

                    }

                    break;
                case INTENT_ACTION_CAREMA://
                {
                    File file = new File(picturePath);
                    openCrop(Uri.fromFile(file));

                }
                break;
                case INTENT_ACTION_CROP: {

                    handleImage(data, isHeadImg);
                }
                break;

                default:
                    break;
            }// end switch
        }
//        else if (resultCode == Activity.RESULT_CANCELED) {
        //finish();////
//        }
    }


    /**
     * 对根据来源图片进行
     *
     * @param data   intent
     * @param isHead 是否为设置图像(小图片)
     */
    private void handleImage(Intent data, boolean isHead) {
        if (picturePath == null) {
            ToastUtil.showShortToast(getActivity(), "找不到图片");
            return;
        }
        progressDialog = ProgressDialog.show(getActivity(), "上传中", "请稍后");
        //保持真确的文件格式
        picturePath = picturePath.replace("jpg", "png");
        final File file = new File(picturePath);
        Bitmap photo = null;
        //头像就用bitmap,格式,小图不会导致系统崩溃
        if (isHead) {
            Bundle extras = data.getExtras();
            Uri uri = data.getData();
            if (extras != null) {
                photo = extras.getParcelable("data");
            } else if (uri != null) {
                photo = getBitmapFromUri(uri);
            }
        } else {
            photo = getBitmapFromUri(Uri.fromFile(file));
        }

        if (photo != null) {
            saveBitmapToPNG(photo, picturePath);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        uploadFileByOkHttp(uploadUrl, file);
                    } catch (Exception e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
            }).start();
        }

    }


    /**
     * 使用OkHttp上传图片
     *
     * @param uploadUrl 图片上传地址
     * @param file      图片文件
     * @throws Exception
     */
    private void uploadFileByOkHttp(String uploadUrl, File file) throws Exception {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        builder.addFormDataPart(NetCenter.UPFILE_NAME, file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));

        MultipartBody requestBody = builder.build();
        //构建请求
        Request request = new Request.Builder()
                .url(uploadUrl)//地址
                .post(requestBody)//添加请求体
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject json = new JSONObject(s);
                    if (json.getInt("code") == 0) {
                        //该地址即为图片的URL
                        String filePath = json.getString("file_path");
                        Log.e("TAG", filePath);
                        netPath = filePath;
                        //对文件地址进行赋值
                        updateUIRunnable = new UpdateUIRunnable(2);
                    } else {
                        updateUIRunnable = new UpdateUIRunnable(-1);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    handler.post(updateUIRunnable);
                }

            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "上传失败:e.getLocalizedMessage() = " + e.getLocalizedMessage());
                updateUIRunnable = new UpdateUIRunnable(-1);
                handler.post(updateUIRunnable);


            }


        });
    }


    /**
     * 从相册获取图片
     * 6.0需要SD卡写入权限
     */
    public void getPicture(boolean isHeadImg) {
        this.isHeadImg = isHeadImg;
        //获取SD卡写入权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 判断是否有权限
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //申请权限
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, SDCARD_JAVA_REQUEST_CODE);
            } else {
                //已有权限执行操作
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, INTENT_ACTION_PICTURE);
            }

        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, INTENT_ACTION_PICTURE);
        }

    }

    /**
     * 打开相机照相
     * 6.0需要相机权限
     */
    public void openCamera(boolean isHeadImg) {
        this.isHeadImg = isHeadImg;
        //检查并获取相机权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, CAMERA_JAVA_REQUEST_CODE);
            } else {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(picturePath)));
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, INTENT_ACTION_CAREMA);
            }
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(picturePath)));
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent, INTENT_ACTION_CAREMA);
        }
    }

    /**
     * 剪裁方法
     */
    private void openCrop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");//可裁剪
        if (isHeadImg) {
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 100);
            intent.putExtra("outputY", 100);
        } else {
            intent.putExtra("aspectX", 5);
            intent.putExtra("aspectY", 4);
            intent.putExtra("outputX", 800);
            intent.putExtra("outputY", 640);
        }
        intent.putExtra("scale", true);
        if (isHeadImg) {
            intent.putExtra("return-data", true);//若为false则表示不返回数据
        } else {
            //截取高清大图要用uri,不能用bitmap
            File file = new File(picturePath);
            uri = Uri.fromFile(file);//截取后输出的目标地址,重新复制下,上面的uri是输入图片的地址
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("return-data", false);//若为false则表示不返回数据
        }

        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, INTENT_ACTION_CROP);
    }


    //    对图片进行压缩
    static boolean saveBitmapToPNG(Bitmap bmp, String filename) {
        Bitmap.CompressFormat format = Bitmap.CompressFormat.PNG;
        int quality = 100;
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bmp.compress(format, quality, stream);
    }


    class UpdateUIRunnable implements Runnable {
        private int choose;//1 id 2  head ; 3 error

        UpdateUIRunnable(int choose) {
            this.choose = choose;
        }

        @Override
        public void run() {
            if (choose == -1) {
                progressDialog.dismiss();
                ToastUtil.showShortToast(getActivity(), "图片上传失败");
            } else if (choose == 2) {
                //图片上传成功
//                BusProvider.getInstance().post(new SelectPictureEvt(picturePath, netPath));
            } else if (choose == 3) {
                ToastUtil.showShortToast(getActivity(), "图片上传失败");
            } else if (choose == 4) {
                ToastUtil.showShortToast(getActivity(), "图片上传失败（网络原因）");
            }
            progressDialog.dismiss();
        }
    }


    //通过uri获取Bitmap图像
    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            // 读取uri所在的图片
            return MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
        } catch (Exception e) {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Intent intent;
        switch (requestCode) {
            case CAMERA_JAVA_REQUEST_CODE:

                if (permissions[0].equals(Manifest.permission.CAMERA)
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户同意使用write
                    //App.sdCardWritePromission = true;
                    ToastUtil.showShortToast(getActivity(), "照相机权限获取成功");
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(picturePath)));
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                    startActivityForResult(intent, INTENT_ACTION_CAREMA);
                } else {
                    //用户不同意，向用户展示该权限作用
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                                .setMessage("需要去设置里存储写入权限，不开启将无法正常工作！")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).create();
                        dialog.show();
                        return;
                    }

                    //用户不同意，向用户展示该权限作用
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                                .setMessage("需要去设置里开启相机，不开启将无法正常工作！")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //finish();
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //finish();
                                    }
                                }).create();
                        dialog.show();
                        return;
                    }
                    //finish();

                }


                break;
            case SDCARD_JAVA_REQUEST_CODE:
                if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    App.sdCardWritePromission = true;
                    ToastUtil.showShortToast(getActivity(), "SD卡写入权限获取成功");
                    intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, INTENT_ACTION_PICTURE);

                } else {
                    //用户不同意，向用户展示该权限作用
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                                .setMessage("需要去设置里存储写入权限，不开启将无法正常工作！")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //finish();
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //finish();
                                    }
                                }).create();
                        dialog.show();
                        return;
                    }
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
