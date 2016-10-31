package mzj.mandroid.ui.android.network.retrofit.api;


import java.util.Map;

import mzj.mandroid.ui.android.network.retrofit.model.Headlines;
import mzj.mandroid.ui.android.network.retrofit.model.Weather;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
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
    Observable<ResponseBody> getResponse(@QueryMap Map<String, String> maps);


    @POST("?m=api&a=api")
    Observable<Weather> getWeather2(@QueryMap Map<String, String> maps);

    @POST("artgoer/api/v1/user/0/home/indexHome/v42?")
    Observable<Headlines> getHeadlines(@QueryMap Map<String, String> maps);


    @GET("artgoer/api/v1/user/0/home/indexHome/v42?")
    Observable<Headlines> getHeadliness(@Query("pageIndex") String pageIndex,
                                        @Query("token") String token);




}
