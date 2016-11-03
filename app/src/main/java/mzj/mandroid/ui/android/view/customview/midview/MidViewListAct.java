package mzj.mandroid.ui.android.view.customview.midview;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.CommentTitleListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.android.view.customview.midview.bounce.BounceAct;
import mzj.mandroid.ui.android.view.customview.midview.cart.AddToCartViewAct;
import mzj.mandroid.ui.android.view.customview.midview.loading.LoadingAct;
import mzj.mandroid.wiget.SpacesItemDecoration;

/**
 * Created by 振坚 on 2016/8/4.
 */
public class MidViewListAct extends BaseActivity<CommentTitleListBinding> {
    ListAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.comment_title_list;
    }

    @Override
    protected void initData() {
        List<TitleModel.Bean> list= new ArrayList<>();
        list.add(new TitleModel.Bean("加入购物车动画",AddToCartViewAct.class));
        list.add(new TitleModel.Bean("各种加载动画",LoadingAct.class));
        list.add(new TitleModel.Bean("仿IOS弹性滑动",BounceAct.class));

        adapter = new ListAdapter(this,list);
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(adapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));

    }
}
