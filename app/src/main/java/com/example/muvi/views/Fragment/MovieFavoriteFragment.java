package com.example.muvi.views.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muvi.R;
import com.example.muvi.adapter.FavoriteAdapter;
import com.example.muvi.database.RealmHelper;
import com.example.muvi.database.RealmInit;
import com.example.muvi.models.FavoriteModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment {
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView rv;
    List<FavoriteModel> list = new ArrayList<>();
    private FavoriteAdapter adapter;
    RealmHelper realmHelper;

    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(getContext());

        rv = view.findViewById(R.id.rv_movie_fav);
        rv.setLayoutManager(linearLayoutManager);

        // Setup Realm
        RealmInit realmInit = new RealmInit();
        realmInit.Initialization(getContext());
        list = realmInit.getRealmHelper().getAllFavorite();

        //set recycler view
        adapter = new FavoriteAdapter(getContext(), list);
        adapter.notifyDataSetChanged();

        rv.setHasFixedSize(true);
        showRecyclerCardView();
    }

    private void showRecyclerCardView() {
        rv.setAdapter(adapter);
    }


}
