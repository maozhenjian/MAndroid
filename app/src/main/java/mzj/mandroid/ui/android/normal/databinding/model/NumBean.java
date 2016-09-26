package mzj.mandroid.ui.android.normal.databinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


/**
 * Created by 振坚 on 2016/8/2.
 * 数据监听，实时改变
 *
 * 如果不想继承也可以调用Observable接口
 */
public class NumBean extends BaseObservable {
    private int phone;

    public NumBean(int phone) {
        this.phone = phone;
    }

    /**notifyPropertyChanged(int fieldId)是一个非常有用的方法，在setter中调用，能够更新View中的数据。
     *
     * 注解@Bindable，这样会在编译时在BR中生成一个标识，这样能够鉴定这个属性是否被修改过。
     * BR是编译时生成的类似于Android中的R.class的文件，其中也是标识了所有你在DataBinding 中定义的类和属性。
     * 而在自动生成的Binding.class中也印证了这一点，当调用binding.setUser(user)时binding也有调用notifyPropertyChanged(BR.user)
     */

    public void setPhone(int phone) {
        this.phone = phone;
//        notifyPropertyChanged(BR.phone);
    }


    @Bindable
    public String getPhone() {
        return phone+"";
    }


}