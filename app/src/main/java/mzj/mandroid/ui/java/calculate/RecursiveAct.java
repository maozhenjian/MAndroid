package mzj.mandroid.ui.java.calculate;

import android.util.Log;
import android.view.View;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActRecursiveBinding;

/**
 * creat by mzj on 2016/10/12 14:23
 */

public class RecursiveAct extends BaseActivity<ActRecursiveBinding> implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.act_recursive;
    }

    @Override
    protected void initData() {
        binding.recursive1.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recursive1:
               binding.recursive1.setText(addSelf((int) (Math.random()*10))+"");
                break;
        }
    }


    /**
     * 利用递归自加
     */
    private int  addSelf(int num){
        Log.e("TAG","num:"+num);
        if (num>0){
          return num+addSelf(num-1);
        }
        return 0;
    }
}
