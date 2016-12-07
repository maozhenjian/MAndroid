package mzj.mandroid.ui.android.normal.permission;

import android.Manifest;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActEasyPermissionBinding;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * creat by mzj on 2016/11/7 10:18
 * <p>
 * https://github.com/googlesamples/easypermissions  官方github
 */

public class EasyPerimissionAct extends BaseActivity<ActEasyPermissionBinding> implements EasyPermissions.PermissionCallbacks {
    private int RC_CAMERA_AND_WIFI = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.act_easy_permission;
    }

    @Override
    protected void initData() {
        binding.permissionBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();

            }
        });
    }

    /**
     * 1 检查权限
     */

    private void checkPermission() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.CHANGE_WIFI_STATE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            //...

        } else {
            //...
            EasyPermissions.requestPermissions(this, "拍照需要摄像头权限",
                    RC_CAMERA_AND_WIFI, perms);
        }
    }


    /**
     * 3 实现EasyPermissions.PermissionCallbacks接口，直接处理权限是否成功申请
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @Nullable String[] permissions,@Nullable int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //成功
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    //失败
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
