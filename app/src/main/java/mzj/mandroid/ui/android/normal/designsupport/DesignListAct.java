package mzj.mandroid.ui.android.normal.designsupport;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import mzj.mandroid.Adapter.DesignListAdapter;
import mzj.mandroid.MItemClickListener;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActNetListBinding;
import mzj.mandroid.model.DesignModel;
import mzj.mandroid.wiget.SpacesItemDecoration;


/**
 * Created by 振坚 on 2016/8/4.
 */
public class DesignListAct extends BaseActivity<ActNetListBinding> {
    DesignListAdapter netListAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.act_net_list;
    }

    @Override
    protected void initData() {
        netListAdapter = new DesignListAdapter(new DesignModel());
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(netListAdapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));
        netListAdapter.setOnItemClickListener(new MItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    startActivity(new Intent(DesignListAct.this, SimpleComponentsAct.class));
                }

            }
        });
    }
}
