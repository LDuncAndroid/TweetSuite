package com.lukewilliamduncan.retweeter.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lukeduncan on 13/12/14.
 */
public class Place {

    @SerializedName("attributes")
    private PlaceAttributes mPlaceAttributes;

    public PlaceAttributes getPlaceAttributes() {
        return mPlaceAttributes;
    }
}
