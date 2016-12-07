package mzj.mandroid.ui.java.reflect;

import android.util.Log;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Properties;

import mzj.mandroid.R;
import mzj.mandroid.base.BaseActivity;
import mzj.mandroid.databinding.ActReflectBinding;

/**
 *
 * creat by mzj on 2016/10/17 20:28
 */

public class ReflectAct extends BaseActivity<ActReflectBinding> {

    int date=100;

    @Override
    protected int getLayoutId() {
        return R.layout.act_reflect;
    }

    @Override
    protected void initData() {
//        creatNewObject();
//        getString();

//        Person p=new Person();
//
//        p.showDate(date);
//
////        这里应该是100
//        Log.e("TAG",date+"");

    }



    private void test1(){
            Mainboard mainboard=new Mainboard();
            mainboard.run();
//            mainboard.usePCI(new SoundCard());


        try {

            Properties properties = new Properties();
            InputStream assets = getAssets().open("pci.properties");
            properties.load(assets);

//            FileInputStream fis = new FileInputStream(configFile);
//            properties.load(getClass().getResourceAsStream("/assets/pci.properties"));

            for (int x=0;x<properties.size();x++){
                String pciName = properties.getProperty("pci"+(x+1));

                Class cls=Class.forName(pciName);
                PCI p=(PCI)cls.newInstance();
                mainboard.usePCI(p);
            }

//            fis.close();

        }  catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     *1:获取字节码对象的方式
     *1：getClass
     * 用这种方式必须明确具体的类，并创建对象
     * */
    public static void getClassObject_1(){
        Person p=new Person();
        Class cls= p.getClass();

        Person p1=new Person();
        Class cls1= p1.getClass();

        Log.e("TAG","cls==cls1:"+(cls==cls1));
    }


    public static void getClassObject_2(){
        Class cls1=Person.class;
        Class cls2=Person.class;
        Log.e("TAG","cls1==cls2:"+(cls1==cls2));
    }

    public static void getClassObject_3(){
        try {
            Class cls=Class.forName("mzj.mandroid.ui.java.reflect.Person");


            Log.e("TAG","cls:"+cls);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void creatNewObject(){
        try {
            Class cls=   Class.forName("mzj.mandroid.ui.java.reflect.Person");

//            获取指定的构造函数对象
            Constructor constructor =cls.getConstructor(int.class,String.class);

//            通过该构造器对象的newInstance()进行对象初始化
            Object obj= constructor.newInstance(18,"jianjian");
            //只能获取公有
//            Field field=cls.getField("age");

//            只获取本类，但包含私有
            Field field=cls.getDeclaredField("age");
//            对私有字段的访问取消权限检测(暴力访问)
            field.setAccessible(true);

            Object o=field.get(obj);

            Log.e("TAG","field:age：："+o);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void getString(){

        //获取类
        try {
            //获取类
            Class c = Class.forName("java.lang.String");
            // 获取所有的属性
            Field[] fields = c.getDeclaredFields();
            StringBuffer sb = new StringBuffer();
            sb.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() + "{\n");
            // 遍历每一个属性
            for (Field field : fields) {
                sb.append("\t");// 空格
                sb.append(Modifier.toString(field.getModifiers()) + " ");// 获得属性的修饰符，例如public，static等等
                sb.append(field.getType().getSimpleName() + " ");// 属性的类型的名字
                sb.append(field.getName() + ";\n");// 属性的名字+回车
            }
            sb.append("}\n");
         Log.e("TAG",sb.toString());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
