package mzj.mandroid.ui.android.view.scroller;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by 振坚 on 2016/1/21.
 */
public class ScrollerLayout extends ViewGroup {

    /**
     * 用于完成滚动操作的实例
     */
    private Scroller mScroller;

    /**
     * 判定为拖动的最小移动像素数
     */
    private int mTouchSlop;

    /**
     * 手机按下时的屏幕坐标
     */
    private float mXDown;

    /**
     * 手机当时所处的屏幕坐标
     */
    private float mXMove;

    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mXLastMove;

    /**
     * 界面可滚动的左边界
     */
    private int leftBorder;

    /**
     * 界面可滚动的右边界
     */
    private int rightBorder;

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 第一步，创建Scroller的实例
        mScroller = new Scroller(context);
        // 获取TouchSlop值,getScaledTouchSlop是一个距离，表示滑动的时候，手的移动要大于这个距离才开始移动控件。
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 为ScrollerLayout中的每一个子控件测量大小
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 一、参数说明：
     * 1）参数changed表示view有新的尺寸或位置；
     * 2）参数l表示相对于父view的Left位置；
     * 3）参数t表示相对于父view的Top位置；
     * 4）参数r表示相对于父view的Right位置；
     * 5）参数b表示相对于父view的Bottom位置。.
     **/
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                // 为ScrollerLayout中的每一个子控件在水平方向上进行布局
                childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
            }

            // 初始化左右边界值，第一个子控件相对于父控件左右边界距离的值
            leftBorder = getChildAt(0).getLeft();
            Log.e("TAG", "leftBorder" + leftBorder);
            rightBorder = getChildAt(getChildCount() - 1).getRight();
            Log.e("TAG", "rightBorder" + rightBorder);
        }
    }


    /**
     * 在这个方法中我们记录了用户手指按下时的X坐标位置，
     * 以及用户手指在屏幕上拖动时的X坐标位置，
     * 当两者之间的距离大于TouchSlop值时，就认为用户正在拖动布局，然后我们就将事件在这里拦截掉，
     * 阻止事件传递到子控件当中。
     * 那么当我们把事件拦截掉之后，就会将事件交给ScrollerLayout的onTouchEvent()方法来处理。
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                手按下时相对于屏幕的横坐标X
                mXDown = ev.getRawX();
//                记录下这次按下的位置
                mXLastMove = mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
//                移动时的相对于屏幕的横坐标X
                mXMove = ev.getRawX();
//                偏移量
                float diff = Math.abs(mXMove - mXDown);
//
                mXLastMove = mXMove;
                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if (diff > mTouchSlop) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                /*
                 * 手当时所处的屏幕X坐标
                 */
                mXMove = event.getRawX();
                //移动的X的距离
                int scrolledX = (int) (mXLastMove - mXMove);

                //边界保护
                if (getScrollX() + scrolledX < leftBorder) {
                    Log.e("TAG", "leftBorder" + leftBorder);
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }


                scrollBy(scrolledX, 0);
                mXLastMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面

                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                Log.e("TAG", "getWidth" + getWidth());
                Log.e("TAG", "targetIndex" + targetIndex);

                int dx = targetIndex * getWidth() - getScrollX();
                //第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                /**    调用startScroll()方法来初始化滚动数据并刷新界面。startScroll()方法接收四个参数
                 // 第一个参数是滚动开始时X的坐标，
                 第二个参数是滚动开始时Y的坐标，
                 // 第三个参数是横向滚动的距离，正值表示向左滚动
                 ，第四个参数是纵向滚动的距离，正值表示向上滚动*/
                mScroller.startScroll(getScrollX(), 0, dx, 0, 500);
//                刷新界面
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
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