package mzj.mandroid.ui.android.view.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * create by mzj on 2016/8/23 15:48
 */
public class MoveView extends View {

    public MoveView(Context context) {
        super(context);
    }

    public MoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float mXMove;
    private float mXLastMove;
    private float mYMove;
    private float mYLastMove;
    private float SY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXLastMove = event.getRawX();
                mYLastMove = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                mYMove = event.getRawY();

                if (!(getX() < 0)) {
                    offsetLeftAndRight((int) (mXMove - mXLastMove));
                } else {
                    offsetLeftAndRight((int) (Math.abs(getX())));
                }

                SY = getScaleX();
                if (mXMove - mXLastMove > 0) {
                    setScaleX(SY + 0.03f);
                } else {
                    setScaleX(SY - 0.03f);
                }
                offsetTopAndBottom((int) (mYMove - mYLastMove));
                mXLastMove = mXMove;
                mYLastMove = mYMove;
                break;


        }

        return true;
    }
}
