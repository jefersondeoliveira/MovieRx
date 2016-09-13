package com.example.jeferson.movierx.data.model;

/**
 * Created by jeferson on 12/09/16.
 */
public enum MovieGenre {
    NONE(""),
    ACTION("action"),
    ADVENTURE("adventure"),
    ANIMATION("animation"),
    COMEDY("comedy"),
    HORROR("horror"),
    SCIFI("sci-fi");

    private String genre;

    MovieGenre(String genre) {
        this.genre = genre;
    }

    public String getValue() {
        return genre;
    }
}
