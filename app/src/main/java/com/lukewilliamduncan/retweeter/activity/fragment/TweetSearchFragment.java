package com.lukewilliamduncan.retweeter.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by luke on 30/09/15.
 */
public class TweetSearchFragment extends Fragment {

    private static final String KEY_SEARCH_TERM = "search_term";

    private String mSearchTerm;

    public static TweetSearchFragment newInstance(String searchTerm) {
        TweetSearchFragment fragment = new TweetSearchFragment();
        Bundle args = new Bundle();
        args.putString(KEY_SEARCH_TERM, searchTerm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(KEY_SEARCH_TERM)) {
            mSearchTerm = getArguments().getString(KEY_SEARCH_TERM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
