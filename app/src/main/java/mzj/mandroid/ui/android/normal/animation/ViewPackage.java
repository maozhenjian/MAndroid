package mzj.mandroid.ui.android.normal.animation;

import android.view.View;

/**
 * Created by 振坚 on 2016/8/2.
 */
public class ViewPackage  {
    private View view;

    public ViewPackage(View view) {
        this.view = view;
    }

    public void setWidth(int width){
        view.getLayoutParams().width=width;
        view.requestLayout();
    }

    public int getWidth(){
        return view.getLayoutParams().width;
    }


    public void setHeight(int height){
        view.getLayoutParams().height=height;
        view.requestLayout();
    }

    public int getHeight(){
        return view.getLayoutParams().height;
    }
}
