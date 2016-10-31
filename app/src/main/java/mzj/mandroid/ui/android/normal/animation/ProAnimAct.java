package mzj.mandroid.ui.android.normal.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActProAnimBinding;


/**
 * Created by 振坚 on 2016/8/1.
 * 属性动画
 * 属性动画(Property Animation)
 * a. 支持对所有View能更新的属性的动画（需要属性的setXxx()和getXxx()）。
 * b. 更改的是View实际的属性，所以不会影响其在动画执行后所在位置的正常使用。
 *
 * 更多方法点进去看
 * Animator
 * ObjectAnimator
 * ValueAnimator
 * AnimatorSet
 * 的源码
 */
public class ProAnimAct extends BaseActivity<ActProAnimBinding> implements View.OnClickListener{

    @Override
    protected int getLayoutId() {
        return R.layout.act_pro_anim;

    }

    @Override
    protected void initData() {
        binding.setAct(this);
    }

    /**
     * ObjectAnimator内部的工作机制并不是直接对我们传入的属性名进行操作的，而是会去寻找控件的这个属性名对应的get和set方法
     * 例如："alpha"：
     * public void setAlpha(float value);
     * public float getAlpha();
     * <p/>
     * 组合动画：
     * 实现组合动画功能主要需要借助AnimatorSet这个类，这个类提供了一个play()方法，
     * 如果我们向这个方法中传入一个Animator对象(ValueAnimator或ObjectAnimator)将会返回一个AnimatorSet.Builder的实例，AnimatorSet.Builder中包括以下四个方法：
     * after(Animator anim)   将现有动画插入到传入的动画之后执行
     * after(long delay)   将现有动画延迟指定毫秒后执行
     * before(Animator anim)   将现有动画插入到传入的动画之前执行
     * with(Animator anim)   将现有动画和传入的动画同时执行
     **/
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.proBt1:
                /**
                 * 第一个参数就是需要动画的控件；
                 * 第二个参数就是需要控件的属性；
                 * 后面的参数就是不固定长度，想要完成什么样的动画就传入什么值
                 * （这里的效果：透明度：1-->0-->1）
                 * */
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(binding.proTv, "alpha", 1f, 0f, 1f);
                animator1.setDuration(5000); //设置动画时长
                // 设置插值器(用于调节动画执行过程的速度)，插值器相关属性见xml文件：R.anim.rotate_float
                animator1.setInterpolator(new LinearInterpolator());
                // 设置重复次数(缺省为0,表示不重复执行，-1表示重复执行)
                animator1.setRepeatCount(ValueAnimator.INFINITE);
                // 设置重复模式(RESTART或REVERSE),重复次数大于0或INFINITE生效，重新开始或倒序执行
//                animator1.setRepeatMode(ValueAnimator.REVERSE);
                // 设置动画开始的延时时间(200ms)
                animator1.setStartDelay(200);

                animator1.start();

            break;
            case R.id.proBt2:
                /**
                 * 与上面同理：
                 * 改变“rotation”属性，
                 * 0-->360
                 * （这里的效果：360旋转）
                 * */
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(binding.proTv, "rotation", 0f, 360f);
                animator2.setDuration(5000);
                animator2.start();
                break;
            case R.id.proBt3:
                /**
                 * （效果：水平移动）
                 * */
                //getTranslationX()方法来获取到当前TextView的translationX的位置
                float curTranslationX = binding.proTv.getTranslationX();
                Log.i("TAG","curTranslationX: "+curTranslationX);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(binding.proTv, "translationX", curTranslationX, -500f, curTranslationX);
                animator3.setDuration(5000);
                animator3.start();
                animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                       Log.e("TAG",valueAnimator.getAnimatedValue()+"");
                    }
                });
                break;
            case R.id.proBt4:
                /**
                 * （效果：在垂直方向上放大3倍再还原）
                 * */
                ObjectAnimator animator = ObjectAnimator.ofFloat(binding.proTv, "scaleY", 1f, 3f, 1f);
                animator.setDuration(5000);
                animator.start();
                break;
            case R.id.proBt5:
                setAnimatorSet();
                break;
            case R.id.proBt6:
                /**
                 * 设置XML动画：
                 * 调用AnimatorInflater的loadAnimator来将XML动画文件加载进来，
                 * 然后再调用setTarget()方法将这个动画设置到某一个对象上面，
                 * 最后再调用start()方法启动动画就可以了，就是这么简单。
                 * */
                Animator animator4 = AnimatorInflater.loadAnimator(ProAnimAct.this, R.animator.anim_change);
                animator4.setTarget(view);
                animator4.start();

                break;

            case R.id.proBt7:
                ValueAnimator colorAnim=ObjectAnimator.ofInt(view, "backgroundColor",/* */0xffffffff, 0xff00ddff);
                colorAnim.setDuration(3000);
                colorAnim.setEvaluator(new ArgbEvaluator());
                colorAnim.setRepeatCount(ValueAnimator.INFINITE);
                colorAnim.setRepeatMode(ValueAnimator.REVERSE);
                colorAnim.start();
                break;


            case R.id.proBt8:
                Log.e("TAG","proBt8");
                ViewPackage viewPackage=new ViewPackage(binding.proTv);
                ObjectAnimator.ofInt(viewPackage,"width",100,1000).setDuration(1000).start();

                break;
        }
    }



    private void setAnimatorSet(){
        ObjectAnimator anim1=ObjectAnimator.ofFloat(binding.proTv, "rotationX", 360);
        ObjectAnimator anim2=ObjectAnimator.ofFloat(binding.proTv, "rotationY", 180);
        ObjectAnimator anim3=ObjectAnimator.ofFloat(binding.proTv, "rotation",-90);
        ObjectAnimator anim4=ObjectAnimator.ofFloat(binding.proTv, "translationX", 90);
        ObjectAnimator anim5=ObjectAnimator.ofFloat(binding.proTv, "translationY", 90);
        ObjectAnimator anim6=ObjectAnimator.ofFloat(binding.proTv, "scaleX", 1, 0.5F,1);
        ObjectAnimator anim7=ObjectAnimator.ofFloat(binding.proTv, "scaleY", 1, 0.5F,1);
        ObjectAnimator anim8=ObjectAnimator.ofFloat(binding.proTv,"alpha", 1, 0.25F,1);
        AnimatorSet set = new AnimatorSet();
        //动画顺序
//        set.play(anim1).before(anim2);
//        set.play(anim1).with(anim2);
//        set.play(anim1).after(anim2);
        // 组合动画一起执行
        set.playTogether(anim1,anim2, anim3, anim4, anim5, anim6, anim7, anim8);
        set.setDuration(5000);

        /**
         * addListener：对动画进行监听
         * 这个是Animator的方法,因此ObjectAnimator，AnimatorSet，ValueAnimator都可以调用此方法
         *
         * */
        set.addListener(new Animator.AnimatorListener() {


            /**
             * onAnimationStart()方法会在动画开始的时候调用
             */
            @Override
            public void onAnimationStart(Animator animation) {
            }

            /**
             * onAnimationRepeat()方法会在动画重复执行的时候调用
             */
            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            /**
             * onAnimationEnd()方法会在动画结束的时候调用
             */
            @Override
            public void onAnimationEnd(Animator animation) {
            }

            /**
             * onAnimationCancel()方法会在动画被取消的时候调用
             */
            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
        /**
         * 但是也许很多时候我们并不想要监听那么多个事件，可能我只想要监听动画结束这一个事件，那么每次都要将四个接口全部实现一遍就显得非常繁琐。
         * 没关系，为此Android提供了一个适配器类，叫作AnimatorListenerAdapter，使用这个类就可以解决掉实现接口繁琐的问题了
         * **/
        set.addListener(new AnimatorListenerAdapter() {


            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });



        set.start();



    }
}
