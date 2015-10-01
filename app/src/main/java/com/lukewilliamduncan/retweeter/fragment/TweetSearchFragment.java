package com.lukewilliamduncan.retweeter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lukewilliamduncan.retweeter.BuildConfig;
import com.lukewilliamduncan.retweeter.R;
import com.lukewilliamduncan.retweeter.model.Tweet;
import com.lukewilliamduncan.retweeter.social.TweetStream;

/**
 * Created by luke on 30/09/15.
 */
public class TweetSearchFragment extends Fragment implements TweetStream.TweetListener {

    private static final String KEY_SEARCH_TERMS = "search_terms";

    private String[] mSearchTerms;
    private TweetStream mTweetStream;

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
        return new View(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTweetStream = new TweetStream(
                getActivity(),
                this,
                "PlaceholderAccessToken",
                "PlaceholderAccessSecret",
                BuildConfig.TWITTER_CONSUMER_KEY,
                BuildConfig.TWITTER_CONSUMER_SECRET,
                mSearchTerms);

        try {
            mTweetStream.init();
            mTweetStream.start();
        } catch (TweetStream.TweetStreamException e) {
            Toast.makeText(getContext(),
                    getString(R.string.unable_to_start_tweet_stream) + " for: " + mSearchTerms + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        mTweetStream.finish();
        super.onDestroy();
    }

    @Override
    public void newTweet(Tweet tweet) {
        if (getContext() != null) {
            Toast.makeText(getContext(), tweet.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
