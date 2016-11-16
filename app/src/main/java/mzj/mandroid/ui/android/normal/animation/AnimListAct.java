package mzj.mandroid.ui.android.normal.animation;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActNetListBinding;
import mzj.mandroid.databinding.CommentTitleListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.android.network.okhttp.OkhttpAct;
import mzj.mandroid.ui.android.network.retrofit.RetrofitAct;
import mzj.mandroid.ui.android.network.socket.SocketTestAct;
import mzj.mandroid.ui.android.network.volley.VolleyFirstAct;
import mzj.mandroid.wiget.SpacesItemDecoration;


/**
 * Created by 振坚 on 2016/8/4.
 *
 */
public class AnimListAct extends BaseActivity<CommentTitleListBinding> {
    ListAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.comment_title_list;
    }

    @Override
    protected void initData() {
        List<TitleModel.Bean> list= new ArrayList<>();
        list.add(new TitleModel.Bean("逐帧动画",FrameAnimAct.class));
        list.add(new TitleModel.Bean("补间动画",TweenAnimAct.class));
        list.add(new TitleModel.Bean("属性动画",ProAnimAct.class));
        list.add(new TitleModel.Bean("5.0MD动画",MDAnimAct.class));
        adapter = new ListAdapter(this,list);
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(adapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));
    }
}
