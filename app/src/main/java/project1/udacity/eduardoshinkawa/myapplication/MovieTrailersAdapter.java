package project1.udacity.eduardoshinkawa.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MovieTrailersAdapter extends RecyclerView.Adapter<MovieTrailersAdapter.MyviewHolder>{

    Context context;
    List<MovieTrailers.MovieVideos> movieVideos;

    public MovieTrailersAdapter(Context context, List<MovieTrailers.MovieVideos> movieVideos) {
        this.context = context;
        this.movieVideos = movieVideos;
    }

    public void setMovieTrailersList(List<MovieTrailers.MovieVideos> movieVideos) {
        this.movieVideos = movieVideos;
    }

    @NonNull
    @Override
    public MovieTrailersAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_movie_trailers,parent,false);
        return new MovieTrailersAdapter.MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieTrailersAdapter.MyviewHolder holder, final int position) {
        holder.title.setText(movieVideos.get(position).getKey());
    }

    @Override
    public int getItemCount() {
        if(movieVideos != null){
            return movieVideos.size();
        }
        return 0;
    }


    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyviewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.trailerName);
        }
    }
}
