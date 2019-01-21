package project1.udacity.eduardoshinkawa.myapplication;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String TAG = "MovieDetailsActivity";
    List<MovieTrailers.MovieVideos> movieTrailers;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    String key = BuildConfig.API_KEY;

    Bundle data = getIntent().getExtras();
    Movie movie = data.getParcelable("movie");

    int id =  movie.getId();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MovieTrailersFragment frag = new MovieTrailersFragment();

        frag.setArguments();

        setContentView(R.layout.activity_movie_details);
        bindData(movie);
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

    public void bindData(Movie movie) {
        setToolBarTitle(movie.getOriginalTitle());

        TextView overview = findViewById(R.id.overview);
        overview.setText(movie.getOverview());

        TextView releaseDate = findViewById(R.id.releaseDate);
        releaseDate.setText(movie.getReleaseDate());

        TextView voteAverage = findViewById(R.id.rating);
        String formattedRating = (movie.getVoteAverage()/2)+" / 5".toString();
        voteAverage.setText(formattedRating);

        ImageView bgImage = findViewById(R.id.bgImage);
        Picasso.get().load("http://image.tmdb.org/t/p/w342/"+movie.getPosterPath()).into(bgImage);
    }

    public void getRatings() {

    }
}
