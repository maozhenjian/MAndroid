package mzj.mandroid.ui.java.calculate;

import android.support.v7.widget.LinearLayoutManager;



import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.CommentTitleListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.android.rx.rxcache.RxCacheAct;
import mzj.mandroid.wiget.SpacesItemDecoration;

/**
 * creat by mzj on 2016/9/23 20:53
 *
 * http://blog.csdn.net/qy1387/article/details/7752973
 */

public class CalculateListAct extends BaseActivity<CommentTitleListBinding> {
   ListAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.comment_title_list;
    }

    @Override
    protected void initData() {
        List<TitleModel.Bean> list= new ArrayList<>();
        list.add(new TitleModel.Bean("冒泡排序",RxCacheAct.class));
        list.add(new TitleModel.Bean("希尔排序",RxCacheAct.class));
        list.add(new TitleModel.Bean("简单选择排序",RxCacheAct.class));
        list.add(new TitleModel.Bean("递归函数",RecursiveAct.class));
        adapter = new ListAdapter(this,list);
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(adapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));
    }
}
