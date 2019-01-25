package project1.udacity.eduardoshinkawa.myapplication;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
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

    public static final String POPULARITY = "popularity";
    public static final String BEST_RATED = "bestrated";

    List<Movie> movieList;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
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
        Call<Model> call = null;
        if (type == BEST_RATED){
            call = apiService.getMoviesRating();
            getSupportActionBar().setTitle("Best Rated Movies");
        } else if (type == POPULARITY) {
            call = apiService.getMoviesPopularity();
            getSupportActionBar().setTitle("Most Popular Movies");
        }

        isConnected = Utils.checkConnectivity(this);

        if(isConnected){
            call.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {
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
                public void onFailure(Call<Model> call, Throwable t) {
                    Log.d("TAG","Response = "+t);

                }
            });

        }
        else {
            showToast("No internet connectivity");
        }
    }


    public void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    public void updateList(List<Movie> list){
        movieList.clear();
        movieList.addAll(list);
        recyclerAdapter.notifyDataSetChanged();
    }


    public void createList(List<Movie> list){
        movieList = list;
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), movieList);
        recyclerView.setAdapter(recyclerAdapter);
    }

}