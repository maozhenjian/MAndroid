package mzj.mandroid.ui.android.normal.databinding.model;

import android.view.View;

import mzj.mandroid.ui.android.normal.databinding.DataBindingFirstAct;


/**
 * Created by 振坚 on 2016/8/2.
 * 事件监听
 */
public class ClickBean  {

    DataBindingFirstAct activity;

    public ClickBean(DataBindingFirstAct activity) {
        this.activity = activity;
    }

    //调用的点击事件
    public void changeAge(View view) {
        if (activity != null && activity.userBean != null) {
            activity.numBean.setPhone((int)(Math.random()*50));
        }
    }

}
