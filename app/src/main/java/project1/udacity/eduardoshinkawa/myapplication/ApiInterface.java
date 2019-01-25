package project1.udacity.eduardoshinkawa.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("3/movie/popular?api_key="+BuildConfig.API_KEY)
    Call<Model> getMoviesPopularity();

    @GET("3/movie/top_rated?api_key="+BuildConfig.API_KEY)
    Call<Model> getMoviesRating();
}