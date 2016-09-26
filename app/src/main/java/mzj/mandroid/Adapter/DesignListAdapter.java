package mzj.mandroid.Adapter;


import java.util.List;

import mzj.mandroid.R;
import mzj.mandroid.databinding.ItemNetBinding;
import mzj.mandroid.model.DesignModel;

/**
 * Created by 振坚 on 2016/7/28.
 */
public class DesignListAdapter extends BaseRvAdapter {
    private DesignModel netListModel;
    public DesignListAdapter(DesignModel netListModel) {
        this.netListModel = netListModel;
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_net;
    }

    @Override
    public List ItemCount() {
        return netListModel.list;
    }
    @Override
    public void ItemAction(BaseHolder holder, int position) {
        ItemNetBinding binding= (ItemNetBinding) holder.getBinding();
        DesignModel.Bean bean = netListModel.list.get(position);
        binding.Tv.setText(bean.name);
    }


}
