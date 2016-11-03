package mzj.mandroid.ui.android.view.customview.midview.cart;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;



import mzj.mandroid.R;
import mzj.mandroid.base.App;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActAddCartBinding;

/**
 * creat by mzj on 2016/10/28 10:51
 */

public class AddToCartViewAct extends BaseActivity<ActAddCartBinding> implements View.OnClickListener{

    @Override
    protected int getLayoutId() {
        return R.layout.act_add_cart;
    }

    @Override
    protected void initData() {
        binding.addBt.setOnClickListener(this); setAnimatorSet();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_bt:

                setAnimatorSet();
                break;
        }

    }




    private void setAnimatorSet(){
        LinearLayout animText = new LinearLayout(this);
        animText.setGravity(Gravity.CENTER);
        final TextView text = new TextView(this);
        text.setTextColor(getResources().getColor(android.R.color.white));
        text.setBackgroundResource(R.drawable.homepage_shape_red_filled_circle);
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        text.setGravity(Gravity.CENTER);
        animText.addView(text, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout.LayoutParams myLayoutParams = new LinearLayout.LayoutParams(50, 50);





       /* 起点 */
        int[] startXY = new int[2];
        binding.addBt.getLocationInWindow(startXY);
        startXY[0] +=  binding.addBt.getWidth() / 2;
        //startXY[0] = tx;
        //startXY[1] = ty - 300;
        int fx = startXY[0];
        int fy = startXY[1];

        Log.e("TAG","fx:"+fx);
        Log.e("TAG","fy:"+fy);



        /* 终点 */
        int[] endXY = new int[2];
        binding.cartTv.getLocationInWindow(endXY);
        endXY[0] += binding.cartTv.getWidth() / 2;
        int tx = endXY[0];
        int ty = endXY[1];

        int distenceX=tx-fx;


         myLayoutParams.leftMargin = fx;
        myLayoutParams.topMargin = fy;

        final ViewGroup viewGroup= createAnimLayout(this);
        viewGroup.addView(animText,myLayoutParams);


        ObjectAnimator anim4=ObjectAnimator.ofFloat(animText, "translationX",0.0f,-500.0f);
        ObjectAnimator anim5=ObjectAnimator.ofFloat(animText, "translationY", 0.0f,-500.0f,0.0f);
        anim4.setInterpolator(new LinearInterpolator());
        anim5.setInterpolator(new BounceInterpolator());
        AnimatorSet set = new AnimatorSet();
        set.playTogether(anim4, anim5);
        set.setDuration(1500);

        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewGroup.removeAllViews();
            }
        });

//        AnimationSet set = new AnimationSet(false);
//
//        TranslateAnimation translateAnimation2 = new TranslateAnimation(0,0,0,-500);
//        translateAnimation2.setInterpolator(new LinearInterpolator());
//        translateAnimation2.setDuration(1500);
//        set.addAnimation(translateAnimation2);
//
//        TranslateAnimation translateAnimation1 = new TranslateAnimation(0, -1000, 0,0);
//        translateAnimation1.setInterpolator(new LinearInterpolator());
//        translateAnimation1.setDuration(3000);
//        set.addAnimation(translateAnimation1);
//
//        set.setFillAfter(false);
//        animText.startAnimation(set);
//        set.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                viewGroup.removeAllViews();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
    }


    /**
     * 将布局设置到根View
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
//        animLayout.setId(Integer.MAX_VALUE - 1);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

}
