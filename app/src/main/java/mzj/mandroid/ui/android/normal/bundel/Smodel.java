package mzj.mandroid.ui.android.normal.bundel;

import java.io.Serializable;

/**
 * Created by 振坚 on 2016/7/28.
 */
public class Smodel implements Serializable {
    private String name;
    private int Id;

    public Smodel(String name, int id) {
        this.name = name;
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "name:"+name+"id:"+getId();
    }
}
