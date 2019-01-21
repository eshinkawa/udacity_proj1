package project1.udacity.eduardoshinkawa.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("3/movie/popular")
    Call<Model> getMoviesPopularity(@Query("api_key") String key);

    @GET("3/movie/top_rated")
    Call<Model> getMoviesRating(@Query("api_key") String key);

    @GET("3/movie/{id}/reviews")
    Call<Model> getMovieReviews(@Path("id") int id, @Query("api_key") String key);


    @GET("3/movie/{id}/videos")
    Call<Model> getMovieTrailers(@Path("id") int id, @Query("api_key") String key);
}