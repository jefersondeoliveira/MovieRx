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
import android.widget.ProgressBar;

import com.example.jeferson.movierx.R;
import com.example.jeferson.movierx.data.model.Movie;
import com.example.jeferson.movierx.data.service.MovieService;
import com.example.jeferson.movierx.data.service.RetrofitFactory;
import com.example.jeferson.movierx.ui.adapter.MovieAdapter;

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
public class MoviesFragment extends Fragment implements MovieAdapter.MovieListener {

    @BindView(R.id.rvMovies) RecyclerView mRvMovies;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private MainActivity mActivity;
    private Unbinder unbinder;
    private Retrofit mRetrofit;
    private int mCurrentMoviePageNumber = 1;
    private MovieAdapter movieAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mActivity = (MainActivity) context;
        }
    }

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movies, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    private void initComponents() {
        mRetrofit = RetrofitFactory.getInstance();

        mRvMovies.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        movieAdapter = new MovieAdapter();
        movieAdapter.setOnMovieListener(this);
        mRvMovies.setAdapter(movieAdapter);

        loadMoviesFromServer();
    }

    private void loadMoviesFromServer() {

        mProgressBar.setVisibility(View.VISIBLE);

        Observable<List<Movie>> observable = mRetrofit.create(MovieService.class)
                .getAllMovies(mCurrentMoviePageNumber);

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(!MoviesFragment.this.isVisible()) return;

                        new AlertDialog.Builder(getActivity())
                                .setMessage(e.getMessage())
                                .setPositiveButton(getString(R.string.ok), null)
                                .create().show();
                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        if(!MoviesFragment.this.isVisible()) return;
                        mProgressBar.setVisibility(View.GONE);
                        movieAdapter.addData(movies);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onLoadMoreData() {
        mCurrentMoviePageNumber++;
        loadMoviesFromServer();
    }
}
