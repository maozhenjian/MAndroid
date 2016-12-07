package mzj.mandroid.ui.java.net;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.CommentTitleListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.java.net.udp.UDPAct;

/**
 * creat by mzj on 2016/10/23 19:37
 */

public class NetAct extends BaseActivity<CommentTitleListBinding> {
    private List<TitleModel.Bean> list;
    private ListAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.comment_title_list;
    }

    @Override
    protected void initData() {
        list=new ArrayList<>();
        list.add(new TitleModel.Bean("UDP", UDPAct.class));
        adapter=new ListAdapter(this,list);

        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(adapter);
    }
}
