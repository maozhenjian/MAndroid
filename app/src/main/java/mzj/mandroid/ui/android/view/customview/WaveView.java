package mzj.mandroid.ui.android.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * creat by mzj on 2016/9/10 09:25
 */

public class WaveView extends View {
    private Paint mPaint;
    private int X;
    private int Y;
    private int width;
    private int height;
    private int angle;
    double lineX = 0;
    double lineY1 = 0;
    public WaveView(Context context) {
        super(context);
        intView();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        intView();
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intView();
    }


    private void intView(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width=getWidth();
        height=getHeight();


//        for (int i=0;i<width;i++){
//            Y= (int) Math.sin(i);
//            canvas.drawLine( i, Y,
//                    i + 1, (int) Math.sin((i+1)), mPaint);
//        }

        for (int i = 0; i < width; i++) {
                lineX = i;
                lineY1 = 100*Math.sin(((i+angle)*Math.PI/ 90));
            canvas.drawLine((float) lineX, (float) (lineY1 + height / 2),
                    (float) lineX + 1, (float) (100 * Math.sin(((lineX + 1) * Math.PI / 90)) + height / 2), mPaint);
//            canvas.drawLine((int) lineX, (int) (lineY2 + height / 1.5),
//                    (int) lineX + 1, (int) (lineY3 + height / 1.5), mPaint);
//            canvas.drawLine((int) lineX, (int) (lineY3 + height / 1.5),
//                    (int) lineX + 1, height, mPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                angle++;
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
