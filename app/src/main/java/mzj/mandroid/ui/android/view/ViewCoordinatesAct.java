package mzj.mandroid.ui.android.view;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActViewCoordinatesBinding;
import mzj.mandroid.utils.DeviceUtil;


/**
 * creat by mzj on 2016/8/23 11:52
 *
 * http://blog.csdn.net/yanbober/article/details/50419117
 */
public class ViewCoordinatesAct extends BaseActivity<ActViewCoordinatesBinding> implements View.OnClickListener{

    @Override
    protected int getLayoutId() {
        return R.layout.act_view_coordinates;
    }



    @Override
    protected void initData() {
        binding.setAct(this);
        binding.getScreenHeight.setText("屏幕的高为："+ DeviceUtil.getScreenHeight());
        binding.getScreenWidth.setText("屏幕的宽为："+DeviceUtil.getScreenWidth());
        binding.view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //返回值为getLeft()+getTranslationX()，当setTranslationX()时getLeft()不变，getX()变。
                binding.getX.setText("返回值为getLeft()+getTranslationX()  view.getX" + (int)view.getX());
                //返回值为getTop()+getTranslationY()，当setTranslationY()时getTop()不变，getY()变。
                binding.getY.setText("返回值为getTop()+getTranslationY()   view.getY" + (int)view.getY());
                binding.getTranslationX.setText("view.getTranslationX" +(int) view.getTranslationX());
                binding.getTranslationY.setText("view.getTranslationY" +(int) view.getTranslationY());


                //当前触摸事件距离整个屏幕左边的距离
                binding.getRawX.setText("当前触摸事件距离整个屏幕左边的距离motionEvent.getRawX" +(int) motionEvent.getRawX());
                //当前触摸事件距离整个屏幕顶边的距离
                binding.getRawY.setText("当前触摸事件距离整个屏幕顶边的距离motionEvent.getRawY" +(int) motionEvent.getRawY());
                //当前触摸事件距离当前View左边的距离
                binding.MGetX.setText("当前触摸事件距离当前View左边的距离motionEvent.getX" + (int)motionEvent.getX());
                //当前触摸事件距离当前View顶边的距离
                binding.MGetY.setText("当前触摸事件距离当前View顶边的距离motionEvent.getY" + (int)motionEvent.getY());
                return false;
            }
        });
    }


    /**
     * 特别注意：这些方法最好在Activity的onWindowFocusChanged ()方法或者之后调运，因为只有这时候才是真正的显示OK
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //  * getLeft()	返回View自身左边到父布局左边的距离
        //  * getTop()	返回View自身顶边到父布局顶边的距离
        //  * getRight()	返回View自身右边到父布局左边的距离
        //  * getBottom()	返回View自身底边到父布局顶边的距离
        binding.getTop.setText( "返回View自身左边到父布局左边的距离View:getTop()::"+binding.view.getTop());
        binding.getBottom.setText( "返回View自身顶边到父布局顶边的距离View:getBottom()::"+binding.view.getBottom());
        binding.getRight.setText( "返回View自身右边到父布局左边的距离View:getRight()::"+binding.view.getRight());
        binding.getLeft.setText( "返回View自身底边到父布局顶边的距离View:getLeft()::"+binding.view.getLeft());


        binding.getWidth.setText("View:getWidth()::"+binding.view.getWidth());
        binding.getHeight.setText("View:getHeight()::"+binding.view.getHeight());


    }



    /**
     * View的静态坐标方法	解释
     * getLeft()	返回View自身左边到父布局左边的距离
     * getTop()	返回View自身顶边到父布局顶边的距离
     * getRight()	返回View自身右边到父布局左边的距离
     * getBottom()	返回View自身底边到父布局顶边的距离
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.view:
                //  * getLeft()	返回View自身左边到父布局左边的距离
                //  * getTop()	返回View自身顶边到父布局顶边的距离
                //  * getRight()	返回View自身右边到父布局左边的距离
                //  * getBottom()	返回View自身底边到父布局顶边的距离
                binding.getTop.setText( "View:getTop()::"+binding.view.getTop());
                binding.getBottom.setText( "View:getBottom()::"+binding.view.getBottom());
                binding.getRight.setText( "View:getRight()::"+binding.view.getRight());
                binding.getLeft.setText( "View:getLeft()::"+binding.view.getLeft());

                Log.e("TAG","获取当前滑动位置偏移量。view.getScrollY:before"+view.getScrollY());

            //水平方向挪动View，offset为正则x轴正向移动，移动的是整个View，getLeft()会变的，自定义View很有用。
//                view.offsetTopAndBottom(50);
                view.scrollBy(0,50);

                Log.e("TAG","获取当前滑动位置偏移量。view.getScrollY:after"+view.getScrollY());
                break;
        }
    }
}
