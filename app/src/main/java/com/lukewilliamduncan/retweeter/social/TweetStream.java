package com.lukewilliamduncan.retweeter.social;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lukewilliamduncan.retweeter.http.HttpStream;
import com.lukewilliamduncan.retweeter.model.Tweet;

import java.net.MalformedURLException;
import java.net.URL;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

/**
 * This class handles the construction of the URL for the Twitter Streaming API which is passed
 * into a new instance of HttpStream and informs it's listener when a new line has been read
 * by HttpStream
 */
public class TweetStream implements HttpStream.OnLineReadListener {

    private static final String TAG = TweetStream.class.getSimpleName();

    private static final String BASE_STREAM_URL = "https://stream.twitter.com/1.1/statuses/filter.json?";

    public long mTimestamp = 0;

    private final Context mContext;
    private final TweetListener mTweetListener;

    private String mAccessToken = "";
    private String mAccessSecret = "";
    private String mConsumerKey = "";
    private String mConsumerSecret = "";

    private final String[] mSearchTerms;

    private String mFeedUrl;

    private OAuthConsumer mConsumer;
    private Gson mGson;
    private HttpStream mHttpStream;
    private String mOAuthSignedUrl;

    public TweetStream(Context context, TweetListener tweetListener, String accessToken,
                       String accessSecret, String consumerKey, String consumerSecret,
                       String[] searchTerms) {
        mContext = context;
        mTweetListener = tweetListener;
        mAccessToken = accessToken;
        mAccessSecret = accessSecret;
        mConsumerKey = consumerKey;
        mConsumerSecret = consumerSecret;
        mSearchTerms = searchTerms;
        mTimestamp = System.currentTimeMillis();
    }

    /**
     * Setup necessary members which are passed onto instance of HttpStream.
     * Throws TweetStreamException when an an error occurs
     *
     * @throws TweetStreamException
     */
    public void init() throws TweetStreamException {
        mGson = new GsonBuilder().create();

        mConsumer = new CommonsHttpOAuthConsumer(mConsumerKey, mConsumerSecret);
        mConsumer.setTokenWithSecret(mAccessToken, mAccessSecret);

        mFeedUrl = appendSearchTermsToUrl();

        mOAuthSignedUrl = null;
        try {
            mOAuthSignedUrl = mConsumer.sign(mFeedUrl);
        } catch (Exception e) {
            throw new TweetStreamException("Unable to sign URL with OAuth");
        }

        URL url = null;
        try {
            if (mOAuthSignedUrl != null) {
                url = new URL(mOAuthSignedUrl);
            }
        } catch (MalformedURLException e) {
            throw new TweetStreamException("OAuth signed URL is malformed");
        }
        mHttpStream = new HttpStream(url, this);
    }

    private String appendSearchTermsToUrl() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("track=");

        for (int i = 0; i < mSearchTerms.length; i++) {
            stringBuilder.append(mSearchTerms[i]);
            if (i != mSearchTerms.length - 1) {
                stringBuilder.append(",");
            }
        }

        return BASE_STREAM_URL + stringBuilder.toString();
    }

    /**
     * Start HttpStream on another Thread
     */
    public void start() {
        mHttpStream.start();
    }

    /**
     * This implemented method is called when HttpStream informs it's listeners that there is a new
     * line that has been read
     *
     * @param line
     */
    @Override
    public void lineRead(String line) {
        informListener(mGson.fromJson(line, Tweet.class));
    }

    /**
     * Called to inform the TweetListener that a new Tweet has been read.
     * This must be run on the UI Thread if the TweetListener is an Activity or Fragment
     *
     * @param tweet
     */
    private void informListener(final Tweet tweet) {
        if (mContext instanceof Activity) {
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTweetListener.newTweet(tweet);
                }
            });
        }
    }

    /**
     * Stop the HttpStream
     */
    public void finish() {
        mHttpStream.finish();
    }

    public interface TweetListener {
        void newTweet(Tweet tweet);
    }

    public class TweetStreamException extends Exception {

        public TweetStreamException(String detailMessage) {
            super(detailMessage);
        }
    }
}