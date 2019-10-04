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
import com.example.muvi.database.RealmHelper;
import com.example.muvi.views.Activity.DetailMovie;
import com.example.muvi.models.MovieModel;
import com.example.muvi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.Realm;

public class CardViewMovieAdapter extends RecyclerView.Adapter<CardViewMovieAdapter.CardViewViewHolder>{

    private ArrayList<MovieModel> listMovie = new ArrayList<>();
    private Context context;
    private RealmHelper realmHelper;
    private Realm realm;

    public CardViewMovieAdapter(Context context) {
        this.context = context;
    }


    public void setData(ArrayList<MovieModel> items) {
        listMovie.clear();
        listMovie.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_each, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder cardViewViewHolder, int i) {
        final MovieModel movie = listMovie.get(i);
        Picasso.get().load(movie.getPoster()).into(cardViewViewHolder.img_poster);
        cardViewViewHolder.str_description.setText(movie.getOverview());
        cardViewViewHolder.str_release.setText(movie.getRelease());
        cardViewViewHolder.str_title.setText(movie.getTitle());

        cardViewViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = cardViewViewHolder.itemView.getContext();

                MovieModel movies = new MovieModel();

                movies.setTitle(listMovie.get(cardViewViewHolder.getAdapterPosition()).getTitle());
                movies.setRelease(listMovie.get(cardViewViewHolder.getAdapterPosition()).getRelease());
                movies.setOverview(listMovie.get(cardViewViewHolder.getAdapterPosition()).getOverview());
                movies.setDuration(listMovie.get(cardViewViewHolder.getAdapterPosition()).getDuration());
                movies.setRating(listMovie.get(cardViewViewHolder.getAdapterPosition()).getRating());
                movies.setPoster(listMovie.get(cardViewViewHolder.getAdapterPosition()).getPoster());

                Intent idataMovie = new Intent(context, DetailMovie.class);
                idataMovie.putExtra(DetailMovie.EXTRA_MOVIE, movies);
                context.startActivity(idataMovie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
            ImageView img_poster;
            TextView str_release, str_description, str_title;

        public CardViewViewHolder(@NonNull View itemView) {
                super(itemView);
                img_poster = itemView.findViewById(R.id.movie_poster);
                str_description = itemView.findViewById(R.id.movie_description);
                str_release = itemView.findViewById(R.id.movie_duration);
                str_title = itemView.findViewById(R.id.movie_title);
            }
        }

    }