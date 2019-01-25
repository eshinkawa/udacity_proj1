package project1.udacity.eduardoshinkawa.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyviewHolder> {

    Context context;
    List<Movie> movieList;

    public MoviesAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public MoviesAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter ,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.MyviewHolder holder, final int position) {
        holder.title.setText(movieList.get(position).getTitle());
        Picasso.get().load("http://image.tmdb.org/t/p/w185/" + movieList.get(position).getPosterPath()).into(holder.poster_path);

        holder.rating.setRating(movieList.get(position).getVoteAverage().floatValue() / 2);

        holder.recyclerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);

                Movie mMovie = new Movie();

                mMovie.setPosterPath(movieList.get(position).getPosterPath());
                mMovie.setOverview(movieList.get(position).getOverview());
                mMovie.setOriginalTitle(movieList.get(position).getOriginalTitle());
                mMovie.setReleaseDate(movieList.get(position).getReleaseDate());
                mMovie.setVoteAverage(movieList.get(position).getVoteAverage());
                mMovie.setId(movieList.get(position).getId());

                Bundle bundle = new Bundle();
                bundle.putParcelable("movie", mMovie);

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

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
        RatingBar rating;
        TextView overView;
        LinearLayout recyclerItem;


        public MyviewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            poster_path = itemView.findViewById(R.id.imageView);
            recyclerItem = itemView.findViewById(R.id.recyclerItem);
            rating = itemView.findViewById(R.id.ratingBar);
        }
    }
}

