package mzj.mandroid.java;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseFragment;
import mzj.mandroid.databinding.CommentListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.java.abs.AbstractAct;
import mzj.mandroid.ui.java.calculate.CalculateListAct;
import mzj.mandroid.ui.java.design.DesignListAct;
import mzj.mandroid.wiget.SpacesItemDecoration;

/**
 * creat by mzj on 2016/9/19 16:39
 */

public class JavaBasisFrg extends BaseFragment<CommentListBinding> {
    private List<TitleModel.Bean> list;
    private ListAdapter adapter;
    @Override
    public void initData() {
        list=new ArrayList<>();
        list.add(new TitleModel.Bean("设计模式", DesignListAct.class));
        list.add(new TitleModel.Bean("抽象与接口", AbstractAct.class));
        list.add(new TitleModel.Bean("算法", CalculateListAct.class));
        list.add(new TitleModel.Bean("集合", AbstractAct.class));
        list.add(new TitleModel.Bean("线程", AbstractAct.class));
        list.add(new TitleModel.Bean("反射", AbstractAct.class));
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
