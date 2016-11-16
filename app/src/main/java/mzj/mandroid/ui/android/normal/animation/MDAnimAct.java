package mzj.mandroid.ui.android.normal.animation;

import android.animation.Animator;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActMdAnimBinding;

/**
 * creat by mzj on 2016/11/3 09:37
 */

public class MDAnimAct extends BaseActivity<ActMdAnimBinding> implements View.OnClickListener{
    @Override
    protected int getLayoutId() {
        return R.layout.act_md_anim;
    }

    @Override
    protected void initData() {

        binding.oval.setOnClickListener(this);
        binding.rect.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.oval:
                /**
                 * view 操作的视图
                 centerX 动画开始的中心点X
                 centerY 动画开始的中心点Y
                 startRadius 动画开始半径
                 startRadius 动画结束半径*/
                Animator animator1 = ViewAnimationUtils.createCircularReveal(
                        binding.oval,
                        binding.oval.getWidth() / 2,
                        binding.oval.getHeight() / 3,
                        binding.oval.getWidth(),
                        0);
                animator1.setInterpolator(new AccelerateDecelerateInterpolator());
                animator1.setDuration(2000);
                animator1.start();
                break;

            case R.id.rect:
                Animator animator2 = ViewAnimationUtils.createCircularReveal(
                        binding.rect,
                        0,
                        0,
                        0,
                        (float) Math.hypot(binding.rect.getWidth(), binding.rect.getHeight()));
                animator2.setInterpolator(new AccelerateInterpolator());
                animator2.setDuration(2000);
                animator2.start();
                break;
        }
    }




}
