package mzj.mandroid.ui.android.network.retrofit.http;

/**
 * creat by mzj on 2016/9/23 12:05
 */

public class BasicNameValuePair implements NameValuePair {
    private String name;
    private String value;

    public BasicNameValuePair(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }


}
