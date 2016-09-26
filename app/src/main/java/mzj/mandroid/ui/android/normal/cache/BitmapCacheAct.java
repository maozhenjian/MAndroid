package mzj.mandroid.ui.android.normal.cache;

import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;



import java.util.ArrayList;
import java.util.List;

import mzj.mandroid.Adapter.CacheAdapter;
import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActBitmapCacheBinding;

/**
 * Created by 振坚 on 2016/8/3.
 */
public class BitmapCacheAct extends BaseActivity<ActBitmapCacheBinding> {
    List<String> url;
    @Override
    protected int getLayoutId() {
        return R.layout.act_bitmap_cache;
    }

    @Override
    protected void initData() {
        //三级缓存相关代码点进去看

//        new MyBitmapUtils().disPlay(binding.Ig, "http://imgsrc.baidu.com/forum/w%3D580/sign=588ef3dca964034f0fcdc20e9fc27980/28381f30e924b899ba7444396c061d950a7bf662.jpg");

        url=new ArrayList<>();
        url.add("http://bcs.img.r1.91.com/data/upload/2014/09_13/23/201409132316102240.jpg");
        url.add("http://img15.3lian.com/2015/a1/16/d/204.jpg");
        url.add("http://img15.3lian.com/2015/a1/16/d/203.jpg");
        url.add("http://img4.duitang.com/uploads/item/201306/10/20130610215621_jJMcS.thumb.600_0.jpeg");
        url.add("http://img1.3lian.com/img13/c3/10/d/32.jpg");
        url.add("http://d.hiphotos.baidu.com/zhidao/pic/item/d6ca7bcb0a46f21fa5ec482af0246b600d33aeea.jpg");
        url.add("http://a.hiphotos.baidu.com/zhidao/pic/item/79f0f736afc379314a650b4eeac4b74543a91143.jpg");
        url.add("http://img15.3lian.com/2015/h1/280/d/9.jpg");
        url.add("http://img1.3lian.com/img13/c3/10/d/34.jpg");
        url.add("http://rescdn.qqmail.com/dyimg/20140630/7D38689E0A7A.jpg");

        CacheAdapter adapter=new CacheAdapter(url);
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setAdapter(adapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.Rv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                }
            });
        }







    }



}
