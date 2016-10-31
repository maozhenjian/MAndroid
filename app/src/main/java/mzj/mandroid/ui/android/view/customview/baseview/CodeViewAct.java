package mzj.mandroid.ui.android.view.customview.baseview;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActEmptyBinding;
import mzj.mandroid.ui.android.normal.animation.ViewPackage;
import mzj.mandroid.ui.android.view.customview.midview.CircleTextView;
import mzj.mandroid.utils.DeviceUtil;

/**
 * creat by mzj on 2016/10/28 12:36
 */

public class CodeViewAct extends BaseActivity<ActEmptyBinding> {
    private boolean clicked = false;
    private Button myButton;
    private EditText myEditText;
    @Override
    protected int getLayoutId() {
        return R.layout.act_empty;
    }

    @Override
    protected void initData() {
     binding.bt.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
//             addView();
             addPoint();

         }
     });
    }



    /**
     * 在DecorView添加布局
     * @param activity
     * @return
     */
    public static ViewGroup createAnimLayout(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(activity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE - 1);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }


    private void addView(){
        //定义两个控件，button和edittext
        myButton = new Button(this);
//        myButton.setText("Press me");

        myEditText = new EditText(this);
        myEditText.setHint("See me");



        //定义好父容器并设置相关属性
        RelativeLayout myLayout = new RelativeLayout(this);
        myLayout.setBackgroundColor(Color.BLUE);

        LinearLayout.LayoutParams myLayoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);


        //设置Button的布局参数
        RelativeLayout.LayoutParams buttonParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                       100);

        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.addRule(RelativeLayout.CENTER_VERTICAL);

        //设置EditText的布局参数
        RelativeLayout.LayoutParams textParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        textParams.addRule(RelativeLayout.ABOVE, myButton.getId());
        textParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
//
        textParams.setMargins(0, 200, 0, 80);//这里的80是px


        //设置EditText的宽度为指定大小宽度,要相应的dp转化为px
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());

        myEditText.setWidth(px);
        //将布局添加到父容器中
        myLayout.addView(myButton, buttonParams);
        myLayout.addView(myEditText, textParams);

//        createAnimLayout(this).addView(myLayout,myLayoutParams);

        binding.rootLL.addView(myLayout,myLayoutParams);

        int[] endXY = new int[2];
        myButton.getLocationInWindow(endXY);
        Log.e("TAG","myButton:getLocationInWindow:endXY[0]"+endXY[0]);
        Log.e("TAG","myButton：getLocationInWindow:endXY[1]"+endXY[1]);


        int[] SXY = new int[2];
        myButton.getLocationOnScreen(SXY);
        Log.e("TAG","myButton:getLocationOnScreen:SXY[0]"+SXY[0]);
        Log.e("TAG","myButton：getLocationOnScreen:SXY[1]"+SXY[1]);

        final ViewPackage viewPackage=new ViewPackage(myButton);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG","clicked::"+clicked);
                //点击Button之后使其宽度变宽，再次点击恢复之前的wrap_content状态，可以配合属性动画使其变化更加自然
                if (!clicked) {
                    ObjectAnimator.ofInt(viewPackage,"width",100,1000).setDuration(1000).start();
                } else {
                    ObjectAnimator.ofInt(viewPackage,"width",1000,100).
                            setDuration(1000).start();
                }
                clicked = !clicked;
            }
        });
    }


    private void addPoint(){
        LinearLayout animText = new LinearLayout(this);

        animText.setGravity(Gravity.CENTER);
        final TextView text = new TextView(this);
        text.setTextColor(getResources().getColor(android.R.color.white));
        text.setBackgroundResource(R.drawable.homepage_shape_red_filled_circle);
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        text.setGravity(Gravity.CENTER);
        animText.addView(text, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));


        LinearLayout.LayoutParams myLayoutParams =
                new LinearLayout.LayoutParams(
                        50,
                        50);


             /* 起点 */
        int[] startXY = new int[2];
        binding.bt.getLocationInWindow(startXY);
        startXY[0] +=    binding.bt.getWidth() / 2;
        //startXY[0] = tx;
        //startXY[1] = ty - 300;
        int fx = startXY[0];
        int fy = startXY[1];

        myLayoutParams.leftMargin = 500;
        myLayoutParams.topMargin = 500;

        binding.rootLL.addView(animText,myLayoutParams);
    }

}

