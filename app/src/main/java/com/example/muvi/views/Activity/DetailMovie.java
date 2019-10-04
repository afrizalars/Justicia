package com.example.muvi.views.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muvi.R;
import com.example.muvi.database.RealmInit;
import com.example.muvi.models.FavoriteModel;
import com.example.muvi.models.MovieModel;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailMovie extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        TextView detailTitle = findViewById(R.id.detail_title);
        TextView detailDuration = findViewById(R.id.detail_duration);
        TextView detailCast = findViewById(R.id.detail_cast);
        TextView detailDescription = findViewById(R.id.detail_sinopsis);
        ImageView detailPoster = findViewById(R.id.detail_poster);
        final Button btnFav = findViewById(R.id.btn_add_fav);

        final MovieModel movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        detailTitle.setText(movie.getTitle());
        detailDuration.setText(movie.getRelease());
        detailCast.setText(movie.getRating());
        detailDescription.setText(movie.getOverview());
        Picasso.get().load(movie.getPoster()).into(detailPoster);
        String title = movie.getTitle();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm realm = Realm.getInstance(configuration);

        FavoriteModel model = realm.where(FavoriteModel.class).equalTo("title", title).findFirst();
        if (model != null){
            btnFav.setAlpha(.5F);
            btnFav.setClickable(false);
        }else{
            btnFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FavoriteModel favoriteModel = new FavoriteModel();
                    favoriteModel.setTitle(movie.getTitle());
                    favoriteModel.setRelease(movie.getRelease());
                    favoriteModel.setSinopsis(movie.getOverview());
                    favoriteModel.setRating(movie.getRating());
                    favoriteModel.setPoster(movie.getPoster());

                    String title = movie.getTitle();
                    String added = getResources().getString(R.string.added_to_favorite);
                    RealmInit realmInit = new RealmInit();
                    realmInit.Initialization(DetailMovie.this);
                    realmInit.getRealmHelper().save(favoriteModel);
                    Toast.makeText(DetailMovie.this,title+added, Toast.LENGTH_SHORT).show();
                    btnFav.setAlpha(.5F);
                    btnFav.setClickable(false);
                }
            });
        }

    }
}
