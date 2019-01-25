package project1.udacity.eduardoshinkawa.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyviewHolder> {

    Context context;
    List<Review> reviewList;

    public ReviewsAdapter(Context context, List<Review> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewsAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.reviews_adapter, parent,false);
        return new ReviewsAdapter.MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.MyviewHolder myviewHolder, int position) {
        myviewHolder.reviewContent.setText(reviewList.get(position).getContent());
        myviewHolder.reviewUser.setText(reviewList.get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        if(reviewList != null){
            return reviewList.size();
        }
        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView reviewContent;
        TextView reviewUser;
        LinearLayout reviewItem;


        public MyviewHolder(View itemView) {
            super(itemView);
            reviewContent = itemView.findViewById(R.id.reviewContent);
            reviewItem = itemView.findViewById(R.id.reviewItem);
            reviewUser = itemView.findViewById(R.id.reviewUser);
        }
    }
}
