package com.example.muvi.views.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.muvi.R;
import com.example.muvi.adapter.CardViewMovieAdapter;
import com.example.muvi.adapter.CardViewTvShowAdapter;
import com.example.muvi.adapter.justicia.LawyerAdapter;
import com.example.muvi.adapter.justicia.MasalahAdapter;
import com.example.muvi.models.JusticiaModel.Masalah.ListMasalahModel;
import com.example.muvi.models.LawyerPopuler.LawyerPopulerModel;
import com.example.muvi.models.MovieModel;
import com.example.muvi.models.TvShowModel;
import com.example.muvi.network.Client;
import com.example.muvi.network.Interface;
import com.example.muvi.viewmodels.MovieViewModel;
import com.example.muvi.viewmodels.TvShowViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieFragment extends Fragment {


    private final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
    private final LinearLayoutManager linearLayoutManagerTv = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

    private TextView tvmovie, tvtv, more;

    private RecyclerView rvMovie;
    private RecyclerView rvTv;

    ProgressBar loading;

    private ArrayList<MovieModel> list = new ArrayList<>();
    private ArrayList<TvShowModel> listTv = new ArrayList<>();
    private MovieViewModel viewModel;
    private TvShowViewModel viewModelTv;

    private CardViewMovieAdapter adapter;
    private CardViewTvShowAdapter adapterTv;

    private Interface anInterface;

    public void getLawyerPopuler() {
        retrofit2.Call<LawyerPopulerModel> listModelCall = anInterface.getLawyerPopuler();
        listModelCall.enqueue(new Callback<LawyerPopulerModel>() {
            @Override
            public void onResponse(Call<LawyerPopulerModel> call, Response<LawyerPopulerModel> response) {
                rvTv.setAdapter(new LawyerAdapter(response.body()));
                loading.setVisibility(View.GONE);
                tvtv.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<LawyerPopulerModel> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }

    public void getListMasalah() {
        retrofit2.Call<ListMasalahModel> listModelCall = anInterface.getListMasalah();
        listModelCall.enqueue(new Callback<ListMasalahModel>() {
            @Override
            public void onResponse(Call<ListMasalahModel> call, Response<ListMasalahModel> response) {
                rvMovie.setAdapter(new MasalahAdapter(response.body()));
                loading.setVisibility(View.GONE);
                tvmovie.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ListMasalahModel> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tvmovie =view.findViewById(R.id.textView);
        tvtv = view.findViewById(R.id.textViewtv);
        more = view.findViewById(R.id.btn_more);
        loading = view.findViewById(R.id.loading);

        anInterface = Client.getClient().create(Interface.class);

        loading.setVisibility(View.VISIBLE);

        getListMasalah();
        getLawyerPopuler();

        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);

        rvTv = view.findViewById(R.id.rv_tv);
        rvTv.setHasFixedSize(true);

        showRecyclerCardView();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    private void showRecyclerCardView(){
        rvMovie.setLayoutManager(linearLayoutManager);
        rvMovie.setAdapter(adapter);

        rvTv.setLayoutManager(linearLayoutManagerTv);
        rvTv.setAdapter(adapterTv);
    }

}
