package com.example.muvi.views.Fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muvi.R;
import com.example.muvi.adapter.FavoriteTabAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class Favorite extends Fragment {
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
//    private LinearLayoutManager linearLayoutManager;
//    private RecyclerView rv;
//    List<FavoriteModel> list = new ArrayList<>();
//    private FavoriteAdapter adapter;
//    RealmHelper realmHelper;

    private void init(@NonNull View view){
        //Toolbar
        toolbar = view.findViewById(R.id.toolbar);

        //viewPager
        viewPager =  view.findViewById(R.id.viewPager);

        //TabLayout
        tabLayout =  view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }
    public Favorite() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        init(view);

        toolbar.setTitle("Feed");

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setupViewPager(viewPager);


//        linearLayoutManager = new LinearLayoutManager(getContext());
//        rv.setLayoutManager(linearLayoutManager);

//        // Setup Realm
//        RealmInit realmInit = new RealmInit();
//        realmInit.Initialization(getContext());
//        list = realmInit.getRealmHelper().getAllFavorite();
//
//        //set recycler view
//        adapter = new FavoriteAdapter(getContext(), list);
//        adapter.notifyDataSetChanged();
//
//        rv.setHasFixedSize(true);
//        showRecyclerCardView();
    }

    private void setupViewPager(ViewPager viewPager) {
            FavoriteTabAdapter favoriteTabAdapter = new FavoriteTabAdapter(getChildFragmentManager());

            //Tab Setting
            favoriteTabAdapter.addFragment(new MovieFavoriteFragment(), "Literasi");
            favoriteTabAdapter.addFragment(new TvFavoriteFragment(), "News");
            viewPager.setAdapter(favoriteTabAdapter);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

}
