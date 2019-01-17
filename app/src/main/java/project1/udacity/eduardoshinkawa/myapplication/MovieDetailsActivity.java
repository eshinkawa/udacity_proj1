package project1.udacity.eduardoshinkawa.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String TAG = "MovieDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Bundle data = getIntent().getExtras();
        Movie movie = data.getParcelable("movie");

        TextView title = findViewById(R.id.title);
        title.setText(movie.getOriginalTitle());

        TextView overview = findViewById(R.id.overview);
        overview.setText(movie.getOverview());

        Log.d(TAG, "movieposterpatch: "+ movie.getPosterPath());
        Log.d(TAG, "moviereleasedate: "+ movie.getReleaseDate());
        Log.d(TAG, "movieoverview: "+ movie.getOverview());
        Log.d(TAG, "movievote: "+ movie.getVoteAverage());
        Log.d(TAG, "movieoriginaltitle: "+ movie.getOriginalTitle());

        ImageView bgImage = findViewById(R.id.bgImage);
        Picasso.get().load("http://image.tmdb.org/t/p/w342/"+movie.getPosterPath()).into(bgImage);

    }
}
