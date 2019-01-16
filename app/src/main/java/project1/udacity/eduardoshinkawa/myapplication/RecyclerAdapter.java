package project1.udacity.eduardoshinkawa.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

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
    public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, final int position) {
        holder.title.setText(movieList.get(position).getTitle());
        holder.overView.setText(movieList.get(position).getOverview());
        Picasso.get().load("http://image.tmdb.org/t/p/w185/" + movieList.get(position).getPosterPath()).into(holder.poster_path);

        holder.vote_average.setRating(movieList.get(position).getVoteAverage().floatValue() / 2);

        holder.recyclerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               Toast.makeText(context, "posicao "+movieList.get(position).getPosterPath() ,Toast.LENGTH_SHORT).show();
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
        RatingBar vote_average;
        TextView overView;
        LinearLayout recyclerItem;


        public MyviewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            poster_path = itemView.findViewById(R.id.imageView);
            overView = itemView.findViewById(R.id.overView);
            recyclerItem = itemView.findViewById(R.id.recyclerItem);
            vote_average = itemView.findViewById(R.id.rating);
        }
    }
}

