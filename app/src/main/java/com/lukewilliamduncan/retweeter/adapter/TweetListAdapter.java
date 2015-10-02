package com.lukewilliamduncan.retweeter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lukewilliamduncan.retweeter.model.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 02/10/15.
 */
public class TweetListAdapter extends BaseAdapter {

    private final Context mContext;
    private List<Tweet> mTweets;

    public TweetListAdapter(Context context) {
        mContext = context;
        mTweets = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mTweets.size();
    }

    @Override
    public Tweet getItem(int position) {
        return mTweets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mTweets.get(position).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tweet tweet = mTweets.get(position);
        TextView tv = new TextView(mContext);

        tv.setText(tweet.getText());

        return tv;
    }

    public void addItem(Tweet tweet) {
        mTweets.add(tweet);
        notifyDataSetChanged();
    }
}