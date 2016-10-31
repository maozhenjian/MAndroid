package mzj.mandroid.ui.java.design;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.CommentTitleListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.java.abs.AbstractAct;
import mzj.mandroid.wiget.SpacesItemDecoration;

/**
 * creat by mzj on 2016/9/19 21:01
 *
 * http://blog.csdn.net/itachi85/article/details/50510124
 */

public class DesignListAct extends BaseActivity<CommentTitleListBinding> {
    private List<TitleModel.Bean> list;
    private ListAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.comment_title_list;
    }

    @Override
    protected void initData() {
        list=new ArrayList<>();
        list.add(new TitleModel.Bean("策略模式", StrategyAct.class));
        list.add(new TitleModel.Bean("单例模式", AbstractAct.class));
        adapter=new ListAdapter(this,list);

        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(adapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));
    }







}
