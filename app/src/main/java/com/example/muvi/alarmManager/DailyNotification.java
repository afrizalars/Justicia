package com.example.muvi.alarmManager;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import com.example.muvi.R;
import com.example.muvi.views.Activity.MainActivity;

import java.util.Calendar;

public class DailyNotification extends BroadcastReceiver {

    public final static int NOTIFICATION_ID = 1669;
    public final static String NOTIFICATION_CHANNEL_ID = "11669";
    public final static String NOTIFICATION_CHANNEL_NAME = "NOTIFICATION_CHANNEL_NAME";


    public DailyNotification() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        sendNotification(context, context.getString(R.string.app_name));
    }

    private void sendNotification(Context context, String title) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = TaskStackBuilder.create(context)
                .addNextIntent(intent)
                .getPendingIntent(NOTIFICATION_ID,PendingIntent.FLAG_UPDATE_CURRENT);

        Uri uriTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_movie_black_24dp)
                .setContentTitle(title)
                .setContentText(context.getString(R.string.deskripsi))
                .setContentIntent(pendingIntent)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(uriTone);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.YELLOW);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{1000,1000,1000,1000,1000});
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            if(notificationManager!=null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        if (notificationManager != null) {
            notificationManager.notify(13, builder.build());
        }

    }

    public void setAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context,DailyNotification.class);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 6);
        calendar.set(Calendar.SECOND,0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,NOTIFICATION_ID,intent,0);

        if (alarmManager != null){
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }

    }

    public void cancelAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,DailyNotification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,NOTIFICATION_ID,intent,0);
        alarmManager.cancel(pendingIntent);
    }



}
