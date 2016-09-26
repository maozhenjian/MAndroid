package mzj.mandroid.Adapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import mzj.mandroid.R;
import mzj.mandroid.databinding.ItemCacheBinding;

/**
 * Created by 振坚 on 2016/7/28.
 */
public class CacheAdapter extends BaseRvAdapter {
    private List<String> url;
    public CacheAdapter(List<String> url) {
        this.url = url;
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_cache;
    }

    @Override
    public List ItemCount() {
        return url;
    }

    @Override
    public void ItemAction(BaseHolder holder, int position) {
        String bean =url.get(position);
        ItemCacheBinding binding = (ItemCacheBinding) holder.getBinding();
        ImageLoader.getInstance().displayImage(bean,binding.Ig);

//        new MyBitmapUtils().disPlay(binding.Ig, bean);

    }


}
