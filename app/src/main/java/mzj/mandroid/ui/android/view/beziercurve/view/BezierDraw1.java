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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * creat by mzj on 2016/9/8 13:37
 */
public class BezierDraw1 extends View {
    private ValueAnimator valueX;
    private ValueAnimator valueY;
    private AnimatorSet animSetXY;
    private int mSupX;
    private int mSupY;
    private int width;
    private int height;
    private int startX;
    private int startY;
    private int mode = 1;

    private Point start, end, control1, control2;
    public BezierDraw1(Context context) {
        super(context);
        initView();
    }

    public BezierDraw1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BezierDraw1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        animSetXY = new AnimatorSet();

        valueX = ValueAnimator.ofInt(mSupX);
        valueY = ValueAnimator.ofInt(mSupY);
        animSetXY.playTogether(valueX, valueY);
        valueX.setDuration(500);
        valueY.setDuration(500);
        valueX.setInterpolator(new OvershootInterpolator());
        valueY.setInterpolator(new OvershootInterpolator());
        valueX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSupX = (int) animation.getAnimatedValue();
                invalidate();
            }

        });
        valueY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSupY = (int) animation.getAnimatedValue();
                invalidate();

            }
        });
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("TAG","w"+w);
        Log.e("TAG","h"+h);
        Log.e("TAG","oldw"+oldw);
        Log.e("TAG","oldh"+oldh);

        mSupX=w/2;
        mSupY=h/2;


        start = new Point(0, h/2);
        end = new Point(w, h/2);
        control1 = new Point(w/2, 0);
        control2 = new Point(w/2, h);
    }


    public void setMode(int mode){
        this.mode=mode;
    }

    /**
     * mPath.cubicTo(x1, y1, x2, y2, x3, y3) (x1,y1) 为控制点，(x2,y2)为控制点，(x3,y3) 为结束点。
     * quadTo( x2, y2, x3, y3) (x2,y2)为控制点，(x3,y3) 为结束点。
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

         width=getWidth();
         height=getHeight();

        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setAntiAlias(true);
        p.setStrokeWidth(10);

        Path path = new Path();
        Path path2 = new Path();
        //设置起点
        path.moveTo(start.x, start.y);
        path2.moveTo(0, height/2);
        path2.quadTo(mSupX, mSupY, width, height/2);
        path.cubicTo(control1.x,control1.y,control2.x,control2.y, end.x, end.y);
        canvas.drawPath(path,p);
        canvas.drawPath(path2,p);
        canvas.drawPoint(mSupX,mSupY,p);

        p.setColor(Color.RED);
        canvas.drawLine(start.x, start.y, control1.x,control1.y, p);
        canvas.drawLine(control1.x,control1.y,control2.x,control2.y, p);
        canvas.drawLine(control2.x,control2.y,end.x, end.y,p);
        p.setColor(Color.BLUE);
        canvas.drawPoint(control1.x,control1.y,p);
        canvas.drawPoint(control2.x,control2.y,p);
        canvas.drawPoint(end.x,end.y,p);
        canvas.drawPoint(start.x,startY,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG","mode:"+mode);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                if (mode == 1) {
//                    getParent().requestDisallowInterceptTouchEvent(true);
                    animSetXY.cancel();
                    mSupX = (int) event.getX();
                    mSupY = (int) event.getY();
                } else if (mode == 2) {
                    control1.x = (int) event.getX();
                    control1.y = (int) event.getY();
                } else {
                    control2.x = (int) event.getX();
                    control2.y = (int) event.getY();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if (mode==1) {
                    mSupX = (int) event.getX();
                    mSupY = (int) event.getY();
                    invalidate();
                }else if (mode==2){
                    control1.x = (int) event.getX();
                    control1.y = (int) event.getY();
                    invalidate();
                }else {
                    control2.x = (int) event.getX();
                    control2.y = (int) event.getY();
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mode==1) {
                valueX.setIntValues(mSupX,width/2);
                valueY.setIntValues(mSupY,height/2);
                animSetXY.start();
                }else if (mode==2){

                }else {

                }
                break;
        }
        return true;
    }
}
