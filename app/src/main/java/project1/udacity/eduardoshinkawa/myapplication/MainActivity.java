package project1.udacity.eduardoshinkawa.myapplication;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Movie> movieList;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    String key = "7fc62a4d4f38231fa1b3a4cdf0e2a4c6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        movieList = new ArrayList<>();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Model> call = apiService.getMoviesPopularity(key);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.d("MainActivity", "posts loaded from API");

                if(response.isSuccessful()) {
                    movieList = response.body().getResults();
                    recyclerAdapter = new RecyclerAdapter(getApplicationContext(), movieList);
                    recyclerView.setAdapter(recyclerAdapter);

                } else {
                    System.out.println(response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d("TAG","Response = "+t);

            }
        });

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
                getMovies("popularity", key);
                return true;
            case R.id.sortRated:
                getMovies("bestrated", key);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void getMovies(String type, String key) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Model> call = null;
        if (type == "bestrated"){
            call = apiService.getMoviesRating(key);
            getSupportActionBar().setTitle("Best Rated Movies");
        } else if (type == "popularity") {
            call = apiService.getMoviesPopularity(key);
            getSupportActionBar().setTitle("Most Popular Movies");
        }
        
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {

                if(response.isSuccessful()) {
                    movieList.clear();
                    movieList.addAll(response.body().getResults());
                    recyclerAdapter.notifyDataSetChanged();
                } else {
                    System.out.println(response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d("TAG","Response = "+t);

            }
        });

    }
}