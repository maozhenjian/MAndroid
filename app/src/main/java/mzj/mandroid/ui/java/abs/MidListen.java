package mzj.mandroid.ui.java.abs;


import android.util.Log;

/**
 * creat by mzj on 2016/10/12 10:10
 */

public class MidListen {
    private TestListener testListeners;


    void setTestListener( TestListener testListener){
        this.testListeners=testListener;
        Log.e("TAG","testListener.addNum(5);"+testListeners.addNum(5));
    }




}
