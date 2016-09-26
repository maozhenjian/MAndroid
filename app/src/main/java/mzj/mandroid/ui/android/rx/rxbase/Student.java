package mzj.mandroid.ui.android.rx.rxbase;

import java.util.List;

/**
 * creat by mzj on 2016/9/21 17:07
 */

public class Student  {
      public List<String> course;

    public Student(List<String> course) {
        this.course = course;
    }

    public List<String> getCourse() {
        return course;
    }
}
