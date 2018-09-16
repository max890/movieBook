package com.powercode.test.max.moviebook.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ratingMovie",
        foreignKeys = {
                @ForeignKey(entity = DetailsMovieModel.class, parentColumns = "id", childColumns = "MovieId", onDelete = ForeignKey.CASCADE)
        })
public class RatingMovieModel {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String Source;
    public String Value;
    public String MovieId;
}
