package com.lukewilliamduncan.retweeter.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lukeduncan on 13/12/14.
 */
public class User {

    @SerializedName("contributors_enabled")
    private Boolean mContributorsEnabled;

    @SerializedName("created_at")
    private String mCreatedAt;

    @SerializedName("default_profile")
    private Boolean mDefaultProfile;

    @SerializedName("default_profile_image")
    private Boolean mDefaultProfileImage;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("entities")
    private List<Entity> mEntities;

    @SerializedName("favourites_count")
    private int mFavouritesCount;

    @SerializedName("follow_request_sent")
    private Boolean mFollowRequestSent;

    @SerializedName("following")
    private Boolean mFollowing;

    @SerializedName("followers_count")
    private int mFollowersCount;

    @SerializedName("friends_count")
    private int mFriendsCount;

    @SerializedName("geo_enabled")
    private boolean mGeoEnabled;

    @SerializedName("id")
    private long mId;

    @SerializedName("id_str")
    private String mIdString;

    @SerializedName("is_translator")
    private Boolean mIsTranslator;

    @SerializedName("lang")
    private String mLang;

    @SerializedName("listed_count")
    private int mListedCount;

    @SerializedName("location")
    private String mLocation;

    @SerializedName("name")
    private String mName;

    @SerializedName("profile_background_color")
    private String mProfilebackgroundColor;

    @SerializedName("profile_background_image_url")
    private String mProfileBackgroundImageUrl;

    @SerializedName("profile_background_image_url_https")
    private String mProfileBackgroundImageUrlHttps;

    @SerializedName("profile_background_tile")
    private boolean mProfileBackgroundTile;

    @SerializedName("profile_banner_url")
    private String mProfileBannerUrl;

    @SerializedName("profile_image_url")
    private String mProfileImageUrl;

    @SerializedName("profile_image_url_https")
    private String mProfileImageUrlHttps;

    @SerializedName("profile_link_color")
    private String mProfileLinkColor;

    @SerializedName("profile_sidebar_border_color")
    private String mProfileSidebarBorderColor;

    @SerializedName("profile_sidebar_fill_color")
    private String mProfileSidebarFillColor;

    @SerializedName("profile_text_color")
    private String mProfileTextColor;

    @SerializedName("profile_use_background_image")
    private boolean mProfileUseBackgroundImage;

    @SerializedName("protected")
    private boolean mProtected;

    @SerializedName("screen_name")
    private String mScreenName;

    @SerializedName("status")
    private List<Tweet> mStatus;

    @SerializedName("statuses_count")
    private int mStatusesCount;

    @SerializedName("time_zone")
    private String mTimeZone;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("utc_offset")
    private int mUtcOffset;

    @SerializedName("verified")
    private boolean mVerified;

    @SerializedName("withheld_in_countries")
    private String mWithheldInCountries;

    @SerializedName("withheld_scope")
    private String mWithheldScope;

    public Boolean getContributorsEnabled() {
        return mContributorsEnabled;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public Boolean getDefaultProfile() {
        return mDefaultProfile;
    }

    public Boolean getDefaultProfileImage() {
        return mDefaultProfileImage;
    }

    public String getDescription() {
        return mDescription;
    }

    public List<Entity> getEntities() {
        return mEntities;
    }

    public int getFavouritesCount() {
        return mFavouritesCount;
    }

    public Boolean getFollowRequestSent() {
        return mFollowRequestSent;
    }

    public Boolean getFollowing() {
        return mFollowing;
    }

    public int getFollowersCount() {
        return mFollowersCount;
    }

    public int getFriendsCount() {
        return mFriendsCount;
    }

    public boolean ismGeoEnabled() {
        return mGeoEnabled;
    }

    public long getId() {
        return mId;
    }

    public String getIdString() {
        return mIdString;
    }

    public Boolean getIsTranslator() {
        return mIsTranslator;
    }

    public String getLang() {
        return mLang;
    }

    public int getListedCount() {
        return mListedCount;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getName() {
        return mName;
    }

    public String getProfilebackgroundColor() {
        return mProfilebackgroundColor;
    }

    public String getProfileBackgroundImageUrl() {
        return mProfileBackgroundImageUrl;
    }

    public String getProfileBackgroundImageUrlHttps() {
        return mProfileBackgroundImageUrlHttps;
    }

    public boolean ismProfileBackgroundTile() {
        return mProfileBackgroundTile;
    }

    public String getProfileBannerUrl() {
        return mProfileBannerUrl;
    }

    public String getProfileImageUrl() {
        return mProfileImageUrl;
    }

    public String getProfileImageUrlHttps() {
        return mProfileImageUrlHttps;
    }

    public String getProfileLinkColor() {
        return mProfileLinkColor;
    }

    public String getProfileSidebarBorderColor() {
        return mProfileSidebarBorderColor;
    }

    public String getProfileSidebarFillColor() {
        return mProfileSidebarFillColor;
    }

    public String getProfileTextColor() {
        return mProfileTextColor;
    }

    public boolean ismProfileUseBackgroundImage() {
        return mProfileUseBackgroundImage;
    }

    public boolean ismProtected() {
        return mProtected;
    }

    public String getScreenName() {
        return mScreenName;
    }

    public List<Tweet> getStatus() {
        return mStatus;
    }

    public int getStatusesCount() {
        return mStatusesCount;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public String getUrl() {
        return mUrl;
    }

    public int getUtcOffset() {
        return mUtcOffset;
    }

    public boolean ismVerified() {
        return mVerified;
    }

    public String getWithheldInCountries() {
        return mWithheldInCountries;
    }

    public String getWithheldScope() {
        return mWithheldScope;
    }
}
