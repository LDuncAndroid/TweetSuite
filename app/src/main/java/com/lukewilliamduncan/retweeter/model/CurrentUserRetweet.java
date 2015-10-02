package com.lukewilliamduncan.retweeter.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lukeduncan on 13/12/14.
 */
public class CurrentUserRetweet {
    
    @SerializedName("id")
    private long mId;
    
    @SerializedName("id_str")
    private String mIdString;

    public String getIdString() {
        return mIdString;
    }

    public long getId() {
        return mId;
    }
}
