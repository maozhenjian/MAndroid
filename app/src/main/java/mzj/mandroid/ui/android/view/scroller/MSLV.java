package mzj.mandroid.ui.android.view.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * creat by mzj on 2016/11/4 09:56
 */

public class MSLV extends LinearLayout {
    /**
     * 用于完成滚动操作的实例
     */
    private Scroller mScroller;

    /**
     * 界面可滚动的左边界
     */
    private int border;
    private int bottom;
    public MSLV(Context context) {
        this(context, null);
    }

    public MSLV(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MSLV(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 第一步，创建Scroller的实例
        mScroller = new Scroller(context);
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        border = getChildAt(0).getTop();
        bottom = getChildAt(0).getBottom();
        Log.e("TAG","border:"+border);
        Log.e("TAG","bottom:"+bottom);
    }


    private float disX;
    private float disY;
    private float mYMove;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG","onTouchEvent:"+event.getAction()+super.onTouchEvent(event));
        switch (event.getAction()) {

            case MotionEvent.ACTION_UP:
                /**    调用startScroll()方法来初始化滚动数据并刷新界面。startScroll()方法接收四个参数
                 // 第一个参数是滚动开始时X的坐标，
                 第二个参数是滚动开始时Y的坐标，
                 // 第三个参数是横向滚动的距离，正值表示向左滚动
                 ，第四个参数是纵向滚动的距离，正值表示向上滚动*/
                mScroller.startScroll(0,getScrollY(),0, border, 3000);
                invalidate();

                break;
        }
        return true;

    }


    @Override
    public void computeScroll() {
        /**      第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
         。在整个后续的平滑滚动过程中，computeScroll()方法是会一直被调用的，
         因此我们需要不断调用Scroller的computeScrollOffset()方法来进行判断滚动操作是否已经完成了，
         如果还没完成的话，那就继续调用scrollTo()方法，并把Scroller的curX和curY坐标传入，
         然后刷新界面从而完成平滑滚动的操作*/
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
