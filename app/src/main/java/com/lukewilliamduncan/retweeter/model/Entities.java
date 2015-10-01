package com.lukewilliamduncan.retweeter.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lukeduncan on 13/12/14.
 */
public class Entities {

    @SerializedName("hashtags")
    private List<HashTag> mHashTags;

}
