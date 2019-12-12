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
import com.example.muvi.adapter.justicia.ProbonoAdapter;
import com.example.muvi.models.JusticiaModel.Masalah.ListMasalahModel;
import com.example.muvi.models.LawyerPopuler.LawyerPopulerModel;
import com.example.muvi.models.MovieModel;
import com.example.muvi.models.ProbonoModel.ProbonoModel;
import com.example.muvi.models.ProfileModel.ProfileModel;
import com.example.muvi.models.TvShowModel;
import com.example.muvi.network.Client;
import com.example.muvi.network.Interface;
import com.example.muvi.viewmodels.MovieViewModel;
import com.example.muvi.viewmodels.TvShowViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment {
    private final LinearLayoutManager probonoLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
    Interface anInterface;
    RecyclerView rv_probono;

    public void getProbono() {
        retrofit2.Call<ProbonoModel> listModelCall = anInterface.getProbono();
        listModelCall.enqueue(new Callback<ProbonoModel>() {
            @Override
            public void onResponse(Call<ProbonoModel> call, Response<ProbonoModel> response) {
                rv_probono.setAdapter(new ProbonoAdapter(response.body()));
//                loading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ProbonoModel> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_probono = view.findViewById(R.id.rv_probono);
        anInterface = Client.getClient().create(Interface.class);
        getProbono();

        rv_probono.setLayoutManager(probonoLayoutManager);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_chat, container, false);
    }


}
