package com.example.muvi.adapter.justicia;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muvi.R;
import com.example.muvi.models.LawyerPopuler.LawyerPopulerModel;
import com.example.muvi.models.ReviewModel.ReviewModel;
import com.squareup.picasso.Picasso;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder>{
    ReviewModel listModel;

    public ReviewAdapter(ReviewModel ModelList) {
        listModel = ModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.review.setText(listModel.getData().get(position).getReview());
        holder.rating.setText(listModel.getData().get(position).getRating().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount () {
        return listModel.getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView review,rating;
        public ImageView photo;
        public MyViewHolder(View itemView) {
            super(itemView);

            review = itemView.findViewById(R.id.tv_review);
            rating = itemView.findViewById(R.id.tv_rating);

        }
    }
}