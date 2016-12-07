package mzj.mandroid.ui.android.network.volley.base;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mzj.mandroid.base.App;
import mzj.mandroid.ui.android.network.retrofit.http.BasicNameValuePair;
import mzj.mandroid.ui.android.network.retrofit.http.NameValuePair;
import mzj.mandroid.utils.FormatUtil;

/**
 * Created by JRC on 15/11/23.
 *
 */
abstract public class BaseApi {
    protected String apiName;
    public Type type;
    public Context context;



    public void call()
    {

        String url= NetCenter.BASEPATH;
        //set api
        List<NameValuePair> params = createParams();
        params.add(new BasicNameValuePair("api_name",apiName));

        //param encypt
        //字符串拼接
        params= NetCenter.paramEncrypt(params);
        HashMap<String,String> mMap=new HashMap<String,String>();
        for(int i=0;i<params.size();i++) {
            mMap.put(params.get(i).getName(),params.get(i).getValue());
        }
                BaseVolleyRequest jsonObjectRequest=new BaseVolleyRequest(url,new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int code=response.getInt("code");
                    if (code == 0) {
                        success(response);

                    }
                    else
                    {
//                        //还没有登录就转到登录界面
//                        if(response.getString("error_msg").equals("无效的PHPSESSID")){
//                            Intent it = new Intent(App.getApplication().getApplicationContext(), SignShowAct.class);
//                            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            App.getApplication().startActivity(it);
//                        }
//                        if(App.debug) {
//                            try {
//                                ToastUtil.showShortToast(App.getApplication(),response.getInt("code") + ":" + response.getString("error_msg"));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }else{
////                            ToastUtil.showShortToast(App.getApplication(),"对不起,访问数据出错");
//                        }
                        fail(response);
                    }
                } catch (Exception e) {
                    error(e);
                }

            }
        }
                ,new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyError(error);
                FormatUtil.printJson(getClass().getSimpleName()+"error::"+error.toString());
            }
        },mMap)
        {
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError
            {
                HashMap<String,String> headers=new HashMap<String,String>();
                headers.put("CUSTOM_HEADER", "Yahoo");
                headers.put("ANOTHER_CUSTOM_HEADER", "Google");
                headers.put("Cookie","PHPSESSION="+ NetCenter.PHPSESSID);
                return headers;
            }
        };
        App app = App.getApplication();
        app.addToRequestQueue(jsonObjectRequest, apiName);
    }

    //创建参数
    public abstract List<NameValuePair> createParams();
    //成功回调
    public abstract void success(JSONObject response);
    //失败回调
    public abstract void fail(JSONObject res);
    //错误回调
    public abstract void error(Exception e);
    //Volley错误回调
    public abstract void volleyError(VolleyError e);
}
