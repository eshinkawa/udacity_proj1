package project1.udacity.eduardoshinkawa.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class MovieTrailersFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_trailers, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here


        movieTrailers = new ArrayList<>();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Model> call = apiService.getMovieTrailers(id, key);
        call.enqueue(new retrofit2.Callback<MovieTrailers>() {
            @Override
            public void onResponse(Call<MovieTrailers> call, Response<MovieTrailers> response) {
                Log.d("MainActivity", "posts loaded from API");

                if(response.isSuccessful()) {
                    movieTrailers = response.body().getResults();
                    recyclerAdapter = new RecyclerAdapter(getApplicationContext(), movieTrailers);
                    recyclerView.setAdapter(recyclerAdapter);


                    Bundle bundleFrag = new Bundle();
                    String myMessage = "Stackoverflow is cool!";
                    bundleFrag.putInt("id", id );
                    MovieTrailersFragment trailersFragment = new MovieTrailersFragment();
                    trailersFragment.setArguments(bundleFrag);



                } else {
                    System.out.println(response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<MovieTrailers> call, Throwable t) {
                Log.d("TAG","Response = "+t);

            }
        });



        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

}
