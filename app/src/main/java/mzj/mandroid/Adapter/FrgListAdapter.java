package mzj.mandroid.Adapter;

import android.content.Context;

import java.util.List;

import mzj.mandroid.R;
import mzj.mandroid.databinding.ItemNetBinding;
import mzj.mandroid.model.FrgsModel;

/**
 * Created by 振坚 on 2016/7/28.
 */
public class FrgListAdapter extends BaseRvAdapter {
    private List<FrgsModel.Bean> list;
    private Context context;
    public FrgListAdapter(Context context, List<FrgsModel.Bean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_net;
    }

    @Override
    public List ItemCount() {
        return list;
    }

    @Override
    public void ItemAction(BaseHolder holder, int position) {
        final FrgsModel.Bean bean = list.get(position);
        ItemNetBinding binding= (ItemNetBinding) holder.getBinding();

        binding.Tv.setText(bean.name);

    }


}
