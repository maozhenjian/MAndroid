package mzj.mandroid.ui.android.network.volley;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.List;

import mzj.mandroid.ui.android.network.retrofit.http.BasicNameValuePair;
import mzj.mandroid.ui.android.network.retrofit.http.NameValuePair;
import mzj.mandroid.ui.android.network.volley.base.BaseApi;
import mzj.mandroid.ui.android.network.volley.base.NetCenter;

/**
 * Created by mzj on 2016/8/29.
 * 2.3.1 文章列表 artist.article.getArticleList
 * is_relation	int	是否是关联文章：1是礼品案例文章，0不区分，默认0。	否
 * is_rec	int	是否是推荐文章：1是，0不区分，默认0。首页中获取文章列表时需传递is_rec=1	否
 * genre_id	int	文章分类id，默认0（不区分）。	否
 * type	int	文章类型id，默认0（无效）。1普通文章，2礼品文章，3案例文章	否
 * article_id	int	文章id，默认0（无效）。获取往期文章时传递	否
 * firstRow	int	开始行，默认0	否
 * fetchNum	int	获取行数，默认10	否
 */
public class GetArticleListApi extends BaseApi {
    private ResponseListener responseListener;
    private Context context;
    private GetArticleListParam getArticleListParam;

    public GetArticleListApi(GetArticleListParam getArticleListParam, Context context, ResponseListener responseListener) {
        this.getArticleListParam = getArticleListParam;
        this.apiName = "artist.article.getArticleList";
        this.context = context;
        this.responseListener = responseListener;
    }

    @Override
    public List<NameValuePair> createParams() {
        List<NameValuePair> params = new NetCenter().getDefaultParams();
        if (getArticleListParam.is_relation.length() > 0) {
            params.add(new BasicNameValuePair("is_relation", getArticleListParam.is_relation));
        }
        if (getArticleListParam.is_rec.length() > 0) {
            params.add(new BasicNameValuePair("is_rec", getArticleListParam.is_rec));
        }
        if (getArticleListParam.genre_id.length() > 0) {
            params.add(new BasicNameValuePair("genre_id", getArticleListParam.genre_id));
        }
        if (getArticleListParam.type.length() > 0) {
            params.add(new BasicNameValuePair("type", getArticleListParam.type));
        }

        if (getArticleListParam.article_id.length() > 0) {
            params.add(new BasicNameValuePair("article_id", getArticleListParam.article_id));
        }

        if (getArticleListParam.firstRow.length() > 0) {
            params.add(new BasicNameValuePair("firstRow", getArticleListParam.firstRow));
        }
        if (getArticleListParam.fetch_num.length() > 0) {
            params.add(new BasicNameValuePair("fetchNum", getArticleListParam.fetch_num));
        }


        return params;
    }

    @Override
    public void success(JSONObject response) {
        responseListener.onSuccess(response);
    }

    @Override
    public void fail(JSONObject res) {
        responseListener.onFail(res);
    }

    @Override
    public void error(Exception e) {
        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void volleyError(VolleyError e) {
        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
    }


    public interface ResponseListener {
        void onSuccess(JSONObject response);

        void onFail(JSONObject response);
    }
}
