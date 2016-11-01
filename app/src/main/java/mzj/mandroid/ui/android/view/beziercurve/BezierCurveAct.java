package mzj.mandroid.ui.android.view.beziercurve;

import android.view.View;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActBezierCurveBinding;


/**
 * creat by mzj on 2016/9/8 13:33
 *
 * http://myst729.github.io/bezier-curve/ 模拟贝赛尔曲线
 * https://github.com/venshine/BezierMaker
 */
public class BezierCurveAct extends BaseActivity<ActBezierCurveBinding>{

    @Override
    protected int getLayoutId() {
        return R.layout.act_bezier_curve;
    }

    @Override
    protected void initData() {
//        intCb();
//        binding.cb1.setOnClickListener(this);
//        binding.cb2.setOnClickListener(this);
//        binding.cb3.setOnClickListener(this);


        binding.bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.bc.startAnim();
            }
        });

    }


//    private void intCb(){
//        binding.cb1.setChecked(false);
//        binding.cb2.setChecked(false);
//        binding.cb3.setChecked(false);
//    }

//    @Override
//    public void onClick(View v) {
//        intCb();
//        switch (v.getId()){
//            case R.id.cb1:
//                binding.cb1.setChecked(true);
//                binding.bezierDraw1.setMode(1);
//                break;
//            case R.id.cb2:
//                binding.cb2.setChecked(true);
//                binding.bezierDraw1.setMode(2);
//                break;
//            case R.id.cb3:
//                binding.cb3.setChecked(true);
//                binding.bezierDraw1.setMode(3);
//                break;
//        }
//    }
}
