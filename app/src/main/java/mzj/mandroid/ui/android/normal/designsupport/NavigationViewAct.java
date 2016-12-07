package mzj.mandroid.ui.android.normal.designsupport;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActNavigationViewBinding;

/**
 * creat by mzj on 2016/8/18 09:54
 */
public class NavigationViewAct extends BaseActivity<ActNavigationViewBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.act_navigation_view;
    }

    @Override
    protected void initData() {
        binding.navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        setTitle(item.getTitle().toString().toUpperCase());
                        binding.drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
