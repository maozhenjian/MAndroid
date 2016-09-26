package mzj.mandroid.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 振坚 on 2016/7/28.
 */
public class NetListModel {

    public List<Bean> list;
    public NetListModel() {
        list=new ArrayList<>();
        list.add(new Bean("Retrofit",0));
        list.add(new Bean("Volley",1));
        list.add(new Bean("OkHttp",2));
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
