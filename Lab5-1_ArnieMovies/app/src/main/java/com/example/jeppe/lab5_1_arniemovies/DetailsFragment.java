package com.example.jeppe.lab5_1_arniemovies;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jeppe.lab5_1_arniemovies.model.Movie;

public class DetailsFragment extends Fragment {

    private TextView txtTitle;
    private TextView txtYear;
    private TextView txtRole;

    private MovieSelectorInterface movieSelector;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        txtRole = (TextView) view.findViewById(R.id.txtRole);
        txtTitle = (TextView) view.findViewById(R.id.txtMovieName);
        txtYear = (TextView) view.findViewById(R.id.txtYear);

        updateMovie();

        return view;
    }

    private void updateMovie() {
        if(movieSelector!=null) {
            setMovie(movieSelector.getCurrentSelection());
        }
    }

    public void setMovie(Movie movie){
        if(txtTitle!=null && txtYear!=null && txtRole!=null) {
            txtTitle.setText(movie.getName());
            txtYear.setText(Integer.toString(movie.getYear()));
            txtRole.setText("Arnie was playing " + movie.getRole());
        }
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        try {
            movieSelector = (MovieSelectorInterface) activity;
        } catch (ClassCastException ex) {
            //Activity does not implement correct interface
            throw new ClassCastException(activity.toString() + " must implement MovieSelectorInterface");
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
