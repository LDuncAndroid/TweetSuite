package com.lukewilliamduncan.retweeter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.lukewilliamduncan.retweeter.BuildConfig;
import com.lukewilliamduncan.retweeter.R;
import com.lukewilliamduncan.retweeter.adapter.TweetListAdapter;
import com.lukewilliamduncan.retweeter.model.Tweet;
import com.lukewilliamduncan.retweeter.social.TweetInteraction;
import com.lukewilliamduncan.retweeter.social.TweetStream;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by luke on 30/09/15.
 */
public class TweetSearchFragment extends Fragment implements TweetStream.TweetListener {

    private static final String KEY_SEARCH_TERMS = "search_terms";

    @Bind(R.id.tweetList)
    ListView mTweetList;

    private String[] mSearchTerms;
    private TweetStream mTweetStream;

    private TweetListAdapter mTweetListAdapter;
    private TwitterSession mSession;

    public static TweetSearchFragment newInstance(String[] searchTerms) {
        TweetSearchFragment fragment = new TweetSearchFragment();
        Bundle args = new Bundle();
        args.putStringArray(KEY_SEARCH_TERMS, searchTerms);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(KEY_SEARCH_TERMS)) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tweet_search, null);
        ButterKnife.bind(this, rootView);
        setupTweetListAdapter();
        setupTweetList();
        return rootView;
    }

    private void setupTweetListAdapter() {
        mTweetListAdapter = new TweetListAdapter(getContext(), new TweetListAdapter.OnRetweetButtonPressedListener() {
            @Override
            public void onRetweetButtonPressed(long tweetId) {
                retweet(tweetId);
            }
        });
    }

    private void setupTweetList() {
        mTweetList.setAdapter(mTweetListAdapter);
    }

    /**
     * Called when the ViewPager changes pages
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mSearchTerms = getArguments().getStringArray(KEY_SEARCH_TERMS);
            mSession = Twitter.getSessionManager().getActiveSession();
            setupTweetStream();
            startTwitterStream();
        } else {
            stopTwitterStream();
        }
    }

    private void setupTweetStream() {
        if (mSession != null) {
            TwitterAuthToken authToken = mSession.getAuthToken();
            mTweetStream = new TweetStream(
                    getActivity(),
                    this,
                    authToken.token,
                    authToken.secret,
                    BuildConfig.TWITTER_CONSUMER_KEY,
                    BuildConfig.TWITTER_CONSUMER_SECRET,
                    mSearchTerms);
        }
    }

    public void startTwitterStream() {
        if (mTweetStream != null && !mTweetStream.isRunning()) {
            try {
                mTweetStream.init();
                mTweetStream.start();
            } catch (TweetStream.TweetStreamException e) {
                Toast.makeText(getContext(),
                        getString(R.string.unable_to_start_tweet_stream) + " for: " + mSearchTerms + e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void stopTwitterStream() {
        if (mTweetStream != null) {
            mTweetStream.finish();
        }
    }

    @Override
    public void newTweet(Tweet tweet) {
        mTweetListAdapter.addItem(tweet);
    }

    private void retweet(long tweetId) {
        TweetInteraction.retweet(tweetId, new Callback<com.twitter.sdk.android.core.models.Tweet>() {
            @Override
            public void success(Result<com.twitter.sdk.android.core.models.Tweet> result) {
                Toast.makeText(getContext(), getString(R.string.retweeted), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(TwitterException e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
