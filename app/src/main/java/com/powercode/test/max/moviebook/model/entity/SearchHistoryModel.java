package com.powercode.test.max.moviebook.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "history")
public class SearchHistoryModel {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public int position;
    public String search;
    public long timestamp;
}
