package mzj.mandroid.ui.java.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * creat by mzj on 2016/10/13 23:07
 */

public class ViewInjectUtils {
    public static void inject(Activity activity) {
        injectContentView(activity);
        injectView(activity);
        injectEvent(activity);
    }

    private static void injectContentView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            //如果这个activity上面存在这个注解的话，就取出这个注解对应的value值，其实就是前面说的布局文件。
            int layoutId = contentView.value();
            try {
                Method setViewMethod = clazz.getMethod("setContentView", int.class);
                setViewMethod.invoke(activity, layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void injectView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        //获得activity的所有成员变量
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //获得每个成员变量上面的ViewInject注解，没有的话，就会返回null
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if (viewInject != null) {
                int viewId = viewInject.value();
                View view = activity.findViewById(viewId);
                try {
                    field.setAccessible(true);
                    field.set(activity, view);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static void injectEvent(final Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (final Method method2 : methods) {
            OnClick click = method2.getAnnotation(OnClick.class);
            if (click != null) {

                int[] viewId = click.value();
                method2.setAccessible(true);
                Object listener = Proxy.newProxyInstance(View.OnClickListener.class.getClassLoader(),
                        new Class[]{View.OnClickListener.class}, new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                return method2.invoke(activity, args);
                            }
                        });

                try {
                    for (int id : viewId) {
                        View v = activity.findViewById(id);
                        Method setClickListener = v.getClass().getMethod("setOnClickListener", View.OnClickListener.class);
                        setClickListener.invoke(v, listener);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
