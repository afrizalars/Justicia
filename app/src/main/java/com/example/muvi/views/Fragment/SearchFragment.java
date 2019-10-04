package com.example.muvi.views.Fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.muvi.R;
import com.example.muvi.adapter.CardViewMovieAdapter;
import com.example.muvi.adapter.CardViewTvShowAdapter;
import com.example.muvi.models.MovieModel;
import com.example.muvi.models.TvShowModel;
import com.example.muvi.viewmodels.MovieSearchViewModel;
import com.example.muvi.viewmodels.MovieViewModel;
import com.example.muvi.viewmodels.TvSearchViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;


public class SearchFragment extends Fragment {

    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    private RecyclerView rvSearchMovie;
    ProgressBar loading;
    RadioGroup radioGroup;
    private ArrayList<MovieModel> list = new ArrayList<>();
    private MovieSearchViewModel viewModel;
    private TvSearchViewModel viewModelTv;
    private CardViewMovieAdapter adapter;
    private CardViewTvShowAdapter adapterTv;

    private SearchView searchView;
    private boolean isMovie = true;


    private Observer<ArrayList<MovieModel>> getMovie = new Observer<ArrayList<MovieModel>>() {
        @Override
        public void onChanged(@Nullable ArrayList<MovieModel> movies) {
            if (movies != null) {
                adapter.setData(movies);
                loading.setVisibility(View.GONE);
            }
        }
    };

    private Observer<ArrayList<TvShowModel>> getTv = new Observer<ArrayList<TvShowModel>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShowModel> tvs) {
            if (tvs != null) {
                adapterTv.setData(tvs);
                loading.setVisibility(View.GONE);
            }
        }
    };

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Radio Group
        radioGroup = view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                isMovie = checkedId == 2131230873;
            }
        });

//        Loading init
        loading = view.findViewById(R.id.loading);

//        ViewModel
        viewModel = ViewModelProviders.of(this).get(MovieSearchViewModel.class);

//        ViewModelTv
        viewModelTv = ViewModelProviders.of(this).get(TvSearchViewModel.class);

//        SearchView
        searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (isMovie){
                    viewModel.getMovies().observe(getViewLifecycleOwner(), getMovie);
                    viewModel.setMoviesSearch(s);
                    showRecyclerCardView();
                    loading.setVisibility(View.VISIBLE);
                }
                else {
                    viewModelTv.getTv().observe(getViewLifecycleOwner(), getTv);
                    viewModelTv.setTvSearch(s);
                    rvSearchMovie.setLayoutManager(linearLayoutManager);
                    rvSearchMovie.setAdapter(adapterTv);
                    loading.setVisibility(View.VISIBLE);
                }

                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });



        adapter = new CardViewMovieAdapter(getContext());
        adapter.notifyDataSetChanged();

        adapterTv = new CardViewTvShowAdapter(getContext());
        adapterTv.notifyDataSetChanged();

        rvSearchMovie = view.findViewById(R.id.rv_search);
        rvSearchMovie.setHasFixedSize(true);
    }

    private void showRecyclerCardView() {
        rvSearchMovie.setLayoutManager(linearLayoutManager);
        rvSearchMovie.setAdapter(adapter);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }


}
