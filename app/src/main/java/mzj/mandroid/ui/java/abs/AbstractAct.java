package mzj.mandroid.ui.java.abs;


import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActAbstractBinding;

/**
 * creat by mzj on 2016/9/19 20:41
 * 抽象接口相关，Java编程思想P311
 *
 * Java反射机制是在运行状态中，对任意一个类对象（class文件），都能知道这个类的所有属性和方法
 * 可以理解为对类的解剖
 */

public class AbstractAct extends BaseActivity<ActAbstractBinding> implements TestListener {

    @Override
    protected int getLayoutId() {
        return R.layout.act_abstract;
    }

    @Override
    protected void initData() {
       new MidListen().setTestListener(this);
    }

    @Override
    public int addNum(int num) {
        return num+1;
    }


}
