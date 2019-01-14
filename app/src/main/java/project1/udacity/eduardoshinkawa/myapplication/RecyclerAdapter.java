package project1.udacity.eduardoshinkawa.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    Context context;
    List<Result> movieList;

    public RecyclerAdapter(Context context, List<Result> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public void setMovieList(List<Result> movieList) {
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
        holder.tvMovieName.setText(movieList.get(position).getTitle());

//        Glide.with(context).load(movieList.get(position).getImageUrl()).apply(RequestOptions.centerCropTransform()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        if(movieList != null){
            return movieList.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tvMovieName;
        ImageView image;

        public MyviewHolder(View itemView) {
            super(itemView);
            tvMovieName = (TextView)itemView.findViewById(R.id.title);
//            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }
}

