package mzj.mandroid.ui.android.frame.buffknife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mzj.mandroid.R;

/**
 * creat by mzj on 2016/9/28 14:29
 *
 * 根据官方文档进行配置
 *
 */

public class ButterKnifeAct extends AppCompatActivity {

    @BindView(R.id.text1)
    TextView text1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_buffknife);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button1)
    public void onClick() {
        Log.e("TAG","button1:onClick");
        text1.setText("11111");
    }


}
