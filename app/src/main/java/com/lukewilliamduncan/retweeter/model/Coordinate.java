package com.lukewilliamduncan.retweeter.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lukeduncan on 13/12/14.
 */
public class Coordinate {

    @SerializedName("coordinates")
    private float[] mCoordinates;

    @SerializedName("type")
    private String mType;

    public float[] getCoordinates() {
        return mCoordinates;
    }

    public String getType() {
        return mType;
    }
}
