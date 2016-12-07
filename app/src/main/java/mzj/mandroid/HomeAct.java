package mzj.mandroid;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActMainBinding;
import mzj.mandroid.utils.FragmentUtil;

/**
 * creat by mzj on 2016/9/19 14:27
 */

public class HomeAct extends BaseActivity<ActMainBinding> {
    private List<Fragment> listFra = new ArrayList<>();
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private ActionBarDrawerToggle drawerToggle;
    private static int currentindex = 0;
    private AndroidFrg androidFrg;
    private JAVAFrg javaFrg;

    @Override
    protected int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    protected void initData() {
        initFrg();
        setupView();

    }

    public void initFrg() {
        androidFrg = new AndroidFrg();
        javaFrg = new JAVAFrg();
        listFra.add(androidFrg);
        listFra.add(javaFrg);
    }

    private void setupView() {
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, toolbar, 0, 0);
        binding.drawerLayout.addDrawerListener(drawerToggle);
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                selectDrawerItem(menuItem);
                return true;
            }
        });
        selectDrawerItem(binding.navigationView.getMenu().getItem(0));
        fragmentManager.beginTransaction().add(R.id.content_frame, listFra.get(0)).commit();
    }


    private void selectDrawerItem(MenuItem menuItem) {
        boolean specialToolbarBehaviour = false;
        switch (menuItem.getItemId()) {
            case R.id.menu_home:
                FragmentUtil.switchFragment(fragmentManager, R.id.content_frame, listFra.get(currentindex), listFra.get(0));
                currentindex = 0;
                break;
            case R.id.menu_order:
                FragmentUtil.switchFragment(fragmentManager, R.id.content_frame, listFra.get(currentindex), listFra.get(1));
                currentindex = 1;
                break;

            default:
                break;
        }
        setToolbarElevation(specialToolbarBehaviour);
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        binding.drawerLayout.closeDrawers();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setToolbarElevation(boolean specialToolbarBehaviour) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (specialToolbarBehaviour) {
                toolbar.setElevation(0.0f);
            } else {
                binding.contentFrame.setElevation(0.0f);
            }
        }
    }

}
