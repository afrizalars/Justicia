package com.example.muvi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muvi.views.Activity.DetailTvShow;
import com.example.muvi.R;
import com.example.muvi.models.TvShowModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardViewTvShowAdapter extends RecyclerView.Adapter<CardViewTvShowAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<TvShowModel> list = new ArrayList<>();

    public CardViewTvShowAdapter(ArrayList<TvShowModel> list) {
        this.list = list;
    }

    public CardViewTvShowAdapter(Context context){this.context = context; }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_each, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder cardViewViewHolder, int i) {
        final TvShowModel show = list.get(i);
        Picasso.get().load(show.getPoster()).into(cardViewViewHolder.img_poster);
        cardViewViewHolder.str_description.setText(show.getOverview());
        cardViewViewHolder.str_title.setText(show.getTitle());
        cardViewViewHolder.str_episode.setText(show.getRelease());

        cardViewViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = cardViewViewHolder.itemView.getContext();

                TvShowModel tvshow = new TvShowModel();
                tvshow.setTitle(list.get(cardViewViewHolder.getAdapterPosition()).getTitle());
                tvshow.setOverview(list.get(cardViewViewHolder.getAdapterPosition()).getOverview());
                tvshow.setRelease(list.get(cardViewViewHolder.getAdapterPosition()).getRelease());
                tvshow.setPoster(list.get(cardViewViewHolder.getAdapterPosition()).getPoster());
                tvshow.setRating(list.get(cardViewViewHolder.getAdapterPosition()).getRating());

                Intent idataShow = new Intent(context, DetailTvShow.class);
                idataShow.putExtra(DetailTvShow.EXTRA_SHOW, tvshow);
                context.startActivity(idataShow);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(ArrayList<TvShowModel> tv) {
        list.clear();
        list.addAll(tv);
        notifyDataSetChanged();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
            ImageView img_poster;
            TextView str_episode, str_description, str_title;

        public CardViewViewHolder(@NonNull View itemView) {
                super(itemView);
                img_poster = itemView.findViewById(R.id.movie_poster);
                str_description = itemView.findViewById(R.id.movie_description);
                str_episode = itemView.findViewById(R.id.movie_duration);
                str_title = itemView.findViewById(R.id.movie_title);
        }
    }
}
