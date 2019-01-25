package project1.udacity.eduardoshinkawa.myapplication;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String POPULARITY = "popularity";
    public static final String BEST_RATED = "bestrated";

    List<Movie> movieList;
    RecyclerView recyclerView;
    MoviesAdapter moviesAdapter;
    Boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        movieList = new ArrayList<>();

        getMovies(POPULARITY);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortPop:
                getMovies(POPULARITY);
                return true;
            case R.id.sortRated:
                getMovies(BEST_RATED);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void getMovies(String type) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesModel> call = null;
        if (type == BEST_RATED){
            call = apiService.getMoviesRating();
            getSupportActionBar().setTitle("Best Rated Movies");
        } else if (type == POPULARITY) {
            call = apiService.getMoviesPopularity();
            getSupportActionBar().setTitle("Most Popular Movies");
        }

        isConnected = Utils.checkConnectivity(this);

        if(isConnected){
            call.enqueue(new Callback<MoviesModel>() {
                @Override
                public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                    List<Movie> resultList = response.body().getResults();
                    if(response.isSuccessful()) {
                        if(movieList.isEmpty()){
                            createList(resultList);
                        } else {
                            updateList(resultList);
                        }

                    } else {
                        Log.d("TAG",response.errorBody().toString());
                    }

                }

                @Override
                public void onFailure(Call<MoviesModel> call, Throwable t) {
                    Log.d("TAG","Response = "+t);

                }
            });

        }
        else {
            Utils.showToast("No internet connectivity", this);
        }
    }

    public void updateList(List<Movie> list){
        movieList.clear();
        movieList.addAll(list);
        moviesAdapter.notifyDataSetChanged();
    }


    public void createList(List<Movie> list){
        movieList = list;
        moviesAdapter = new MoviesAdapter(getApplicationContext(), movieList);
        recyclerView.setAdapter(moviesAdapter);
    }

}