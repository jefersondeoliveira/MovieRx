package com.example.jeferson.movierx.data.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jeferson on 12/09/16.
 */
public class Movie {

    private Long id;
    private String title;
    private String year;
    private String runtime;
    private List<String> genres;
    private String synopsis;
    private String language;
    @SerializedName("background_image_original")
    private String backgroundImageOriginalUrl;
    @SerializedName("medium_cover_image")
    private String mediumCoverImageUrl;

    public String getGenreList() {
        StringBuilder genreList = new StringBuilder();

        for(String genre : genres) {
            genreList.append(genre);
            genreList.append(" ");
        }

        return genreList.toString();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getLanguage() {
        return language;
    }

    public String getBackgroundImageOriginalUrl() {
        return backgroundImageOriginalUrl;
    }

    public String getMediumCoverImageUrl() {
        return mediumCoverImageUrl;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
