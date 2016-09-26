package mzj.mandroid.ui.android.network;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import mzj.mandroid.Adapter.NetListAdapter;
import mzj.mandroid.MItemClickListener;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActNetListBinding;
import mzj.mandroid.model.NetListModel;
import mzj.mandroid.ui.android.network.okhttp.OkhttpAct;
import mzj.mandroid.ui.android.network.retrofit.RetrofitAct;
import mzj.mandroid.ui.android.network.volley.VolleyFirstAct;
import mzj.mandroid.wiget.SpacesItemDecoration;


/**
 * Created by 振坚 on 2016/8/4.
 */
public class NetListAct extends BaseActivity<ActNetListBinding> {
    NetListAdapter netListAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.act_net_list;
    }

    @Override
    protected void initData() {
        netListAdapter = new NetListAdapter(new NetListModel());
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(netListAdapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));
        netListAdapter.setOnItemClickListener(new MItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    startActivity(new Intent(NetListAct.this, RetrofitAct.class));
                }
                if (position == 1) {
                    startActivity(new Intent(NetListAct.this, VolleyFirstAct.class));
                }
                if (position == 2) {
                    startActivity(new Intent(NetListAct.this, OkhttpAct.class));
                }
            }
        });
    }
}
