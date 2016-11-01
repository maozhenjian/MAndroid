package mzj.mandroid.ui.android.view.viewpager.viewpagercard;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActViewpagerCardBinding;
import mzj.mandroid.ui.android.view.viewpager.adapter.CardFragmentPagerAdapter;
import mzj.mandroid.ui.android.view.viewpager.adapter.CardPagerAdapter;
import mzj.mandroid.ui.android.view.viewpager.transformer.ShadowTransformer;

/**
 * creat by mzj on 2016/11/1 09:14
 */

public class ViewPagerCardAct extends BaseActivity<ActViewpagerCardBinding> {
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
//    private CardFragmentPagerAdapter mFragmentCardAdapter;
//    private ShadowTransformer mFragmentCardShadowTransformer;
    @Override
    protected int getLayoutId() {
        return R.layout.act_viewpager_card;
    }

    @Override
    protected void initData() {
        mCardAdapter = new CardPagerAdapter();

        mCardShadowTransformer = new ShadowTransformer( binding.viewPager, mCardAdapter);
//        mFragmentCardShadowTransformer = new ShadowTransformer( binding.viewPager, mFragmentCardAdapter);

        binding.viewPager.setAdapter(mCardAdapter);
        binding.viewPager.setPageTransformer(false, mCardShadowTransformer);
//        表示三个界面之间来回切换都不会重新加载
        binding.viewPager.setOffscreenPageLimit(3);
        mCardShadowTransformer.enableScaling(true);
    }
}
