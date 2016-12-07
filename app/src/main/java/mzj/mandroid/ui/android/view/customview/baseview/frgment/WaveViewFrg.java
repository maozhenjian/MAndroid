package mzj.mandroid.ui.android.view.customview.baseview.frgment;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseFragment;
import mzj.mandroid.databinding.FrgWaveViewBinding;

/**
 * Creat by mzj on 2016/12/7 12:58
 * Des:
 */

public class WaveViewFrg extends BaseFragment<FrgWaveViewBinding>{

    @Override
    public int getLayoutId() {
        return R.layout.frg_wave_view;
    }


    @Override
    public void initData() {
        binding.waveView.setDuration(5000);
        binding.waveView.setStyle(Paint.Style.FILL);
        binding.waveView.setColor(Color.BLUE);
        binding.waveView.setInterpolator(new LinearOutSlowInInterpolator());
        binding.waveView.start();
    }



}
