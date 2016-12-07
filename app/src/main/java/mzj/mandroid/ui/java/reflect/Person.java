package mzj.mandroid.ui.java.reflect;

import android.util.Log;

/**
 * Creat by mzj on 2016/12/2 16:43
 * Des:
 */

public class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person() {
        this.name="mao";
        this.age=20;
    }

    public void show(){
        Log.e("TAG","show::"+age);

    }

    public void showDate(int i){
        i=200;
        Log.e("TAG","show::"+i);

    }

    public static void say(){
        Log.e("TAG","say::");

    }


}
