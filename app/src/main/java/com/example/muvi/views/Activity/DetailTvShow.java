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
import com.example.muvi.models.FavoriteTvModel;
import com.example.muvi.models.TvShowModel;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailTvShow extends AppCompatActivity {
    public static final String EXTRA_SHOW = "extra_show";

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

        final TvShowModel show = getIntent().getParcelableExtra(EXTRA_SHOW);

        detailTitle.setText(show.getTitle());
        detailDuration.setText(show.getRelease());
        detailCast.setText(show.getRating());
        detailDescription.setText(show.getOverview());
        Picasso.get().load(show.getPoster()).into(detailPoster);
        String title = show.getTitle();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm realm = Realm.getInstance(configuration);

        FavoriteTvModel model = realm.where(FavoriteTvModel.class).equalTo("title", title).findFirst();
        if (model != null){
            btnFav.setAlpha(.5F);
            btnFav.setClickable(false);
        }else{
            btnFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FavoriteTvModel favoriteTvModel = new FavoriteTvModel();
                    favoriteTvModel.setTitle(show.getTitle());
                    favoriteTvModel.setRelease(show.getRelease());
                    favoriteTvModel.setSinopsis(show.getOverview());
                    favoriteTvModel.setRating(show.getRating());
                    favoriteTvModel.setPoster(show.getPoster());

                    String title = show.getTitle();
                    String added = getResources().getString(R.string.added_to_favorite);
                    RealmInit realmInit = new RealmInit();
                    realmInit.Initialization(DetailTvShow.this);
                    realmInit.getRealmHelper().saveTv(favoriteTvModel);
                    Toast.makeText(DetailTvShow.this,title+added, Toast.LENGTH_SHORT).show();
                    btnFav.setAlpha(.5F);
                    btnFav.setClickable(false);
                }
            });
        }
    }
}
