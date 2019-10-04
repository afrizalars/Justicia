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
import com.example.muvi.models.FavoriteModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private List<FavoriteModel> favoriteModels = new ArrayList<>();
    private Context context;

    public FavoriteAdapter(Context context, List<FavoriteModel> favoriteModels){
        this.context = context;
        this.favoriteModels = favoriteModels;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_each, viewGroup, false);
        return new FavoriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteViewHolder favoriteViewHolder, int i) {

        final Context c = favoriteViewHolder.itemView.getContext();
        final FavoriteModel fav = favoriteModels.get(i);
        final Integer id = fav.getId();
        final String title = fav.getTitle();
        final RealmInit realmInit = new RealmInit();
        realmInit.Initialization(c);

        Picasso.get().load(fav.getPoster()).into(favoriteViewHolder.img_poster);
        favoriteViewHolder.str_description.setText(fav.getSinopsis());
        favoriteViewHolder.str_title.setText(fav.getTitle());
        favoriteViewHolder.str_episode.setText(fav.getRelease());

        favoriteViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
                                realmInit.getRealmHelper().delete(id);
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
        return favoriteModels.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster;
        TextView str_episode, str_description, str_title;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster = itemView.findViewById(R.id.movie_poster);
            str_description = itemView.findViewById(R.id.movie_description);
            str_episode = itemView.findViewById(R.id.movie_duration);
            str_title = itemView.findViewById(R.id.movie_title);
        }
    }
}
