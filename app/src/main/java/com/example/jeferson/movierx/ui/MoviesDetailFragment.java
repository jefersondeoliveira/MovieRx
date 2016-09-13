package com.example.jeferson.movierx.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jeferson.movierx.R;
import com.example.jeferson.movierx.data.model.Movie;
import com.example.jeferson.movierx.data.service.MovieService;
import com.example.jeferson.movierx.data.service.RetrofitFactory;
import com.example.jeferson.movierx.ui.adapter.MovieAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jeferson on 12/09/16.
 */
public class MoviesDetailFragment extends Fragment {

    private static final String MOVIE_KEY = "MOVIE_KEY";

    @BindView(R.id.imgMovieBackground) ImageView mImgMovieBackground;
    @BindView(R.id.txtTitle) TextView mTxtTitle;
    @BindView(R.id.txtMovieYear) TextView mTxtMovieYear;
    @BindView(R.id.txtMovieRuntime) TextView mTxtMovieRuntime;
    @BindView(R.id.txtMovieGenres) TextView mTxtMovieGenres;
    @BindView(R.id.txtMovieDescription) TextView mTxtMovieDescription;

    private MainActivity mActivity;
    private Unbinder unbinder;
    private Movie mMovie;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mActivity = (MainActivity) context;
        }
    }

    public static MoviesDetailFragment newInstance(Movie movie) {
        MoviesDetailFragment fragment = new MoviesDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MOVIE_KEY, movie.toString());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovie = new Gson().fromJson(getArguments().getString(MOVIE_KEY), Movie.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    private void initComponents() {
        Picasso.with(getActivity())
                .load(mMovie.getBackgroundImageOriginalUrl())
                .into(mImgMovieBackground);

        mTxtTitle.setText(mMovie.getTitle());
        mTxtMovieYear.setText(mMovie.getYear());
        mTxtMovieRuntime.setText(mMovie.getRuntime() + " m");
        mTxtMovieGenres.setText(mMovie.getGenreList());
        mTxtMovieDescription.setText(mMovie.getSynopsis());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
