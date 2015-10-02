package com.lukewilliamduncan.retweeter.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lukeduncan on 13/12/14.
 */
public class Tweet {

    @SerializedName("contributors")
    private List<Contributor> mContributors;

    @SerializedName("coordinates")
    private Coordinate mCoordinates;

    @SerializedName("created_at")
    private String mCreatedAt;

    @SerializedName("current_user_retweet")
    private CurrentUserRetweet mCurrentUserRetweet;

    @SerializedName("entities")
    private Entities mEntities;

    @SerializedName("favourite_count")
    private int mFavouriteCount;

    @SerializedName("favorited")
    private boolean mFavorited;

    @SerializedName("filter_level")
    private String mFilterLevel;

    @SerializedName("id")
    private long mId;

    @SerializedName("id_str")
    private String mIdString;

    @SerializedName("in_reply_to_screen_name")
    private String mInReplyToScreenName;

    @SerializedName("in_reply_to_status_id")
    private String mInReplyToStatusId;

    @SerializedName("in_reply_to_status_id_str")
    private String mInReplyToStatusIdString;

    @SerializedName("in_reply_to_user_id")
    private String mInReplyToUserId;

    @SerializedName("in_reply_to_user_id_str")
    private String mInReplyToUserIdString;

    @SerializedName("lang")
    private String mLang;

    @SerializedName("place")
    private Place mPlace;

    @SerializedName("possibly_sensitive")
    private Boolean mPossibleSensitive;

    @SerializedName("retweet_count")
    private int mRetweetCount;

    @SerializedName("retweeted")
    private Boolean mRetweeted;

    @SerializedName("retweeted_status")
    private Tweet mRetweetedStatus;

    @SerializedName("text")
    private String mText;

    @SerializedName("truncated")
    private Boolean mTruncated;

    @SerializedName("user")
    private User mUser;

    @SerializedName("withheld_copyright")
    private boolean mWithheldCopyright;

    @SerializedName("withheld_in_countries")
    private String[] mWitheldInCountries;

    @SerializedName("withheld_scope")
    private String mWithheldScope;

    public List<Contributor> getContributors() {
        return mContributors;
    }

    public Coordinate getCoordinates() {
        return mCoordinates;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public CurrentUserRetweet getCurrentUserRetweet() {
        return mCurrentUserRetweet;
    }

    public Entities getEntities() {
        return mEntities;
    }

    public int getFavouriteCount() {
        return mFavouriteCount;
    }

    public boolean ismFavorited() {
        return mFavorited;
    }

    public String getFilterLevel() {
        return mFilterLevel;
    }

    public long getId() {
        return mId;
    }

    public String getIdString() {
        return mIdString;
    }

    public String getInReplyToScreenName() {
        return mInReplyToScreenName;
    }

    public String getInReplyToStatusId() {
        return mInReplyToStatusId;
    }

    public String getInReplyToStatusIdString() {
        return mInReplyToStatusIdString;
    }

    public String getInReplyToUserId() {
        return mInReplyToUserId;
    }

    public String getInReplyToUserIdString() {
        return mInReplyToUserIdString;
    }

    public String getLang() {
        return mLang;
    }

    public Place getPlace() {
        return mPlace;
    }

    public Boolean getPossibleSensitive() {
        return mPossibleSensitive;
    }

    public int getRetweetCount() {
        return mRetweetCount;
    }

    public Boolean getRetweeted() {
        return mRetweeted;
    }

    public Tweet getRetweetedStatus() {
        return mRetweetedStatus;
    }

    public String getText() {
        return mText;
    }

    public Boolean getTruncated() {
        return mTruncated;
    }

    public User getUser() {
        return mUser;
    }

    public boolean ismWithheldCopyright() {
        return mWithheldCopyright;
    }

    public String[] getWitheldInCountries() {
        return mWitheldInCountries;
    }

    public String getWithheldScope() {
        return mWithheldScope;
    }
}
