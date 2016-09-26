package mzj.mandroid.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * Created by Tong Maobiao on 2015/11/4.
 */
public abstract class BaseFragment<BindingType extends ViewDataBinding> extends Fragment {
    public BindingType binding;
    //-----Dialog 绑定数据时需要的2个参数
    public LayoutInflater inflater;
    public ViewGroup viewGroup;
    //-----Dialog end-----


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("TAG","onCreateView");
        this.inflater = inflater;
        this.viewGroup = container;
        if(getLayoutId() != 0 )
        {
            binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        }
        Log.e("TAG",getClass().getSimpleName().toString());
        return binding.getRoot();
    }

    public abstract void initData();
    public abstract int getLayoutId();
}
