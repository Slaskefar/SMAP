package com.example.jeppe.lab5_1_arniemovies;

import com.example.jeppe.lab5_1_arniemovies.model.Movie;

import java.util.ArrayList;

/**
 * Created by Jeppe on 09-10-2017.
 */

public interface MovieSelectorInterface {
    public ArrayList<Movie> getMovieList();
    public Movie getCurrentSelection();
}

