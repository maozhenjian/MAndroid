package mzj.mandroid.ui.java.calculate;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActRecursiveBinding;

/**
 * creat by mzj on 2016/10/12 14:23
 */

public class RecursiveAct extends BaseActivity<ActRecursiveBinding> implements View.OnClickListener {

    private int num1;
    private static int num2=3;
    @Override
    protected int getLayoutId() {
        return R.layout.act_recursive;
    }

    @Override
    protected void initData() {
        binding.recursive1.setOnClickListener(this);
        binding.button1.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
        binding.button3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recursive1:
               binding.recursive1.setText(addSelf((int) (Math.random()*10))+"");
                break;

            case R.id.button1:
                test1(num1);

                Log.e(TAG,num1+"num1");
                Log.e(TAG,num2+"num2");
                break;

            case R.id.button2:
                A a=new A();
                test2(new A());
                Log.e(TAG,"a::"+a.a);
                break;

            case R.id.button3:
                break;
        }
    }

    private void test1(int num){
        num++;
        Log.e(TAG,num+"test1");
    }

    private void test2(A a){
        a.a++;
        Log.e(TAG,"test2:a::"+a.a);
    }


    class A {
        int a=5;
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
