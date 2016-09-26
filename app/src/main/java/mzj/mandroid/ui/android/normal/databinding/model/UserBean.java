package mzj.mandroid.ui.android.normal.databinding.model;

/**
 * Created by 振坚 on 2016/8/2.
 */
public class UserBean {
    public String name;
    public int age;
    public String index;
    public String imageUrl;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return "年龄:; "+age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
