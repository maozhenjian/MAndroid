package mzj.mandroid.ui.android.view.customview;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * creat by mzj on 2016/9/8 21:36
 */

public class MeasureView extends View {
    private int mColor= Color.RED;
    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    public MeasureView(Context context) {
        this(context,null);
    }

    public MeasureView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        TypedArray typedArray=context.obtainStyledAttributes(attrs, mzj.mandroid.R.styleable.CircleView);
//        mColor=typedArray.getColor(mzj.mandroid.R.styleable.CircleView_circle_color,Color.RED);
//        typedArray.recycle();
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
        int paddingLeft=getPaddingLeft();
        int paddingRight=getPaddingRight();
        int paddingTop=getPaddingTop();
        int paddingBottom=getPaddingBottom();
        int width=getWidth()-paddingLeft-paddingRight;
        int height=getHeight()-paddingTop-paddingBottom;
        Log.e("TAG","width"+width);
        Log.e("TAG","height"+height);
        int radius=Math.min(width,height)/2;//半径
        canvas.drawCircle(paddingLeft+width/2,paddingTop+height/2,radius,mPaint);

    }

    private int  measureWidth(int measureSpec){
        int result=0;
        int specMode=MeasureSpec.getMode(measureSpec);
        int specSize=MeasureSpec.getSize(measureSpec);
        if (specMode==MeasureSpec.EXACTLY){
            result=specSize;
        }else {
            result=200;
            if (specMode==MeasureSpec.AT_MOST){
                result=Math.min(specSize,result);
            }
        }
        return result;
    }

    private int  measureHeight(int measureSpec){
        int result=0;
        int specMode=MeasureSpec.getMode(measureSpec);
        int specSize=MeasureSpec.getSize(measureSpec);
        if (specMode==MeasureSpec.EXACTLY){
            result=specSize;
        }else {
            result=200;
            if (specMode==MeasureSpec.AT_MOST){
                result=Math.min(specSize,result);
            }
        }
        return result;
    }
}
