package mzj.mandroid.ui.java.thread;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.CommentTitleListBinding;
import mzj.mandroid.model.TitleModel;

import mzj.mandroid.wiget.SpacesItemDecoration;

/**
 * creat by mzj on 2016/9/28 21:58
 */

public class BaseThreadAct extends BaseActivity<CommentTitleListBinding> {
    private List<TitleModel.Bean> list;
    private ListAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.comment_title_list;
    }

    @Override
    protected void initData() {
        list=new ArrayList<>();
        list.add(new TitleModel.Bean("线程创建的方式", CreatThreadAct.class));
        list.add(new TitleModel.Bean("线程的生命周期", LifeThreadAct.class));
        list.add(new TitleModel.Bean("线程同步", LockThreadAct.class));
        list.add(new TitleModel.Bean("线程通信", CommuThreadAct.class));
        list.add(new TitleModel.Bean("线程池", CommuThreadAct.class));
        adapter=new ListAdapter(mContext,list);
        binding.Rv.setLayoutManager(new LinearLayoutManager(mContext));
        binding.Rv.setAdapter(adapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));
    }
}
