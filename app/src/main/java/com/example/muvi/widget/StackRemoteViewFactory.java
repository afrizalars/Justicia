package com.example.muvi.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.muvi.R;
import com.example.muvi.database.RealmHelper;
import com.example.muvi.database.RealmInit;
import com.example.muvi.models.FavoriteModel;
import com.example.muvi.models.FavoriteTvModel;
import com.example.muvi.models.MovieModel;
import com.example.muvi.views.Fragment.Favorite;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class StackRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private int widgetId;
    private Realm realm;
    private RealmHelper helper = new RealmHelper(realm);
    private List<FavoriteModel> list = new ArrayList<>();



    public StackRemoteViewFactory(Context applicationContext, Intent intent) {
        context = applicationContext;
        widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onDataSetChanged() {
        // Setup Realm
        realm = Realm.getDefaultInstance();
        RealmInit realmInit = new RealmInit();
        realmInit.Initialization(context);
        list = realmInit.getRealmHelper().getAllFavorite();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        realm = Realm.getDefaultInstance();
        final RemoteViews rv =new RemoteViews(context.getPackageName(), R.layout.widget_item);
        FavoriteModel model = list.get(position);

        Picasso.get().load(model.getPoster()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                rv.setImageViewBitmap(R.id.img_widget,bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

          @Override
           public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
       });


        Bundle extras =new Bundle();
        extras.putInt("WIDGET_EXTRA_ITEM", position);
        Intent i = new Intent();
        i.putExtras(extras);

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
