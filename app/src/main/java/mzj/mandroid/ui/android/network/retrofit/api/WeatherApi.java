package mzj.mandroid.ui.android.network.retrofit.api;


import mzj.mandroid.ui.android.network.retrofit.model.Weather;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 振坚 on 16/8/5.
 */
public interface WeatherApi {
    @POST("?m=api&a=api&PHPSESSID=4dlres5tnvdrd37q9gfibs2966&api_name=artist.article.getArticleList&appid=1&type=1")
    Call<Weather> getWeather(@Query("is_rec") String is_rec,
                             @Query("token") String token);

}
