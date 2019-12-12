package com.example.muvi.views.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.muvi.R;
import com.example.muvi.adapter.CardViewMovieAdapter;
import com.example.muvi.adapter.CardViewTvShowAdapter;
import com.example.muvi.adapter.justicia.LawyerAdapter;
import com.example.muvi.adapter.justicia.LawyerSearchAdapter;
import com.example.muvi.models.LawyerPopuler.LawyerPopulerModel;
import com.example.muvi.models.LawyerSearch.LawyerSearchModel;
import com.example.muvi.models.ListJenis.ListJenisModel;
import com.example.muvi.models.MovieModel;
import com.example.muvi.models.TvShowModel;
import com.example.muvi.network.Client;
import com.example.muvi.network.Interface;
import com.example.muvi.viewmodels.MovieViewModel;
import com.example.muvi.viewmodels.TvShowViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchLawyerFragment extends Fragment implements AdapterView.OnItemSelectedListener  {

    private final LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2);

    private RecyclerView rvSearch;
    private RecyclerView rvTv;

    private Spinner search_spinner;
    ProgressBar loading;


    private LawyerSearchAdapter adapter;

    private Interface anInterface;

    public SearchLawyerFragment() {
        // Required empty public constructor
    }

    public void getLawyerPopuler() {
        retrofit2.Call<LawyerSearchModel> listModelCall = anInterface.getLawyer();
        listModelCall.enqueue(new Callback<LawyerSearchModel>() {
            @Override
            public void onResponse(Call<LawyerSearchModel> call, Response<LawyerSearchModel> response) {
                rvSearch.setAdapter(new LawyerSearchAdapter(response.body()));
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<LawyerSearchModel> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }

    public void getListJenis() {
        retrofit2.Call<ListJenisModel> listModelCall = anInterface.getListJenis();
        listModelCall.enqueue(new Callback<ListJenisModel>() {
            @Override
            public void onResponse(Call<ListJenisModel> call, Response<ListJenisModel> response) {
                // Array of choices
                String colors[] = new String[response.body().getData().size()];
                for (int i = 0; i<response.body().getData().size(); i++){
                    colors[i] = response.body().getData().get(i).getJenis();
                }
                //create adapter
                // Application of the Array to the Spinner
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, colors);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                search_spinner.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onFailure(Call<ListJenisModel> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_lawyer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loading = view.findViewById(R.id.loading);
        anInterface = Client.getClient().create(Interface.class);
        loading.setVisibility(View.VISIBLE);
        rvSearch = view.findViewById(R.id.rv_search_lawyer);

        getLawyerPopuler();
        getListJenis();

        showRecyclerCardView();
        search_spinner = view.findViewById(R.id.search_spinner);
        //add on item selected listerners to spinner
        search_spinner.setOnItemSelectedListener(this);


    }

    private void showRecyclerCardView(){
        rvSearch.setLayoutManager(linearLayoutManager);
        rvSearch.setAdapter(adapter);
    }

    //Do something when the item is selected
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        //getting label name of the selected spinner
        String message = adapterView.getItemAtPosition(i).toString();

        //showing in Toast selected item name
        Log.d("spinner", "onItemSelected: " + message);
        if (message.equals("Semua Bidang")){
            getLawyerPopuler();
            Log.d("tes", "filter");

        } else {
            CariLawyer(Client.getClient().create(Interface.class),message);
            Log.d("tes", "all");
        }
    }

    //may keep blank
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void CariLawyer (Interface apiInterface, String jenis){
        Call<LawyerSearchModel> call = apiInterface.cariLawyer(jenis);

        call.enqueue(new Callback<LawyerSearchModel>() {
            @Override
            public void onResponse(Call<LawyerSearchModel> call, Response<LawyerSearchModel> response) {
                rvSearch.setAdapter(new LawyerSearchAdapter(response.body()));
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<LawyerSearchModel> call, Throwable t) {
                Log.d("API", t.getMessage());
            }
        });
    }

}
