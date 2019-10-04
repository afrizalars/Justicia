package com.example.muvi.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muvi.R;
import com.example.muvi.database.RealmInit;
import com.example.muvi.models.FavoriteTvModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoriteTvAdapter extends RecyclerView.Adapter<FavoriteTvAdapter.FavoriteTvViewHolder> {

    private List<FavoriteTvModel> favoriteTvModels = new ArrayList<>();
    private Context context;

    public FavoriteTvAdapter(Context context, List<FavoriteTvModel> favoriteModels){
        this.context = context;
        this.favoriteTvModels = favoriteModels;
    }

    @NonNull
    @Override
    public FavoriteTvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_each, viewGroup, false);
        return new FavoriteTvViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteTvViewHolder favoriteTvViewHolder, int i) {

        final Context c = favoriteTvViewHolder.itemView.getContext();
        final FavoriteTvModel fav = favoriteTvModels.get(i);
        final Integer id = fav.getId();
        final RealmInit realmInit = new RealmInit();

        realmInit.Initialization(c);

        Picasso.get().load(fav.getPoster()).into(favoriteTvViewHolder.img_poster);
        favoriteTvViewHolder.str_description.setText(fav.getSinopsis());
        favoriteTvViewHolder.str_title.setText(fav.getTitle());
        favoriteTvViewHolder.str_episode.setText(fav.getRelease());

        favoriteTvViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);

                // set title

                alertDialogBuilder.setTitle(R.string.dialog_title);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(true)

                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                realmInit.getRealmHelper().deleteTv(id);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(R.string.no,null);

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteTvModels.size();
    }

    public class FavoriteTvViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster;
        TextView str_episode, str_description, str_title;

        public FavoriteTvViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster = itemView.findViewById(R.id.movie_poster);
            str_description = itemView.findViewById(R.id.movie_description);
            str_episode = itemView.findViewById(R.id.movie_duration);
            str_title = itemView.findViewById(R.id.movie_title);
        }
    }
}