package mzj.mandroid.ui.android.frame.imageloader;

import android.support.v7.widget.LinearLayoutManager;



import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.CommentTitleListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.wiget.SpacesItemDecoration;

/**
 * creat by mzj on 2016/9/26 10:47
 *
 */

public class ImageLoaderListAct extends BaseActivity<CommentTitleListBinding> {
    ListAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.comment_title_list;
    }
    @Override
    protected void initData() {
        List<TitleModel.Bean> list= new ArrayList<>();
        list.add(new TitleModel.Bean("Universal_Image_Loader", UniversalImageLoaderAct.class));
        list.add(new TitleModel.Bean("Glide", GlideAct.class));
        adapter=new ListAdapter(this,list);
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(adapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));
    }
}
