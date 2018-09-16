package com.powercode.test.max.moviebook.model.entity;

import android.arch.persistence.room.Ignore;

public class BaseRestModel {
    @Ignore
    String Error;
    @Ignore
    boolean Response;

    @Ignore
    public String getError() {
        return Error;
    }

    @Ignore
    public boolean getResponse() {
        return Response;
    }
}
