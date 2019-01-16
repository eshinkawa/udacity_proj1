package project1.udacity.eduardoshinkawa.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    Context context;
    List<Movie> movieList;

    public RecyclerAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter ,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, int position) {
        holder.title.setText(movieList.get(position).getTitle());
        holder.overView.setText(movieList.get(position).getOverview());
        Picasso.get().load("http://image.tmdb.org/t/p/w185/" + movieList.get(position).getPosterPath()).into(holder.poster_path);
    }

    @Override
    public int getItemCount() {
        if(movieList != null){
            return movieList.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster_path;
        RatingBar voteAverage;
        TextView overView;


        public MyviewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            poster_path = itemView.findViewById(R.id.imageView);
            overView = itemView.findViewById(R.id.overView);
        }
    }
}

