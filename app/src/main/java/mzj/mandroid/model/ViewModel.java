package mzj.mandroid.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 振坚 on 2016/7/28.
 */
public class ViewModel {
    public List<Bean> list;
    public ViewModel() {
        list=new ArrayList<>();
        list.add(new Bean("View坐标",0));
        list.add(new Bean("View事件分发机制",1));
        list.add(new Bean("贝塞尔曲线",2));
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
