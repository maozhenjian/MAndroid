/*
 * Copyright © Yan Zhenjie. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mzj.mandroid.ui.android.view.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by Yan Zhenjian on 2016/11/4.
 */
public class ScrollLayout extends LinearLayout {

    private Scroller mScroller;
    private int mLastX;
    private int mLastY;

    public ScrollLayout(Context context) {
        this(context, null, 0);
    }

    public ScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                if (!mScroller.isFinished()) { // 如果上次的动画没有执行完就取消。
                    mScroller.abortAnimation();
                }
                mLastX = x;
                mLastY = y;
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                int dy = y - mLastY;
                int dx = x - mLastX;

                mLastX = x;
                mLastY = y;

                scrollBy(-dx, -dy);
                return true;
            }
            case MotionEvent.ACTION_UP:

            case MotionEvent.ACTION_CANCEL: {
                // XY都从滑动的距离回去，最后一个参数是多少毫秒内执行完这个动作。
                mScroller.startScroll(getScrollX(), getScrollY(), -getScrollX(), -getScrollY(), 1000);
                invalidate();
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 这个方法在调用了invalidate()后被回调。
     */
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) { // 判断滚动是否完成。
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();// 再次调用computeScroll。
        }
    }
}
