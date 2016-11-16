package mzj.mandroid.ui.android.view.customview;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActNetListBinding;
import mzj.mandroid.databinding.CommentTitleListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.android.rx.rxcache.RxCacheAct;
import mzj.mandroid.ui.android.view.customview.baseview.BaseViewListAct;
import mzj.mandroid.ui.android.view.customview.midview.MidViewListAct;
import mzj.mandroid.ui.android.view.customview.superview.SuperViewListAct;
import mzj.mandroid.ui.android.view.viewpager.ViewPagerListAct;
import mzj.mandroid.wiget.SpacesItemDecoration;

/**
 * Created by 振坚 on 2016/8/4.
 */
public class CustomViewListAct extends BaseActivity<CommentTitleListBinding> {
    ListAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.comment_title_list;
    }

    @Override
    protected void initData() {
        List<TitleModel.Bean> list= new ArrayList<>();
        list.add(new TitleModel.Bean("简单自定义View",BaseViewListAct.class));
        list.add(new TitleModel.Bean("一般可用性自定义View",MidViewListAct.class));
        list.add(new TitleModel.Bean("高级View",SuperViewListAct.class));

        adapter = new ListAdapter(this,list);
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(adapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));

    }
}
