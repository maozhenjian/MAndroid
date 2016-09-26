package mzj.mandroid.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 振坚 on 2016/7/28.
 */
public class HomePageListModel {
    public List<Bean> list;
    public HomePageListModel() {
        list=new ArrayList<>();
        list.add(new Bean("Budle对象值传递Demo",0));
        list.add(new Bean("DataBinding实例Demo",1));
        list.add(new Bean("关于动画的实例解析",2));
        list.add(new Bean("Android当前主要网络请求框架比较",3));
        list.add(new Bean("Bitmap缓存的具体实现",4));
        list.add(new Bean("RxJava系列全面解析",5));
        list.add(new Bean("2015Design库组件使用详解",6));
        list.add(new Bean("View",7));
    }



    public static class Bean{
        public Bean(String name, int num) {
            this.name = name;
            this.num = num;
        }

        public String name;
        public int num;
    }



}
