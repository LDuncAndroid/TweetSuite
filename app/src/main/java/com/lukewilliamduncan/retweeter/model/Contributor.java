package com.lukewilliamduncan.retweeter.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lukeduncan on 13/12/14.
 */
public class Contributor {

    @SerializedName("id")
    private int mId;

    @SerializedName("id_str")
    private String mIdString;

    @SerializedName("screen_name")
    private String mScreenName;

    public int getId() {
        return mId;
    }

    public String getIdString() {
        return mIdString;
    }

    public String getScreenName() {
        return mScreenName;
    }
}
