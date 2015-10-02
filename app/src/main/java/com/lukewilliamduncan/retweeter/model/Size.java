package com.lukewilliamduncan.retweeter.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lukeduncan on 13/12/14.
 */
public class Size {
    
    @SerializedName("w")
    private int mWidth;
    
    @SerializedName("h")
    private int mHeight;
    
    @SerializedName("resize")
    private String mResize;

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public String getResize() {
        return mResize;
    }
}
