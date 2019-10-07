package com.example.muvi.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.muvi.R;
import com.example.muvi.views.Activity.MainActivity;

import java.util.Objects;

/**
 * Implementation of App Widget functionality.
 */
public class FavoriteMovieWidget extends AppWidgetProvider {

    private static final String TOAST_ACTION = "com.example.muvi.TOAST_ACTION";
    public static final String EXTRA_ITEM = "com.example.muvi.EXTRA_ITEM";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Intent i =new Intent(context, RemoteViewService.class);

        i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        i.setData(Uri.parse(i.toUri(Intent.URI_INTENT_SCHEME)));

        RemoteViews rv = new RemoteViews(context.getPackageName(),R.layout.favorite_movie_widget);
        rv.setRemoteAdapter(R.id.stack_view, i);

        Intent toastIntent = new Intent(context, FavoriteMovieWidget.class);

        toastIntent.setAction(TOAST_ACTION);
        toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        i.setData(Uri.parse(i.toUri(Intent.URI_INTENT_SCHEME)));
        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        rv.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, rv);


    }

    public static void sendRefreshBroadcast(Context context) {
        Intent intent = new Intent("REFRESH_ACTION");
        intent.setComponent(new ComponentName(context, FavoriteMovieWidget.class));
        context.sendBroadcast(intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context c, Intent i){
        AppWidgetManager.getInstance(c);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.requireNonNull(i.getAction()).equals(TOAST_ACTION)) {
                i.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID);
                int viewIndex = i.getIntExtra(EXTRA_ITEM, 0);
                Toast.makeText(c, "Touched view " + viewIndex, Toast.LENGTH_SHORT).show();
            }
        }
        super.onReceive(c, i);
    }
}

