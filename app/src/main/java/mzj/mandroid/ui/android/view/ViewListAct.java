package mzj.mandroid.ui.android.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import mzj.mandroid.Adapter.ViewListAdapter;
import mzj.mandroid.MItemClickListener;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActNetListBinding;
import mzj.mandroid.model.ViewModel;
import mzj.mandroid.ui.android.view.beziercurve.BezierCurveAct;
import mzj.mandroid.wiget.SpacesItemDecoration;


/**
 * Created by 振坚 on 2016/8/4.
 */
public class ViewListAct extends BaseActivity<ActNetListBinding> {
    ViewListAdapter viewListAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.act_net_list;
    }

    @Override
    protected void initData() {
        viewListAdapter = new ViewListAdapter(new ViewModel());
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(viewListAdapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));
        viewListAdapter.setOnItemClickListener(new MItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    startActivity(new Intent(ViewListAct.this, ViewCoordinatesAct.class));
                }
                if (position == 1) {
                    startActivity(new Intent(ViewListAct.this, TouchAct.class));
                }
                if (position == 2) {
                    startActivity(new Intent(ViewListAct.this, BezierCurveAct.class));
                }

            }
        });
    }
}
