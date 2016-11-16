package mzj.mandroid.ui.android.view.scroller;

import android.view.View;
import android.widget.Scroller;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActScorllerBinding;

/**
 * creat by mzj on 2016/11/4 09:19
 */

public class ScrollerAct extends BaseActivity<ActScorllerBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.act_scorller;
    }

    @Override
    protected void initData() {
        /**
         * 而scrollTo()方法则是让View相对于初始的位置滚动某段距离。
         * scrollBy()方法是让View相对于当前的位置滚动某段距离，
         */
        binding.scrollByBtn.setOnClickListener(this);
        binding.scrollToBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scroll_to_btn:
                binding.rootLL.scrollTo((int)getResources().getDimension(R.dimen.scroll_h),(int)getResources().getDimension(R.dimen.scroll_v));
                break;

            case R.id.scroll_by_btn:
                binding.rootLL.scrollBy((int)getResources().getDimension(R.dimen.scroll_h),(int)getResources().getDimension(R.dimen.scroll_v));
                break;
        }
    }
}
