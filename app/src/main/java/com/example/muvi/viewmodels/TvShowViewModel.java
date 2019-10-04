package com.example.muvi.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.muvi.BuildConfig;
import com.example.muvi.models.TvShowModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvShowViewModel extends ViewModel {
    public static final String MY_API_KEY = BuildConfig.API_KEY;;

    private MutableLiveData<ArrayList<TvShowModel>> tvlist =new MutableLiveData<>();

    public void setTV(){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final ArrayList<TvShowModel> item = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + MY_API_KEY + "&language=en-US";

        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject respnseObject = new JSONObject(result);
                    JSONArray list = respnseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tv = list.getJSONObject(i);
                        TvShowModel tvItems = new TvShowModel(tv);
                        item.add(tvItems);
                    }
                    tvlist.postValue(item);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });

    }

    public LiveData<ArrayList<TvShowModel>> getTV(){
        return tvlist;
    }
}
