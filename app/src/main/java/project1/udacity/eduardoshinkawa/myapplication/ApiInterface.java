package project1.udacity.eduardoshinkawa.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("3/movie/popular")
    Call<Model> getMovies(@Query("api_key") String key);
}
