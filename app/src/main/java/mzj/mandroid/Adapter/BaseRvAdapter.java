package mzj.mandroid.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import mzj.mandroid.MItemClickListener;

/**
 * Created by 振坚 on 2016/7/28.
 */
public abstract class BaseRvAdapter extends RecyclerView.Adapter<BaseHolder> {

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(getItemLayout(),parent,false);
        BaseHolder holder = new BaseHolder(v,mItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        ItemAction(holder,position);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return ItemCount()== null ? 0 :ItemCount().size();
    }
    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MItemClickListener listener){
        this.mItemClickListener = listener;
    }
    private MItemClickListener mItemClickListener;

    public abstract int getItemLayout();
    public abstract List ItemCount();
    public abstract void ItemAction(BaseHolder holder, int position);
}
