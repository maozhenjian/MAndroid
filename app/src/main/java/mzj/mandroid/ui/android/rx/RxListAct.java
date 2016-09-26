package mzj.mandroid.ui.android.rx;

import android.support.v7.widget.LinearLayoutManager;



import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActNetListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.android.rx.rxcache.RxCacheAct;
import mzj.mandroid.wiget.SpacesItemDecoration;

/**
 * Created by 振坚 on 2016/8/4.
 */
public class RxListAct extends BaseActivity<ActNetListBinding> {
    ListAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.act_net_list;
    }

    @Override
    protected void initData() {
        List<TitleModel.Bean> list= new ArrayList<>();
        list.add(new TitleModel.Bean("RxJava基本使用方法",RxCacheAct.class));
        list.add(new TitleModel.Bean("RxJava基本使用方法",RxCacheAct.class));
        list.add(new TitleModel.Bean("RxJava基本使用方法",RxCacheAct.class));
        adapter = new ListAdapter(this,list);
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(adapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));

    }
}
