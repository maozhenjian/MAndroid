package mzj.mandroid.ui.android.normal.animation;



import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;

import com.nostra13.universalimageloader.core.ImageLoader;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActTweenBinding;


/**
 * Created by 振坚 on 2016/8/1.
 * 补间动画
 */
public class TweenAnimAct extends BaseActivity<ActTweenBinding> implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.act_tween;
    }

    @Override
    protected void initData() {
        binding.setAct(this);
        ImageLoader.getInstance().displayImage("http://pic34.nipic.com/20131011/7487939_181724677136_2.jpg",binding.animIv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xml_alpha:
                Animation xmlAlpha= AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
                xmlAlpha.setFillAfter(false);
                binding.animIv.startAnimation(xmlAlpha);
                break;
            case R.id.xml_translate:
                Animation xmlTranslate= AnimationUtils.loadAnimation(this,R.anim.anim_translate);
                xmlTranslate.setFillAfter(false);
                binding.animIv.startAnimation(xmlTranslate);
                break;
            case R.id.xml_scale:
                Animation xmlScale= AnimationUtils.loadAnimation(this,R.anim.anim_scale);
                xmlScale.setFillAfter(false);
                binding.animIv.startAnimation(xmlScale);
                break;
            case R.id.xml_roate:
                Animation xmlRotate= AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
                xmlRotate.setFillAfter(false);
                binding.animIv.startAnimation(xmlRotate);
                break;

            case R.id.xml_animationSet:
                Animation animationSet= AnimationUtils.loadAnimation(this,R.anim.anim_set);
                binding.animIv.startAnimation(animationSet);
                break;



            case R.id.code_alpha:

                break;

            case R.id.code_translate:

                break;

            case R.id.code_scale:

                break;

            case R.id.code_rotate:

                break;

            case R.id.code_animationSet:
                AnimationSet set = new AnimationSet(true);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                alphaAnimation.setDuration(2000);
                RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                        Animation.RELATIVE_TO_SELF,0.5f,
                        Animation.RELATIVE_TO_SELF,0.5f);
                rotateAnimation.setDuration(1000);
                set.addAnimation(rotateAnimation);
                set.addAnimation(alphaAnimation);
                binding.animIv.startAnimation(set);
                break;
        }
    }
}
