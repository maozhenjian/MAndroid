package mzj.mandroid.ui.android.view.viewpager;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActNetListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.android.normal.designsupport.DesignListAct;
import mzj.mandroid.ui.android.view.TouchAct;
import mzj.mandroid.ui.android.view.ViewCoordinatesAct;
import mzj.mandroid.ui.android.view.beziercurve.BezierCurveAct;
import mzj.mandroid.ui.android.view.customview.CustomViewListAct;
import mzj.mandroid.ui.android.view.viewpager.viewpagercard.ViewPagerCardAct;
import mzj.mandroid.wiget.SpacesItemDecoration;

/**
 * creat by mzj on 2016/11/1 09:13
 */

public class ViewPagerListAct extends BaseActivity<ActNetListBinding> {
    private List<TitleModel.Bean> list;
    private ListAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.act_net_list;
    }

    @Override
    protected void initData() {
        list=new ArrayList<>();
        list.add(new TitleModel.Bean("ViewPagerCard", ViewPagerCardAct.class));

        adapter=new ListAdapter(this,list);
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(adapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));
    }
}
