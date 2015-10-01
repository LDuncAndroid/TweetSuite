package com.lukewilliamduncan.retweeter.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lukeduncan on 13/12/14.
 */
public class Sizes {

    @SerializedName("medium")
    private Size mMedium;
    
    @SerializedName("thumb")
    private Size mThumb;
    
    @SerializedName("small")
    private Size mSmall;
    
    @SerializedName("large")
    private Size mLarge;

    public Size getMedium() {
        return mMedium;
    }

    public Size getThumb() {
        return mThumb;
    }

    public Size getSmall() {
        return mSmall;
    }

    public Size getLarge() {
        return mLarge;
    }
}
