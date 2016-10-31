package mzj.mandroid.ui.android.normal.animation;



import android.graphics.drawable.AnimationDrawable;
import android.view.View;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActFrameAnimBinding;
import mzj.mandroid.databinding.ActTweenBinding;


/**
 * Created by 振坚 on 2016/8/1.
 * 逐帧动画
 * 4.）AnimationDrawable 几个常见的api:
 *
 * void start() - 开始播放动画
 * <p>
 * void stop() - 停止播放动画
 * <p>
 * addFrame(Drawable frame, int duration) - 添加一帧，并设置该帧显示的持续时间
 * <p>
 * void setOneShoe(boolean flag) - false为循环播放，true为仅播放一次
 * <p>
 * boolean isRunning() - 是否正在播放
 */
public class FrameAnimAct extends BaseActivity<ActFrameAnimBinding> {
    private AnimationDrawable animationDrawable;
    @Override
    protected int getLayoutId() {
        return R.layout.act_frame_anim;

    }

    @Override
    protected void initData() {
        binding.buttonA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                binding.animationIV.setImageResource(R.drawable.wifi_anim_list);
                animationDrawable = (AnimationDrawable) binding.animationIV.getDrawable();
                animationDrawable.start();
            }

        });

        binding.buttonB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animationDrawable = (AnimationDrawable) binding.animationIV.getDrawable();
                animationDrawable.stop();
            }

        });
    }

}
