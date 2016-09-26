package mzj.mandroid.ui.android.network.retrofit.param;



import java.util.List;

import mzj.mandroid.ui.android.network.retrofit.http.BasicNameValuePair;

/**
 * creat by mzj on 2016/9/22 14:23
 */
public class WorksParam extends BaseParam{

    public WorksParam() {
        this.apiName = "artist.item.getItemList";
    }

    @Override
    public List<BasicNameValuePair> createParams() {
        List<BasicNameValuePair> params =getDefaultParams();
        return params;
    }
}
