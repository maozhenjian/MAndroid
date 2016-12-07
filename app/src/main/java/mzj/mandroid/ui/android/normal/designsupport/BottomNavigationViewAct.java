package mzj.mandroid.ui.android.normal.designsupport;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActBottomViewBinding;

/**
 * creat by mzj on 2016/8/18 09:54
 */
public class BottomNavigationViewAct extends BaseActivity<ActBottomViewBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.act_bottom_view;
    }

    @Override
    protected void initData() {
        binding.navigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        binding.text.setText(item.getTitle().toString().toUpperCase());
                        return true;
                    }
                });
    }
}
