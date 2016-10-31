package mzj.mandroid.ui.android.normal.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import mzj.mandroid.R;
import mzj.mandroid.databinding.ActDatabindingFirstBinding;
import mzj.mandroid.ui.android.normal.databinding.model.ClickBean;
import mzj.mandroid.ui.android.normal.databinding.model.NumBean;
import mzj.mandroid.ui.android.normal.databinding.model.UserBean;


/**
 * Created by 振坚 on 2016/8/2.
 * DataBinding的基础使用方式
 * 如何启动看build.gradle
 */
public class DataBindingFirstAct extends AppCompatActivity {
    public UserBean userBean;
    public NumBean numBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //实体类和布局文件进行了绑定
        ActDatabindingFirstBinding binding = DataBindingUtil.setContentView(this, R.layout.act_databinding_first);

        // 传递数据到布局文件
        userBean = new UserBean();
        userBean.setAge(12);
        userBean.setName("皮卡丘");
        userBean.setIndex("0");
        userBean.setImageUrl("http://imgsrc.baidu.com/forum/pic/item/8c1001e93901213f98fd265c54e736d12e2e9567.jpg");
        binding.setUser(userBean);
        //对应app:image
        binding.setImageUrl("http://imgsrc.baidu.com/forum/w%3D580/sign=588ef3dca964034f0fcdc20e9fc27980/28381f30e924b899ba7444396c061d950a7bf662.jpg");

        //点击事件
        binding.setClick(new ClickBean(this));


        numBean = new NumBean(15);
        binding.setNum(numBean);

        binding.et.addTextChangedListener(watcher);


    }


    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            Log.i("TAG", "userBean.getName()::" + userBean.getName());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

    };
}
