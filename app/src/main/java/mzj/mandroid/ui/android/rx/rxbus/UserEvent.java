package mzj.mandroid.ui.android.rx.rxbus;

/**
 * creat by mzj on 2016/11/16 14:30
 */

public class UserEvent {
    long id;
    String name;
    public UserEvent(long id,String name) {
        this.id= id;
        this.name= name;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}