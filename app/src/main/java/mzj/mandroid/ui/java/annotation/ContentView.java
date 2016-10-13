package mzj.mandroid.ui.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * creat by mzj on 2016/10/13 23:02
 *
 * @Retention 这个是决定你Annotation存活的时间的，它包含一个RetationPolicy的value成员变量，用于指定它所修饰的Annotation保留时间，一般有:
 * <p>
 * Retationpolicy.CLASS：编译器将把Annotation记录在Class文件中，不过当java程序执行的时候，JVM将抛弃它。
 * Retationpolicy.SOURCE : Annotation只保留在原代码中，当编译器编译的时候就会抛弃它。
 * Retationpolicy.RUNTIME ： 在Retationpolicy.CLASS的基础上，JVM执行的时候也不会抛弃它，所以我们一般在程序中可以通过反射来获得这个注解，然后进行处理。
 *
 *
 *
 * @Target 这个注解一般用来指定被修饰的Annotation修饰哪些元素，这个注解也包含一个value变量：
 * <p>
 * ElementType.ANNOTATION_TYPE : 指定该Annotation只能修饰Annotation。
 * ElementType.CONSTRUCTOR: 指定只能修饰构造器。
 * ElementType.FIELD: 指定只能成员变量。
 * ElementType.LOCAL_VARIABLE: 指定只能修饰局部变量。
 * ElementType.METHOD: 指定只能修饰方法。
 * ElementType.PACKAGE: 指定只能修饰包定义。
 * ElementType.PARAMETER: 指定只能修饰参数。
 * ElementType.TYPE: 指定可以修饰类，接口，枚举定义。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface  ContentView {
    int value();
}
