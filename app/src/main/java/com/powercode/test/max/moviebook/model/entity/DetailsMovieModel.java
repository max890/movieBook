package com.powercode.test.max.moviebook.model.entity;

import java.util.List;

public class DetailsMovieModel extends ShortMovieModel {

    public String Released;
    public String Director;
    public String Writer;
    public String Actors;
    public String Plot;
    public String Country;
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
