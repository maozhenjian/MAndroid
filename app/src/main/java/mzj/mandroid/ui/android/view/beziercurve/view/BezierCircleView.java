package mzj.mandroid.ui.android.view.beziercurve.view;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;

import mzj.mandroid.ui.android.view.customview.baseview.view.MagicCircleView;

/**
 * creat by mzj on 2016/10/31 15:06
 */

public class BezierCircleView extends View {
    /** View的宽度 **/
    private int width;
    /** View的高度，这里View应该是正方形，所以宽高是一样的 **/
    private int height;
    /** View的中心坐标x **/
    private int centerX;
    /** View的中心坐标y **/
    private int centerY;

    private Paint mFillCirclePaint;
    private Path mPath;


    /**带上下两控制点的圆上的点*/
    private VPoint p2,p4;
    /**带左右两控制点的圆上的点*/
    private HPoint p1,p3;
    /** 系数**/
    private float blackMagic = 0.551915024494f;
    /** 原点离第一控制点的距离 **/
    private float c;

    private ValueAnimator valueRadius;
    private AnimatorSet animSetXY;
    public BezierCircleView(Context context) {
        this(context,null);

    }

    public BezierCircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mFillCirclePaint = new Paint();
        mFillCirclePaint.setColor(0xFFfe626d);
        mFillCirclePaint.setStyle(Paint.Style.FILL);
        mFillCirclePaint.setStrokeWidth(1);
        mFillCirclePaint.setAntiAlias(true);

        mPath = new Path();

        valueRadius = ValueAnimator.ofFloat(radiusX);
        valueRadius.setDuration(2000);
        valueRadius.setInterpolator(new OvershootInterpolator());
        valueRadius.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radiusX = (float) animation.getAnimatedValue();
                invalidate();

            }
        });


    }

    public void startAnim(){
        valueRadius.setFloatValues(radiusX,200);
        valueRadius.start();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();
        height = getHeight();

        p2 = new VPoint();
        p4 = new VPoint();

        p1 = new HPoint();
        p3 = new HPoint();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        centerX = width / 2;
        centerY = height / 2;
        radius =200;
        radiusX =200;
        c = radius*blackMagic;

    }

    private float radius;
    private float radiusX;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        /**偏移画布，为把圆显示全*/
        canvas.translate(radius, radius);

//        model0();
        model1();

        mPath.moveTo(p1.x,p1.y);
        mPath.cubicTo(p1.right.x, p1.right.y, p2.bottom.x, p2.bottom.y, p2.x,p2.y);
        mPath.cubicTo(p2.top.x, p2.top.y, p3.right.x, p3.right.y, p3.x,p3.y);
        mPath.cubicTo(p3.left.x, p3.left.y, p4.top.x, p4.top.y, p4.x,p4.y);
        mPath.cubicTo(p4.bottom.x,p4.bottom.y,p1.left.x,p1.left.y,p1.x,p1.y);

        canvas.drawPath(mPath,mFillCirclePaint);

    }

    /**
     * 是否超出边界
     * */
    private boolean ISOUT=false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (event.getX()>2*radius||event.getX()>2*radius){
                    ISOUT=true;
                    break;
                }else {
                    ISOUT=false;
                }

                radiusX=event.getX()-radius;
                break;
            case MotionEvent.ACTION_MOVE:
                if (ISOUT) {
                    break;
                }
                radiusX=event.getX()-radius;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (ISOUT) {
                    break;
                }
                startAnim();
                break;
        }
        return true;
    }

    private void model0(){
        p1.setY(radius);
        p3.setY(-radius);
        p3.x = p1.x = 0;
        p3.left.x =  p1.left.x = -c;
        p3.right.x = p1.right.x = c;

        p2.setX(radius-20);
        p4.setX(-radius);
        p2.y = p4.y = 0;

        p2.top.y =  p4.top.y = -c;
        p2.bottom.y = p4.bottom.y = c;
    }

    private void model1(){
        p1.setY(radius);
        p3.setY(-radius);
        p3.x = p1.x = 0;
        p3.left.x =  p1.left.x = -c;
        p3.right.x = p1.right.x = c;

        p2.setX(radiusX);
        p4.setX(-radius);
        p2.y = p4.y = 0;

        p2.top.y =  p4.top.y = -c;
        p2.bottom.y = p4.bottom.y = c;
    }

    class VPoint{
        public float x;
        public float y;
        public PointF top = new PointF();
        public PointF bottom = new PointF();

        public void setX(float x){
            this.x = x;
            top.x = x;
            bottom.x = x;
        }

        public void adjustY(float offset){
            top.y -= offset;
            bottom.y += offset;
        }

        public void adjustAllX(float offset){
            this.x+= offset;
            top.x+= offset;
            bottom.x+=offset;
        }
    }

    class HPoint{
        public float x;
        public float y;
        public PointF left = new PointF();
        public PointF right = new PointF();

        public void setY(float y){
            this.y = y;
            left.y = y;
            right.y = y;
        }

        public void adjustAllX(float offset){
            this.x +=offset;
            left.x +=offset;
            right.x +=offset;
        }
    }



}
