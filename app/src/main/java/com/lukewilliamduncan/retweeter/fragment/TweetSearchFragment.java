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
import com.lukewilliamduncan.retweeter.model.Tweet;
import com.lukewilliamduncan.retweeter.social.TweetStream;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.TweetViewFetchAdapter;

import java.util.ArrayList;

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

    private TweetViewFetchAdapter<CompactTweetView> mTweetListAdapter;
    private ArrayList<Long> mTweetsIds;
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
            mSearchTerms = getArguments().getStringArray(KEY_SEARCH_TERMS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tweet_search, null);
        ButterKnife.bind(this, rootView);
        setupTweetListAdapter();
        return rootView;
    }

    /**
     * Using deprecated TweetViewFetchAdapter as the Timelines that have superseeded the adapter
     * do not allow for use of the Streaming API
     */
    private void setupTweetListAdapter() {
        mTweetListAdapter = new TweetViewFetchAdapter<CompactTweetView>(getContext());
        mTweetList.setAdapter(mTweetListAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSession = Twitter.getSessionManager().getActiveSession();
        setupTweetStream();
        startTwitterStream();
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
        if (mTweetStream != null) {
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

    @Override
    public void onDestroy() {
        stopTwitterStream();
        super.onDestroy();
    }

    public void stopTwitterStream() {
        if (mTweetStream != null) {
            mTweetStream.finish();
        }
    }

    @Override
    public void newTweet(Tweet tweet) {
        if (mTweetsIds == null) {
            mTweetsIds = new ArrayList<>();
        }
        mTweetsIds.add(tweet.getId());
        mTweetListAdapter.setTweetIds(mTweetsIds);
    }
}
