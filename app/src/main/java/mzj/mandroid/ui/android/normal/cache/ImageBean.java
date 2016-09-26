package mzj.mandroid.ui.android.normal.cache;

import java.util.List;

/**
 * Created by 振坚 on 2016/8/4.
 */
public class ImageBean {

    /**
     * status : 1
     * data : [{"id":1,"name":"Tony老师聊shell\u2014\u2014环境变量配置文件","picSmall":"http://img.mukewang.com/55237dcc0001128c06000338-300-170.jpg","picBig":"http://img.mukewang.com/55237dcc0001128c06000338.jpg","description":"为你带来shell中的环境变量配置文件","learner":12312},{"id":2,"name":"数学知识在CSS动画中的应用","picSmall":"http://img.mukewang.com/55249cf30001ae8a06000338-300-170.jpg","picBig":"http://img.mukewang.com/55249cf30001ae8a06000338.jpg","description":"数学知识与CSS结合实现酷炫效果","learner":45625},{"id":3,"name":"Oracle数据库开发必备利器之PL/SQL基础","picSmall":"http://img.mukewang.com/5523711700016d1606000338-300-170.jpg","picBig":"http://img.mukewang.com/5523711700016d1606000338.jpg","description":"Oracle数据库高级开发必备的基础。","learner":41236},{"id":4,"name":"Android见证消息推送时刻进阶篇","picSmall":"http://img.mukewang.com/551e470500018dd806000338-300-170.jpg","picBig":"http://img.mukewang.com/551e470500018dd806000338.jpg","description":"Android消息推送就在眼前，Come on","learner":45456},{"id":5,"name":"Avalon探索之旅基础教程\u2014\u2014复杂绑定","picSmall":"http://img.mukewang.com/551de0570001134f06000338-300-170.jpg","picBig":"http://img.mukewang.com/551de0570001134f06000338.jpg","description":"前端迷您MVVM框架，Avalon复杂绑定属性篇。","learner":56556},{"id":6,"name":"Android-Service系列之断点续传下载","picSmall":"http://img.mukewang.com/552640c300018a9606000338-300-170.jpg","picBig":"http://img.mukewang.com/552640c300018a9606000338.jpg","description":"想升职加薪么？本章课程你值得拥有","learner":48996},{"id":7,"name":"JUnit\u2014Java单元测试必备工具","picSmall":"http://img.mukewang.com/551b92340001c9f206000338-300-170.jpg","picBig":"http://img.mukewang.com/551b92340001c9f206000338.jpg","description":"Java单元测试利器!","learner":13210},{"id":8,"name":"细说Java多线程之内存可见性","picSmall":"http://img.mukewang.com/5518c3d7000175af06000338-300-170.jpg","picBig":"http://img.mukewang.com/5518c3d7000175af06000338.jpg","description":"用两种方式实现内存可见性","learner":15051},{"id":9,"name":"CSS动画实用技巧","picSmall":"http://img.mukewang.com/551b98ae0001e57906000338-300-170.jpg","picBig":"http://img.mukewang.com/551b98ae0001e57906000338.jpg","description":"教你使用CSS实现惊艳的动画效果！","learner":15210},{"id":10,"name":"C++远征之起航篇","picSmall":"http://img.mukewang.com/550b86560001009406000338-300-170.jpg","picBig":"http://img.mukewang.com/550b86560001009406000338.jpg","description":"C++亮点尽在其中","learner":84545}]
     * msg : 成功
     */

    public int status;
    public String msg;
    /**
     * id : 1
     * name : Tony老师聊shell——环境变量配置文件
     * picSmall : http://img.mukewang.com/55237dcc0001128c06000338-300-170.jpg
     * picBig : http://img.mukewang.com/55237dcc0001128c06000338.jpg
     * description : 为你带来shell中的环境变量配置文件
     * learner : 12312
     */

    public List<DataBean> data;

    public static class DataBean {
        public int id;
        public String name;
        public String picSmall;
        public String picBig;
        public String description;
        public int learner;
    }

    public List<DataBean> getData() {
        return data;
    }
}
