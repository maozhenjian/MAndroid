package mzj.mandroid.ui.android.network;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.Adapter.NetListAdapter;
import mzj.mandroid.MItemClickListener;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActNetListBinding;
import mzj.mandroid.model.NetListModel;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.android.network.okhttp.OkhttpAct;
import mzj.mandroid.ui.android.network.retrofit.RetrofitAct;
import mzj.mandroid.ui.android.network.socket.SocketTestAct;
import mzj.mandroid.ui.android.network.volley.VolleyFirstAct;
import mzj.mandroid.ui.android.rx.rxcache.RxCacheAct;
import mzj.mandroid.wiget.SpacesItemDecoration;


/**
 * Created by 振坚 on 2016/8/4.
 */
public class NetListAct extends BaseActivity<ActNetListBinding> {
    ListAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.act_net_list;
    }

    @Override
    protected void initData() {
        List<TitleModel.Bean> list= new ArrayList<>();
        list.add(new TitleModel.Bean("Volley",VolleyFirstAct.class));
        list.add(new TitleModel.Bean("OkHttp",OkhttpAct.class));
        list.add(new TitleModel.Bean("RetrofitAct",RetrofitAct.class));
        list.add(new TitleModel.Bean("Socket",SocketTestAct.class));
        adapter = new ListAdapter(this,list);
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(adapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));
    }
}
