package mzj.mandroid.ui.java.collection;

import android.support.v7.widget.LinearLayoutManager;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mzj.mandroid.Adapter.ListAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActCollectionListBinding;
import mzj.mandroid.databinding.CommentTitleListBinding;
import mzj.mandroid.model.TitleModel;
import mzj.mandroid.ui.android.rx.rxcache.RxCacheAct;
import mzj.mandroid.wiget.SpacesItemDecoration;

/**
 * creat by mzj on 2016/9/23 20:53
 *
 * http://blog.csdn.net/qy1387/article/details/7752973
 */

public class CollectionListAct extends BaseActivity<ActCollectionListBinding> {
   ListAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.act_collection_list;
    }

    @Override
    protected void initData() {
        ImageLoader.getInstance().displayImage("http://images2015.cnblogs.com/blog/862876/201602/862876-20160227193813393-1620063866.png",binding.iv);
        List<TitleModel.Bean> list= new ArrayList<>();
        list.add(new TitleModel.Bean("ArrayList",ArrayListAct.class));
        list.add(new TitleModel.Bean("Map",MapAct.class));
        list.add(new TitleModel.Bean("Set",SetAct.class));
        adapter = new ListAdapter(this,list);
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(adapter);
        binding.Rv.addItemDecoration(new SpacesItemDecoration(10));
    }
}
