package mzj.mandroid.ui.android.view.customview.baseview;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.FrgListAdapter;
import mzj.mandroid.MItemClickListener;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.base.BaseFragment;
import mzj.mandroid.databinding.CommentTitleListBinding;
import mzj.mandroid.model.FrgsModel;
import mzj.mandroid.ui.android.view.customview.baseview.frgment.WaveViewFrg;
import mzj.mandroid.utils.FragmentUtil;

/**
 * Created by 振坚 on 2016/8/4.
 */
public class BaseViewListAct extends BaseActivity<CommentTitleListBinding> {
    FrgListAdapter adapter;
    BaseFragment mcurrentFrg;
    @Override
    protected int getLayoutId() {
        return R.layout.comment_title_list;
    }

    @Override
    protected void initData() {

        final List<FrgsModel.Bean> list= new ArrayList<>();
        list.add(new FrgsModel.Bean("波纹扩散",new WaveViewFrg()));
//        list.add(new TitleModel.Bean("简单View展示",BaseViewShowAct.class));
//        list.add(new TitleModel.Bean("圆角图",BaseViewShowAct.class));

//        list.add(new TitleModel.Bean("圆角图",WaveView.class));
        adapter = new FrgListAdapter(this,list);
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new MItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("TAG","onItemClick");
                FragmentUtil.switchFragment(getSupportFragmentManager(),mcurrentFrg,list.get(position).frg,R.id.root_root,list.get(position).frg.getClass().getName());
                mcurrentFrg=list.get(position).frg;
            }
        });


    }



}
