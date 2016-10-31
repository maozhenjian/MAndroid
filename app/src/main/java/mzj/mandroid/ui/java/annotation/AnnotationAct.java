package mzj.mandroid.ui.java.annotation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActAnnotationBinding;

/**
 * creat by mzj on 2016/10/13 22:59
 */
@ContentView(R.layout.act_annotation)
public class AnnotationAct extends Activity{

    @ViewInject(R.id.button)
    Button button;

    @OnClick(R.id.button)
    private void onClick(View view){
        button.setText("我是click后的button");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        button.setText("我是click前的button");
    }



}
