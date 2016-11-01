package mzj.mandroid.ui.android.view.beziercurve.view;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import mzj.mandroid.ui.android.normal.animation.ProAnimAct;

/**
 * creat by mzj on 2016/10/31 15:58
 */

public class BezierPopView extends View {
    private AnimatorSet animSetXY;

    private ValueAnimator valueX;
    private ValueAnimator valueY;

    private float mSupX;
    private float mSupY;
    /**
     * 拖拽圆的圆心
     */
    PointF mDragCanterPoint ;
    /**
     * 固定圆的圆心
     */
    PointF mFixCanterPoint = new PointF(250, 250);
    /**
     * 控制点
     */
    PointF mCanterPoint = new PointF(250, 400);

    /**
     * 固定圆的切点
     */
    PointF[] mFixTangentPointes = new PointF[]{new PointF(200, 250),
            new PointF(265, 250)};
    /**
     * 拖拽圆的切点
     */
    PointF[] mDragTangentPoint = new PointF[]{new PointF(230, 450),
            new PointF(270, 450)};
    /**
     * 拖拽圆半径
     */
    float mDragRadius = 50;
    /**
     * 固定圆半径
     */
    float mFixRadius = 100;

    private Paint mPaint;
    private Path mPath;

    public BezierPopView(Context context) {
        super(context);
        init();

    }

    public BezierPopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierPopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init() {
        animSetXY = new AnimatorSet();
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();

        mSupX=250;
        mSupY=250;

        mDragCanterPoint=new PointF(mSupX, mSupY);

        valueX = ValueAnimator.ofFloat(mSupX);
        valueY = ValueAnimator.ofFloat(mSupY);
        animSetXY.playTogether(valueX, valueY);
        valueX.setDuration(500);
        valueY.setDuration(500);
        valueX.setInterpolator(new OvershootInterpolator());
        valueY.setInterpolator(new OvershootInterpolator());
        valueX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSupX = (float) animation.getAnimatedValue();
                mDragCanterPoint.set(mSupX, mSupY);
                invalidate();
            }

        });
        valueY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSupY = (float) animation.getAnimatedValue();
                mDragCanterPoint.set(mSupX, mSupY);
                invalidate();
            }
        });
    }


/**
 * 是否超出边界
 * */
    private boolean ISOUT=false;
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if ((event.getX()-250)>100||(event.getY()-250)>100){
                    ISOUT=true;
                    break;
                }else {
                    ISOUT=false;
                }

                 mSupX = event.getX();
                 mSupY = event.getY();
                updateDragCenterPoint(mSupX, mSupY);
                break;
            case MotionEvent.ACTION_MOVE:
                if (ISOUT){                    break;
                }
                 mSupX = event.getX();
                 mSupY = event.getY();
//                更加手的移动位置绘制拖拽圆的位置
                updateDragCenterPoint(mSupX, mSupY);

                break;

            case MotionEvent.ACTION_UP:
                valueX.setFloatValues(mSupX,250);
                valueY.setFloatValues(mSupY,250);
                animSetXY.start();

                break;
        }
        return true;
    }
     /**
     * 更新拖拽圆圆心
     */
    private void updateDragCenterPoint(float x, float y) {
        mDragCanterPoint.set(x, y);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
//        canvas.translate(0, -statusBarHeight);
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(mFixCanterPoint.x, mFixCanterPoint.y, mFixRadius,
                mPaint);

        float dy = mDragCanterPoint.y - mFixCanterPoint.y;
        float dx = mDragCanterPoint.x - mFixCanterPoint.x;

        mCanterPoint.set((mDragCanterPoint.x + mFixCanterPoint.x) / 2,(mDragCanterPoint.y + mFixCanterPoint.y) / 2);


        if (dx != 0) {
            float k1 = dy / dx;
            float k2 = -1 / k1;
            mDragTangentPoint = getIntersectionPoints(
                    mDragCanterPoint, mDragRadius, (double) k2);
            mFixTangentPointes = getIntersectionPoints(
                    mFixCanterPoint, mFixRadius, (double) k2);
        } else {
            mDragTangentPoint = getIntersectionPoints(
                    mDragCanterPoint, mDragRadius, (double) 0);
            mFixTangentPointes = getIntersectionPoints(
                    mFixCanterPoint, mFixRadius, (double) 0);
        }

        mPath.reset();
        mPath.moveTo(mFixTangentPointes[0].x, mFixTangentPointes[0].y);
        mPath.quadTo(mCanterPoint.x, mCanterPoint.y,
                mDragTangentPoint[0].x, mDragTangentPoint[0].y);
        mPath.lineTo(mDragTangentPoint[1].x, mDragTangentPoint[1].y);
        mPath.quadTo(mCanterPoint.x, mCanterPoint.y,
                mFixTangentPointes[1].x, mFixTangentPointes[1].y);
//        mPath.close();

        canvas.drawPath(mPath, mPaint);


        mPaint.setColor(Color.BLUE);
        mPath.lineTo(mDragTangentPoint[1].x, mDragTangentPoint[1].y);

        canvas.drawCircle(mDragCanterPoint.x, mDragCanterPoint.y,
                mDragRadius, mPaint);
        canvas.drawLine(mFixCanterPoint.x, mFixCanterPoint.y,mDragCanterPoint.x, mDragCanterPoint.y,mPaint);

        canvas.drawLine(mFixTangentPointes[0].x, mFixTangentPointes[0].y,mFixTangentPointes[1].x, mFixTangentPointes[1].y,mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawPoint(mCanterPoint.x,mCanterPoint.y,mPaint);
        canvas.restore();
    }

    /**
     * 获取状态栏高度
     *
     * @param v
     * @return
     */
    public static int getStatusBarHeight(View v) {
        if (v == null) {
            return 0;
        }
        Rect frame = new Rect();
        v.getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        statusBarHeight = getStatusBarHeight(this);
    }

    /**
     * Get the point of intersection between circle and line.
     * 获取 通过指定圆心，斜率为lineK的直线与圆的交点。
     *
     * @param pMiddle The circle center point.
     * @param radius  The circle radius.
     * @param lineK   The slope of line which cross the pMiddle.
     * @return
     */
    public static PointF[] getIntersectionPoints(PointF pMiddle, float radius, Double lineK) {
        PointF[] points = new PointF[2];

        float radian, xOffset = 0, yOffset = 0;
        if (lineK != null) {

            radian = (float) Math.atan(lineK);
            xOffset = (float) (Math.cos(radian) * radius);
            yOffset = (float) (Math.sin(radian) * radius);
        } else {
            xOffset = radius;
            yOffset = 0;
        }
        points[0] = new PointF(pMiddle.x + xOffset, pMiddle.y + yOffset);
        points[1] = new PointF(pMiddle.x - xOffset, pMiddle.y - yOffset);

        return points;

    }
}