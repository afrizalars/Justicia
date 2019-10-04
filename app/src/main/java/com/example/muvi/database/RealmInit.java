package com.example.muvi.database;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmInit {
    private RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();

    public void Initialization(Context context){
        // Set up
        Realm.init(context);
        Realm realm = Realm.getInstance(configuration);
    }


    private RealmHelper realmHelper = new RealmHelper(Realm.getInstance(configuration));

    public RealmHelper getRealmHelper(){
        return realmHelper;
    }
}
