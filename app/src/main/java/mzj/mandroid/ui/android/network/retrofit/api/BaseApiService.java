package mzj.mandroid.ui.android.network.retrofit.api;


import java.util.Map;

import mzj.mandroid.ui.android.network.retrofit.model.Weather;
import okhttp3.ResponseBody;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by 振坚 on 16/8/5.
 */
public interface BaseApiService {
    //        @Header用来添加Header
    //        @Query用来添加查询关键字
    @POST("?m=api&a=api")
    Observable<ResponseBody> getWeather(@QueryMap Map<String, String> maps);


    @POST("?m=api&a=api")
    Observable<Weather> getWeather2(@QueryMap Map<String, String> maps);



}
