package mzj.mandroid.ui.android.view.customview.baseview;


import android.view.View;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActBaseViewShowBinding;
import mzj.mandroid.ui.android.view.customview.baseview.view.RoundImageView;

/**
 * creat by mzj on 2016/10/31 14:36
 */

public class BaseViewShowAct extends BaseActivity<ActBaseViewShowBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.act_base_view_show;
    }

    @Override
    protected void initData() {
//        binding.circleView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.circleView  .startAnimation();
//            }
//        });



        binding.idQiqiu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                binding.idQiqiu.setType(RoundImageView.TYPE_ROUND);
            }
        });

        binding.idMeinv.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                binding.idMeinv.setBorderRadius(90);
            }
        });
    }
}
