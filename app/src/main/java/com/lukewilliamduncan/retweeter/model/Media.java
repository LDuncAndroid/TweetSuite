package com.lukewilliamduncan.retweeter.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lukeduncan on 13/12/14.
 */
public class Media implements Entity {

    @SerializedName("id")
    private int mId;

    @SerializedName("id_str")
    private String mIdString;

    @SerializedName("media_url")
    private String mMediaUrl;

    @SerializedName("media_url_https")
    private String mMediaUrlHttps;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("display_url")
    private String mDisplayUrl;

    @SerializedName("expanded_url")
    private String mExpandedUrl;

    @SerializedName("type")
    private String mType;

    @SerializedName("sizes")
    private Sizes mSizes;
    
    @SerializedName("indices")
    private int[] mIndices;

    public int getId() {
        return mId;
    }

    public String getIdString() {
        return mIdString;
    }

    public String getMediaUrl() {
        return mMediaUrl;
    }

    public String getMediaUrlHttps() {
        return mMediaUrlHttps;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getDisplayUrl() {
        return mDisplayUrl;
    }

    public String getExpandedUrl() {
        return mExpandedUrl;
    }

    public String getType() {
        return mType;
    }

    public Sizes getSizes() {
        return mSizes;
    }

    public int[] getIndices() {
        return mIndices;
    }
}
