package mzj.mandroid.ui.android.network.retrofit.param;

import android.util.Log;



import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mzj.mandroid.ui.android.network.retrofit.http.BasicNameValuePair;
import mzj.mandroid.ui.android.network.retrofit.http.NameValuePair;
import mzj.mandroid.utils.StringUtils;

/**
 * creat by mzj on 2016/9/22 15:04
 */

public abstract class BaseParam {

    public String token;
    public String appid="1";
    public String PHPSESSID="4dlres5tnvdrd37q9gfibs2966";
    public String apiName;

    public String getToken(List<BasicNameValuePair> params) {
        Collections.sort(params, new Comparator<NameValuePair>() {
            @Override
            public int compare(NameValuePair lhs, NameValuePair rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        String content = params.get(0).getName() + "="
                + params.get(0).getValue();
        for (int i =1; i < params.size(); i++) {
            content += "&" + params.get(i).getName() + "="
                    + params.get(i).getValue();
        }

        content += "@J4*A9N7&B^A9Y7j6sWv8m6%q_p+z-h=";

        token = StringUtils.stringToMD5(content);
        return token;
    }


    public Map<String,String> getMap(){
        List<BasicNameValuePair> params = createParams();
        params.add(new BasicNameValuePair("api_name",apiName));
        params.add(new BasicNameValuePair("token", getToken(params)));

        HashMap<String,String> mMap=new HashMap<>();
        for(int i=0;i<params.size();i++) {
            mMap.put(params.get(i).getName(),params.get(i).getValue());
        }
        return mMap;
    }

    protected List<BasicNameValuePair> getDefaultParams(){
        List<BasicNameValuePair> params = new LinkedList<>();

        params.add(new BasicNameValuePair("appid",appid));

//        if (PHPSESSID == null) {
//            params.add(new BasicNameValuePair("PHPSESSID", ""));
//        } else{
            params.add(new BasicNameValuePair("PHPSESSID", PHPSESSID));
//        }
        return params;
    }




    //创建参数
    public abstract List<BasicNameValuePair> createParams();


}
