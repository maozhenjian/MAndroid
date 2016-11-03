package mzj.mandroid.model;

import java.util.ArrayList;
import java.util.List;

/**
 * creat by mzj on 2016/9/19 17:02
 *
 */

public class TitleModel {

    public List<Bean> list;
    public TitleModel(List<Bean> list){
        this.list=list;
    }

    public static class Bean{
        public Bean(String name, Class<?> cls) {
            this.name = name;
            this.cls = cls;
        }

        public String name;
        public Class<?> cls;
    }

}
