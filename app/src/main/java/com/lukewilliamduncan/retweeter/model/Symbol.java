package com.lukewilliamduncan.retweeter.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lukeduncan on 13/12/14.
 */
public class Symbol implements Entity {
    
    @SerializedName("text")
    private String mText;
    
    @SerializedName("indices")
    private int[] mIndices;

    public String getText() {
        return mText;
    }

    public int[] getIndices() {
        return mIndices;
    }
}
