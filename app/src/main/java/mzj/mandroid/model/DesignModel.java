package mzj.mandroid.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 振坚 on 2016/7/28.
 */
public class DesignModel {
    public List<Bean> list;
    public DesignModel() {
        list=new ArrayList<>();
        list.add(new Bean("TextInputLayout ",0));

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
