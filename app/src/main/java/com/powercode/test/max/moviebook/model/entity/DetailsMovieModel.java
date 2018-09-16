package com.powercode.test.max.moviebook.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import java.util.List;

@Entity(tableName = "movies")
public class DetailsMovieModel extends ShortMovieModel {

    public String Released;
    public String Director;
    public String Writer;
    public String Actors;
    public String Plot;
    public String Country;
    @Ignore
    public List<RatingMovieModel> Ratings;
    public String Production;
    public String Website;

    public String getReleased() {
        return Released;
    }

    public String getDirector() {
        return Director;
    }

    public String getWriter() {
        return Writer;
    }

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public String getCountry() {
        return Country;
    }

    public List<RatingMovieModel> getRatings() {
        return Ratings;
    }

    public String getProduction() {
        return Production;
    }

    public String getWebsite() {
        return Website;
    }
}
