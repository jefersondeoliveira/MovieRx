package com.example.jeferson.movierx.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jeferson.movierx.R;
import com.example.jeferson.movierx.data.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jeferson on 12/09/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemViewHolder>{

    private List<Movie> mMovies;
    private Context mContext;
    private MovieListener mMovieListener;
    private boolean mLoading;
    private int mLastMoviesCount = 0;

    public MovieAdapter() {
        this.mMovies = new ArrayList<>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie_home, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Movie movie = mMovies.get(position);
        if(canLoadMoreMovies(position)) {
            if(mMovieListener != null) {
                mMovieListener.onLoadMoreData();
                mLastMoviesCount = mMovies.size();
                mLoading = true;
            }
        }
        Picasso.with(mContext)
            .load(movie.getMediumCoverImageUrl())
            .placeholder(R.drawable.placeholder)
            .into(holder.ivMoviePoster);
    }

    private boolean canLoadMoreMovies(int currentPosition) {
        return !mLoading && currentPosition == mMovies.size() - 1 &&
                mLastMoviesCount != mMovies.size();
    }

    public void addData(List<Movie> movieList) {
        this.mMovies.addAll(movieList);
        mLoading = false;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivMoviePoster) ImageView ivMoviePoster;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    public void setOnMovieListener(MovieListener movieListener) {
        this.mMovieListener = movieListener;
    }

    public interface MovieListener {
        void onLoadMoreData();
    }

}
