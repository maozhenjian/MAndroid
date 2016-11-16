package mzj.mandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;


import java.util.List;

import mzj.mandroid.R;
import mzj.mandroid.databinding.ItemNetBinding;
import mzj.mandroid.model.TitleModel;

/**
 * Created by 振坚 on 2016/7/28.
 */
public class ListAdapter extends BaseRvAdapter {
    private List<TitleModel.Bean> list;
    private Context context;
    public ListAdapter(Context context,List<TitleModel.Bean> list) {
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
        final TitleModel.Bean bean = list.get(position);
        ItemNetBinding binding= (ItemNetBinding) holder.getBinding();
        binding.Tv.setText(bean.name);
        binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,bean.cls));
            }
        });
    }


}
