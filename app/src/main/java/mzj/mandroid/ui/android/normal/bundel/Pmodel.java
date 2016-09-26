package mzj.mandroid.ui.android.normal.bundel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 振坚 on 2016/7/28.
 */
public class Pmodel implements Parcelable {
    private String name;
    private int Id;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.Id = id;
    }

    @Override
    public String toString() {
        return "name::"+name+"Id::"+Id;
    }
    //    内容描述接口，没什么作用
    @Override
    public int describeContents() {
        return 0;
    }
    //写入接口函数，用来打包
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.Id);
    }

    public Pmodel() {
    }

    //这里的顺序必须与writeToParcel一致
    protected Pmodel(Parcel in) {
        this.name = in.readString();
        this.Id = in.readInt();
    }

    public static final Creator<Pmodel> CREATOR = new Creator<Pmodel>() {
        @Override
        public Pmodel createFromParcel(Parcel source) {
            return new Pmodel(source);
        }

        @Override
        public Pmodel[] newArray(int size) {
            return new Pmodel[size];
        }
    };
}
