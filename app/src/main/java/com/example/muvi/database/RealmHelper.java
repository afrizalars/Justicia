package com.example.muvi.database;

import android.util.Log;

import com.example.muvi.models.FavoriteModel;
import com.example.muvi.models.FavoriteTvModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper (Realm realm){
        this.realm = realm;
    }

    //menyimpan data favorite
    public void save(final FavoriteModel favoriteModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(FavoriteModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    favoriteModel.setId(nextId);
                    FavoriteModel model = realm.copyToRealm(favoriteModel);
                }else{
                    Log.e("ERROR", "execute: Database not Exist");
                }
            }
        });
    }

    //Read semua data
    public List<FavoriteModel> getAllFavorite(){
        RealmResults<FavoriteModel> results = realm.where(FavoriteModel.class).findAll();
        return results;
    }

    //delete
    public void delete(Integer id){
        final RealmResults<FavoriteModel> model = realm.where(FavoriteModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

    //menyimpan data favorite Tv
    public void saveTv(final FavoriteTvModel favoriteTvModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(FavoriteTvModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    favoriteTvModel.setId(nextId);
                    FavoriteTvModel model = realm.copyToRealm(favoriteTvModel);
                }else{
                    Log.e("ERROR", "execute: Database not Exist");
                }
            }
        });
    }

    //Read semua data
    public List<FavoriteTvModel> getAllFavoriteTv(){
        RealmResults<FavoriteTvModel> results = realm.where(FavoriteTvModel.class).findAll();
        return results;
    }

    //delete
    public void deleteTv(Integer id){
        final RealmResults<FavoriteTvModel> model = realm.where(FavoriteTvModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

}
