package com.lukewilliamduncan.retweeter.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lukeduncan on 13/12/14.
 */
public class Url implements Entity {

    @SerializedName("url")
    private String mUrl;

    @SerializedName("display_url")
    private String mDisplayUrl;

    @SerializedName("expanded_url")
    private String mExpandedUrl;

    @SerializedName("indices")
    private int[] mIndices;

    public String getUrl() {
        return mUrl;
    }

    public String getDisplayUrl() {
        return mDisplayUrl;
    }

    public String getExpandedUrl() {
        return mExpandedUrl;
    }

    public int[] getIndices() {
        return mIndices;
    }
}
