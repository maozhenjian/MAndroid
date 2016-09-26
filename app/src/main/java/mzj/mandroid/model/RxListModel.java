package mzj.mandroid.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 振坚 on 2016/7/28.
 */
public class RxListModel {
    public List<Bean> list;
    public RxListModel() {
        list=new ArrayList<>();
        list.add(new Bean("RxJava终极缓存实例",0));
        list.add(new Bean("RxBus使用实例",1));
        list.add(new Bean("Rx???",2));
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
