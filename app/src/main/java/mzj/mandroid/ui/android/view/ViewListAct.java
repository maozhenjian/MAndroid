package mzj.mandroid.ui.android.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.Adapter.ViewListAdapter;
import mzj.mandroid.MItemClickListener;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActNetListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.model.ViewModel;
import mzj.mandroid.ui.android.view.beziercurve.BezierCurveAct;
import mzj.mandroid.ui.android.view.customview.CustomViewListAct;
import mzj.mandroid.wiget.SpacesItemDecoration;


/**
 * Created by 振坚 on 2016/8/4.
 */
public class ViewListAct extends BaseActivity<ActNetListBinding> {
    private List<TitleModel.Bean> list;
    private ListAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.act_net_list;
    }

    @Override
    protected void initData() {


        list=new ArrayList<>();
        list.add(new TitleModel.Bean("坐标", ViewCoordinatesAct.class));
        list.add(new TitleModel.Bean("事件机制", TouchAct.class));
        list.add(new TitleModel.Bean("自定义View", CustomViewListAct.class));
        list.add(new TitleModel.Bean("贝赛尔曲线", BezierCurveAct.class));

        adapter=new ListAdapter(this,list);
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(adapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));


    }
}
