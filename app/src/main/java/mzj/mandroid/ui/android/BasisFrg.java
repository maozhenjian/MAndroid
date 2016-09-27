package mzj.mandroid.ui.android;

import android.support.v7.widget.LinearLayoutManager;


import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseFragment;
import mzj.mandroid.databinding.CommentListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.android.normal.animation.ProAnimAct;
import mzj.mandroid.ui.android.normal.bitmap.BitmapAct;
import mzj.mandroid.ui.android.normal.bundel.BundelAct;
import mzj.mandroid.ui.android.normal.cache.BitmapCacheAct;
import mzj.mandroid.ui.android.normal.databinding.DataBindingFirstAct;
import mzj.mandroid.ui.android.normal.designsupport.DesignListAct;
import mzj.mandroid.wiget.SpacesItemDecoration;

/**
 * creat by mzj on 2016/9/19 16:39
 */

public class BasisFrg extends BaseFragment<CommentListBinding> {
    private List<TitleModel.Bean> list;
    private ListAdapter adapter;
    @Override
    public void initData() {
        list=new ArrayList<>();
        list.add(new TitleModel.Bean("Budle对象值传递Demo", BundelAct.class));
        list.add(new TitleModel.Bean("DataBinding实例Demo", DataBindingFirstAct.class));
        list.add(new TitleModel.Bean("关于动画的实例解析", ProAnimAct.class));
//        list.add(new TitleModel.Bean("Android当前主要网络请求框架比较", NetListAct.class));
        list.add(new TitleModel.Bean("Bitmap相关操作", BitmapAct.class));
//        list.add(new TitleModel.Bean("RxJava系列全面解析", RxListAct.class));
        list.add(new TitleModel.Bean("2015Design库组件使用详解", DesignListAct.class));
//        list.add(new TitleModel.Bean("View", ViewListAct.class));
        initList();
    }

    @Override
    public int getLayoutId() {
        return R.layout.comment_list;
    }
    private void initList(){
        adapter=new ListAdapter(getActivity(),list);
        binding.Rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.Rv.setAdapter(adapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));
    }
}
