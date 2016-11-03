package mzj.mandroid.ui.android.view.customview.midview.loading;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActLoadingBinding;

/**
 * creat by mzj on 2016/10/31 14:17
 */

public class LoadingAct extends BaseActivity<ActLoadingBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.act_loading;
    }

    @Override
    protected void initData() {
//        binding.loadView.start();
        binding.cLoadView.start();
    }
}
