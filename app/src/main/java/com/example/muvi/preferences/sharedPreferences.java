package com.example.muvi.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class sharedPreferences {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    sharedPreferences(Context context){
        preferences = context.getSharedPreferences("",Context.MODE_PRIVATE);
    }
}
