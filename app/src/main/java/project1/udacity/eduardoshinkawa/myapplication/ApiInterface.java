package project1.udacity.eduardoshinkawa.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("3/movie/popular?api_key="+BuildConfig.API_KEY)
    Call<MoviesModel> getMoviesPopularity();

    @GET("3/movie/top_rated?api_key="+BuildConfig.API_KEY)
    Call<MoviesModel> getMoviesRating();

    @GET("3/movie/{id}/reviews?api_key="+BuildConfig.API_KEY)
    Call<ReviewsModel> getMovieReviews(@Path("id") String id);

    @GET("3/movie/{id}/videos?api_key="+BuildConfig.API_KEY)
    Call<ReviewsModel> getMoviesTrailers(@Path("id") String id);
}