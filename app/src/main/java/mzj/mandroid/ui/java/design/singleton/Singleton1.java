package mzj.mandroid.ui.java.design.singleton;

/**
 * creat by mzj on 2016/10/20 14:25
 * 懒汉模式（线程安全）
 * 这种写法能够在多线程中很好的工作，但是每次调用getInstance方法时都需要进行同步，
 * 造成不必要的同步开销，而且大部分时候我们是用不到同步的，所以不建议用这种模式。
 */

public class Singleton1 {
    private static Singleton1 instance;
    private Singleton1 (){

    }

    public static synchronized Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }

}
