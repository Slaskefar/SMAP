package com.example.jeppe.lab5_1_arniemovies;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jeppe.lab5_1_arniemovies.adaptor.MovieListAdaptor;
import com.example.jeppe.lab5_1_arniemovies.model.Movie;

import java.util.ArrayList;

public class OverviewFragment extends Fragment {

    private ListView movieListView;
    private ArrayList<Movie> movies;
    private MovieListAdaptor adaptor;

    private MovieSelectorInterface movieSelector;


    public OverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        movieListView = (ListView) view.findViewById(R.id.movieList);
        updateMovies();

        return view;


    }

    public void setMovies(ArrayList<Movie> movieList) {
        movies = (ArrayList<Movie>) movieList.clone();
    }

    public void updateMovies() {
        if (movieSelector != null) {
            movies = movieSelector.getMovieList();
        }
        if (movies != null) {
            adaptor = new MovieListAdaptor(getActivity(), movies);
            movieListView.setAdapter(adaptor);

//            movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    onMovieSelected(position)
//                }
//            });

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}
