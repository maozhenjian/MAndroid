package mzj.mandroid.model;

import java.util.List;

import mzj.mandroid.base.BaseFragment;

/**
 * creat by mzj on 2016/9/19 17:02
 *
 */

public class FrgsModel {

    public List<Bean> list;
    public FrgsModel(List<Bean> list){
        this.list=list;
    }

    public static class Bean{
        public Bean(String name, BaseFragment frg) {
            this.name = name;
            this.frg = frg;
        }

        public String name;
        public BaseFragment frg;
    }

}
