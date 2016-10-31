package mzj.mandroid.ui.java.design.singleton;

/**
 * creat by mzj on 2016/10/20
 * 双重检查模式 （DCL）
 */

public class Singleton4 {
    /**
     * 一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义：
     * <p>
     * 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
     * 禁止进行指令重排序。
     */
    private volatile static Singleton4 instance;

    private Singleton4() {

    }

    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }


}
