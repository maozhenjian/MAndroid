package mzj.mandroid.ui.java;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseFragment;
import mzj.mandroid.databinding.CommentListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.java.abs.AbstractAct;
import mzj.mandroid.ui.java.annotation.AnnotationAct;
import mzj.mandroid.ui.java.calculate.CalculateListAct;
import mzj.mandroid.ui.java.collection.CollectionListAct;
import mzj.mandroid.ui.java.common.CommonAct;
import mzj.mandroid.ui.java.design.DesignListAct;
import mzj.mandroid.ui.java.io.IOAct;
import mzj.mandroid.ui.java.net.NetAct;
import mzj.mandroid.ui.java.reflect.ReflectAct;
import mzj.mandroid.ui.java.thread.BaseThreadAct;
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
        list.add(new TitleModel.Bean("集合", CollectionListAct.class));
        list.add(new TitleModel.Bean("线程", BaseThreadAct.class));
        list.add(new TitleModel.Bean("反射", ReflectAct.class));
        list.add(new TitleModel.Bean("注解", AnnotationAct.class));
        list.add(new TitleModel.Bean("文件流", IOAct.class));
        list.add(new TitleModel.Bean("网络", NetAct.class));
        list.add(new TitleModel.Bean("正则表达式", NetAct.class));
        list.add(new TitleModel.Bean("JAVA常用类", CommonAct.class));
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
