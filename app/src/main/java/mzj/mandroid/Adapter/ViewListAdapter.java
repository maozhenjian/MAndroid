package mzj.mandroid.Adapter;

import android.util.Log;

import java.util.List;

import mzj.mandroid.R;
import mzj.mandroid.databinding.ItemNetBinding;
import mzj.mandroid.model.ViewModel;

/**
 * Created by 振坚 on 2016/7/28.
 */
public class ViewListAdapter extends BaseRvAdapter {
    private ViewModel viewModel;
    public ViewListAdapter(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_net;
    }

    @Override
    public List ItemCount() {
        return viewModel.list;
    }

    @Override
    public void ItemAction(BaseHolder holder, int position) {
        ViewModel.Bean bean = viewModel.list.get(position);
        ItemNetBinding binding= (ItemNetBinding) holder.getBinding();
        Log.e("TAg",bean.name);
        binding.Tv.setText(bean.name);
    }


}
