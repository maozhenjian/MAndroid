package mzj.mandroid.Adapter;



import java.util.List;

import mzj.mandroid.R;
import mzj.mandroid.databinding.ItemNetBinding;
import mzj.mandroid.model.NetListModel;

/**
 * Created by 振坚 on 2016/7/28.
 */
public class NetListAdapter extends BaseRvAdapter {
    private NetListModel netListModel;
    public NetListAdapter(NetListModel netListModel) {
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
        NetListModel.Bean bean = netListModel.list.get(position);
        ItemNetBinding binding= (ItemNetBinding) holder.getBinding();
        binding.Tv.setText(bean.name);
    }


}
