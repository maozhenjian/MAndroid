package mzj.mandroid.ui.java.common;

import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActCommonBinding;
import mzj.mandroid.databinding.CommentTitleListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.android.rx.rxcache.RxCacheAct;
import mzj.mandroid.ui.java.calculate.RecursiveAct;
import mzj.mandroid.wiget.SpacesItemDecoration;

/**
 * creat by mzj on 2016/9/23 20:53
 *
 */

public class CommonAct extends BaseActivity<ActCommonBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.act_common;
    }

    @Override
    protected void initData() {
        binding.countDownTimer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.count_down_timer:
                countDownTimer();
                break;
        }
    }

    /**
     * 倒计时操作
     * CountDownTimer timer = new CountDownTimer(10000, 1000)中，
     * 第一个参数表示总时间，第二个参数表示间隔时间。
     * 意思就是每隔一秒会回调一次方法onTick，然后10秒之后会回调onFinish方法。
     */
    private void countDownTimer() {
        CountDownTimer timer = new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                binding.countDownTimer.setClickable(false);
                binding.countDownTimer.setText((millisUntilFinished / 1000) + "秒后可重发");
            }

            @Override
            public void onFinish() {
                binding.countDownTimer.setClickable(true);
                binding.countDownTimer.setText("获取验证码");
            }
        };
        timer.start();
    }

}
