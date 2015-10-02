package com.lukewilliamduncan.retweeter.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lukeduncan on 13/12/14.
 */
public class PlaceAttributes {

    @SerializedName("street_address")
    private String mStreetAddress;

    @SerializedName("locality")
    private String mLocality;

    @SerializedName("region")
    private String mRegion;

    @SerializedName("iso3")
    private String mCountryCode;

    @SerializedName("postal_code")
    private String mPostalCode;

    @SerializedName("phone")
    private String mPhone;

    @SerializedName("twitter")
    private String mTwitter;

    @SerializedName("url")
    private String mUrl;

    public String getStreetAddress() {
        return mStreetAddress;
    }

    public String getLocality() {
        return mLocality;
    }

    public String getRegion() {
        return mRegion;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public String getPostalCode() {
        return mPostalCode;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getTwitter() {
        return mTwitter;
    }

    public String getUrl() {
        return mUrl;
    }
}
