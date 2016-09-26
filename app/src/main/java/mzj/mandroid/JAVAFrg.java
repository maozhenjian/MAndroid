package mzj.mandroid;

import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;


import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.TabsAdapter;
import mzj.mandroid.base.BaseFragment;
import mzj.mandroid.databinding.ActivityHomeBinding;
import mzj.mandroid.java.JavaBasisFrg;
import mzj.mandroid.wiget.NetworkImageHolderView;

/**
 * 文本推荐：http://www.diycode.cc/wiki/androidinterview 《Android 开发工程师面试指南》
 *
 */
public class JAVAFrg extends BaseFragment<ActivityHomeBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initData() {
        setBanner();
        binding.collapsingToolbar.setTitle("JAVA-系列");
        binding.Fab.setVisibility(View.GONE);
        TabsAdapter tabsAdapter = new TabsAdapter(getChildFragmentManager());
        tabsAdapter.addFragment(new JavaBasisFrg(), "JAVA第一章");
        binding.vp.setAdapter(tabsAdapter);
        binding.tabLayout.setupWithViewPager(binding.vp);
    }

    //轮播图设置
    private void setBanner() {
        List<String> urls= new ArrayList<>();
        urls.add("http://img1.imgtn.bdimg.com/it/u=2550228549,1199432068&fm=21&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3867673427,4137098964&fm=21&gp=0.jpg");
        urls.add("http://g.hiphotos.baidu.com/zhidao/pic/item/574e9258d109b3deda108bc3cabf6c81810a4ce9.jpg");
        urls.add("http://pic.qiantucdn.com/58pic/17/91/52/55aa91b56d401_1024.jpg");
        binding.convenientBanner.setPages(
                new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, urls)
                .startTurning(5000)     //设置自动切换（同时设置了切换时间间隔）
                .setPointViewVisible(true)    //设置指示器是否可见
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT); //设置指示器的方向
    }
}
