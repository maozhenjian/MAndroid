package mzj.mandroid.Adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import mzj.mandroid.MItemClickListener;


/**
 * Databinding 通用的holder
 * Created by 振坚 on 16/5/10.
 */
public class BaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ViewDataBinding binding;
    private MItemClickListener mListener;

    public BaseHolder(View itemView, MItemClickListener listener) {
        super(itemView);
        this.mListener = listener;
        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onItemClick(view, getAdapterPosition());
        }
    }
}
