package mzj.mandroid.ui.android;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseFragment;
import mzj.mandroid.databinding.CommentListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.android.system.pic.PicAct;
import mzj.mandroid.wiget.SpacesItemDecoration;

/**
 * creat by mzj on 2016/9/19 16:39
 *
 */

public class SystemFrg extends BaseFragment<CommentListBinding> {
    private List<TitleModel.Bean> list;
    private ListAdapter adapter;
    @Override
    public void initData() {
        list=new ArrayList<>();
        list.add(new TitleModel.Bean("拍照和相册的图片地址", PicAct.class));


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
