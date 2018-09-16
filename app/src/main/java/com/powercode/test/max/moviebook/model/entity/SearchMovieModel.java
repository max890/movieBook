package com.powercode.test.max.moviebook.model.entity;

import java.util.List;

public class SearchMovieModel extends BaseRestModel {

    List<ShortMovieModel> Search;

    public List<ShortMovieModel> getSearch() {
        return Search;
    }
}
