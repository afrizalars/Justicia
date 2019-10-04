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
import com.example.muvi.adapter.CardViewMovieAdapter;
import com.example.muvi.models.MovieModel;
import com.example.muvi.viewmodels.MovieViewModel;
import java.util.ArrayList;


public class MovieFragment extends Fragment {


    private final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    private RecyclerView rvMovie;
    ProgressBar loading;
    private ArrayList<MovieModel> list = new ArrayList<>();
    private MovieViewModel viewModel;
    private CardViewMovieAdapter adapter;

    private Observer<ArrayList<MovieModel>> getMovie = new Observer<ArrayList<MovieModel>>() {
        @Override
        public void onChanged(@Nullable ArrayList<MovieModel> movies) {
            if (movies != null) {
                adapter.setData(movies);
                loading.setVisibility(View.GONE);
            }
        }
    };

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loading = view.findViewById(R.id.loading);

        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getMovies().observe(this, getMovie);
        viewModel.setMovies();
        loading.setVisibility(View.VISIBLE);

        adapter = new CardViewMovieAdapter(getContext());
        adapter.notifyDataSetChanged();

        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);
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
    }

}
