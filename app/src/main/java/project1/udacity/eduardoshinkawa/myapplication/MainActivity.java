package project1.udacity.eduardoshinkawa.myapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

//    List<Result> movieList;
//    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
//    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        movieList = new ArrayList<>();
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerAdapter = new RecyclerAdapter(getApplicationContext(),movieList);
//        recyclerView.setAdapter(recyclerAdapter);



        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Model> call = apiService.getMovies("7fc62a4d4f38231fa1b3a4cdf0e2a4c6");
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
//                Log.d("MainActivity", "posts loaded from API");
//                recyclerAdapter.setMovieList(response.body().getResults());

                if(response.isSuccessful()) {
//                recyclerAdapter.setMovieList(response.body().getResults());
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
    }
}
