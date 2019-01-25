package project1.udacity.eduardoshinkawa.myapplication;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String TAG = "MovieDetailsActivity";
    public static final String REVIEWS = "reviews";
    public static final String TRAILERS = "trailers";

    Boolean isConnected;
    String id;
    List<Review> reviewList;
    RecyclerView recyclerView;
    ReviewsAdapter reviewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        bindData();
        getMovieData(REVIEWS);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView = findViewById(R.id.reviewAdapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setToolBarTitle(String movieTitle) {
        getSupportActionBar().setTitle(movieTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void bindData() {
        Bundle data = getIntent().getExtras();
        Movie movie = data.getParcelable("movie");

        setToolBarTitle(movie.getOriginalTitle());

        TextView overview = findViewById(R.id.overview);
        overview.setText(movie.getOverview());

        TextView releaseDate = findViewById(R.id.releaseDate);
        releaseDate.setText(movie.getReleaseDate());

        TextView voteAverage = findViewById(R.id.rating);
        String formattedRating = (movie.getVoteAverage()/2)+" / 5".toString();
        voteAverage.setText(formattedRating);

        id = movie.getId().toString();

        ImageView bgImage = findViewById(R.id.bgImage);
        Picasso.get().load("http://image.tmdb.org/t/p/w342/"+movie.getPosterPath()).into(bgImage);
    }


    private void getMovieData(String type) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ReviewsModel> call = null;
        if (type == REVIEWS){
            call = apiService.getMovieReviews(id);
        } else if (type == TRAILERS) {
            call = apiService.getMoviesTrailers(id);
        }

        isConnected = Utils.checkConnectivity(this);

        if(isConnected){
            call.enqueue(new retrofit2.Callback<ReviewsModel>() {
                @Override
                public void onResponse(Call<ReviewsModel> call, Response<ReviewsModel> response) {
                    List<Review> resultList = response.body().getResults();
                    if(response.isSuccessful()) {
                        reviewsAdapter = new ReviewsAdapter(MovieDetailsActivity.this, resultList);
                        recyclerView.setAdapter(reviewsAdapter);
                    } else {
                        Log.d("TAG",response.errorBody().toString());
                    }

                }

                @Override
                public void onFailure(Call<ReviewsModel> call, Throwable t) {
                    Log.d("TAG","Response = "+t);

                }
            });

        }
        else {
            Utils.showToast("No internet connectivity", this);
        }
    }
}
