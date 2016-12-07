package mzj.mandroid.ui.android.network.volley;

/**
 * creat by mzj on 2016/9/9 09:05
 */

public class GetArticleListParam {
    public String is_relation;
    public String is_rec;
    public String genre_id;
    public String type;
    public String article_id;
    public String firstRow;
    public String fetch_num;

    public GetArticleListParam() {
        this.is_relation="";
        this.is_rec="";
        this.genre_id="";
        this.firstRow="";
        this.fetch_num="";
        this.type="";
        this.article_id="";
    }
}
