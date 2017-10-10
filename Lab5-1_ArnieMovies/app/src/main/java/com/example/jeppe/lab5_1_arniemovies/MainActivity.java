package com.example.jeppe.lab5_1_arniemovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.jeppe.lab5_1_arniemovies.adaptor.MovieListAdaptor;
import com.example.jeppe.lab5_1_arniemovies.model.Movie;
import com.example.jeppe.lab5_1_arniemovies.util.MovieLoader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieSelectorInterface {

    public enum PhoneMode {PORTRAIT, LANDSCAPE}

    private static final String OVERVIEW_FRAG = "overview_fragment";
    private static final String DETAILS_FRAG = "details_fragment";

    private MovieLoader movieLoader;
    private OverviewFragment overviewFragment;
    private DetailsFragment detailsFragment;

    private ArrayList<Movie> movies;

    private int selectedMovieIndex;

    private LinearLayout overviewContainer;
    private LinearLayout detailsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overviewContainer = (LinearLayout) findViewById(R.id.overview_container);
        detailsContainer = (LinearLayout) findViewById(R.id.details_container);

        movies = new MovieLoader(this).getMovieList();


        if (savedInstanceState == null) {
            selectedMovieIndex = 0;

            overviewFragment = new OverviewFragment();
            detailsFragment = new DetailsFragment();

            overviewFragment.setMovies(movies);
            detailsFragment.setMovie(movies.get(selectedMovieIndex));

            getFragmentManager().beginTransaction()
                    .add(R.id.overview_container, overviewFragment, OVERVIEW_FRAG)
                    .add(R.id.details_container, detailsFragment, DETAILS_FRAG)
                    .commit();
        } else {
            selectedMovieIndex = savedInstanceState.getInt("movie_position");

            overviewFragment = (OverviewFragment)getFragmentManager().findFragmentByTag(OVERVIEW_FRAG);
            if(overviewFragment == null) {
                overviewFragment = new OverviewFragment();
            }

            detailsFragment = (DetailsFragment)getFragmentManager().findFragmentByTag(DETAILS_FRAG);
            if(detailsFragment == null) {
                detailsFragment = new DetailsFragment();
            }
        }

    }

    @Override
    public ArrayList<Movie> getMovieList() {
        return movies;
    }

    @Override
    public Movie getCurrentSelection(){
        if(movies!=null) {
            return movies.get(selectedMovieIndex);
        } else {
            return null;
        }
    }


}
