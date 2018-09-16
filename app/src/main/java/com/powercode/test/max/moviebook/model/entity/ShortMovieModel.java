package com.powercode.test.max.moviebook.model.entity;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class ShortMovieModel extends BaseRestModel{

    @PrimaryKey
    @SerializedName("imdbID")
    @NonNull
    public String id;
    public String Title;
    public String Year;
    public String Poster;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortMovieModel that = (ShortMovieModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(Title, that.Title) &&
                Objects.equals(Year, that.Year) &&
                Objects.equals(Poster, that.Poster);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, Title, Year, Poster);
    }
}
