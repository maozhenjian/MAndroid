package mzj.mandroid.ui.android.network.retrofit.param;



import java.util.List;

import mzj.mandroid.ui.android.network.retrofit.http.BasicNameValuePair;

/**
 * creat by mzj on 2016/9/22 14:23
 */
public class ArticleParam extends BaseParam{
    public String type;
    public String is_rec;
    public ArticleParam() {
        this.apiName = "artist.article.getArticleList";
        this.type="";
        this.is_rec="";
    }

    @Override
    public List<BasicNameValuePair> createParams() {
        List<BasicNameValuePair> params =getDefaultParams();
        params.add(new BasicNameValuePair("type",type));
        params.add(new BasicNameValuePair("is_rec",is_rec));
        return params;
    }
}
