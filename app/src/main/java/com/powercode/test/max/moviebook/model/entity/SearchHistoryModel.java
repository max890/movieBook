package com.powercode.test.max.moviebook.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity(tableName = "history")
public class SearchHistoryModel {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public int position;
    public String search;
    public long timestamp;

    @Ignore
    public String getDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        return formatter.format(new Date(timestamp));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchHistoryModel that = (SearchHistoryModel) o;
        return id == that.id &&
                position == that.position &&
                timestamp == that.timestamp &&
                Objects.equals(search, that.search);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, position, search, timestamp);
    }
}
