package com.lukewilliamduncan.retweeter.social;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

/**
 * Created by luke on 02/10/15.
 */
public class TweetInteraction {

    public static void retweet(long tweetId, Callback<Tweet> callback) {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();

        statusesService.retweet(tweetId, null, callback);
    }

}
