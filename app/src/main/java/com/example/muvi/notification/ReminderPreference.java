package com.example.muvi.notification;

import android.content.Context;
import android.content.SharedPreferences;

public class ReminderPreference {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String KEY_REMINDER_ISON ="key_reminder_ison" ;
    public static final String KEY_RELEASE_ISON ="key_release_ison" ;
    public static final String PREFS_NAME = "user_pref";
    public static final String KEY_REMINDER_Daily = "key_reminder_daily";
    public static final String KEY_REMINDER_MESSAGE_Release = "key_reminder_message_release";
    public static final String KEY_REMINDER_MESSAGE_Daily = "key_reminder_message_daily";


    public ReminderPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void dailyReminderisOn(Boolean b){
        editor.putBoolean(KEY_REMINDER_ISON, b);
        editor.apply();
        editor.commit();
    }

    public void setReminderReleaseTime(String time){
        editor.putString(KEY_REMINDER_Daily,time);
        editor.apply();
        editor.commit();
    }
    public void setReminderReleaseMessage (String message){
        editor.putString(KEY_REMINDER_MESSAGE_Release,message);
    }
    public void setReminderDailyTime(String time){
        editor.putString(KEY_REMINDER_Daily,time);
        editor.apply();
        editor.commit();
    }
    public void setReminderDailyMessage(String message){
        editor.putString(KEY_REMINDER_MESSAGE_Daily,message);
    }
}
