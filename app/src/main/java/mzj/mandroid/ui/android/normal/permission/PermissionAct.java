package mzj.mandroid.ui.android.normal.permission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActPermissionBinding;
import mzj.mandroid.databinding.CommentTitleListBinding;
import mzj.mandroid.utils.ToastUtil;

/**
 * creat by mzj on 2016/10/31 11:32
 *
 * http://blog.csdn.net/kong_gu_you_lan/article/details/52488097 《Android 6.0运行时权限详解》  容华谢后
 */

public class PermissionAct extends BaseActivity<ActPermissionBinding> {
    private final int CAMERA_REQUEST_CODE = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.act_permission;
    }

    @Override
    protected void initData() {
        binding.permissionBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
    }

    private void requestPermission() {
//        1>首先判断当前应用有没有CAMERA权限，如果没有则进行申请。
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {

            // 第一次请求权限时，用户如果拒绝，下一次请求shouldShowRequestPermissionRationale() 返回true
            // 向用户解释为什么需要这个权限
            Log.e("TAG",ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)+"");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

                new AlertDialog.Builder(this)
                        .setMessage("申请相机权限")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //申请相机权限
                                ActivityCompat.requestPermissions(PermissionAct.this,
                                        new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                            }
                        })
                        .show();

            } else {
                //申请相机权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            }
        } else {
            binding.permissionTv.setTextColor(Color.GREEN);
            binding.permissionTv.setText("相机权限已申请");
        }
    }

    //    此方法是权限申请的回调方法，在此方法中处理权限申请成功或失败后的操作。
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
//            1>因为可以同时申请多个权限，所以回调的结果是以数组方式返回的，如果用户点击允许的话，此判断为true，可以在里面处理打开摄像头的操作。
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                binding.permissionTv.setTextColor(Color.GREEN);
                binding.permissionTv.setText("相机权限已申请");
            } else {

/**
 2>当多次（两次或两次以上）请求操作时，会有不再提示的选择框，
 如果用户选择了不再提示，shouldShowRequestPermissionRationale为false，在此判断中提示用户权限已被禁止，需要在应用管理中自行打开。
 用户勾选了不再询问
 提示用户手动打开权限

 * shouldShowRequestPermissionRationale() 默认返回 false。
 第一次请求权限时，如果用户拒绝了，再次请求时 shouldShowRequestPermissionRationale() 返回 true。
 多次请求权限（超过一次），用户如果选择了不再提醒并拒绝，shouldShowRequestPermissionRationale() 返回 false。
 设备的策略禁止当前应用获取这个权限的授权，shouldShowRequestPermissionRationale() 返回 false。
 *
 * */
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    ToastUtil.showShortToast(mContext, "相机权限已被禁止");
                }
            }
        }
    }

}
