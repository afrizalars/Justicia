package com.example.muvi.views.Fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.muvi.R;
import com.example.muvi.adapter.CardViewTvShowAdapter;
import com.example.muvi.models.TvShowModel;
import com.example.muvi.viewmodels.TvShowViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    RecyclerView rvShow;
    ProgressBar loading;
    CardViewTvShowAdapter adapter;
    TvShowViewModel viewModel;
    private ArrayList<TvShowModel> list = new ArrayList<>();

    private Observer<ArrayList<TvShowModel>> getTV = new Observer<ArrayList<TvShowModel>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShowModel> tv) {
            if (tv != null) {
                adapter.setData(tv);
                loading.setVisibility(View.GONE);
            }
        }
    };

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loading = view.findViewById(R.id.loading);

        rvShow = view.findViewById(R.id.rv_show);
        rvShow.setHasFixedSize(true);

        viewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        viewModel.getTV().observe(this, getTV);
        viewModel.setTV();
        loading.setVisibility(View.VISIBLE);

        adapter = new CardViewTvShowAdapter(getContext());

        adapter.notifyDataSetChanged();
        showRecyclerCardView();
    }


    private void showRecyclerCardView() {
        rvShow.setLayoutManager(linearLayoutManager);
        rvShow.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

}
