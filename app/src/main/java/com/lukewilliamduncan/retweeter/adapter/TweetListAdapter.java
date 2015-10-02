package com.lukewilliamduncan.retweeter.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukewilliamduncan.retweeter.R;
import com.lukewilliamduncan.retweeter.model.Tweet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by luke on 02/10/15.
 */
public class TweetListAdapter extends BaseAdapter {

    private final Context mContext;
    private final OnRetweetButtonPressedListener mOnRetweetButtonPressedListener;

    private List<Tweet> mTweets;
    private final ArrayList<Long> mTweetsIds;

    /**
     * @param context
     * @param listener
     */
    public TweetListAdapter(Context context, OnRetweetButtonPressedListener listener) {
        mContext = context;
        mOnRetweetButtonPressedListener = listener;
        mTweets = new ArrayList<>();
        mTweetsIds = new ArrayList<>();
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
        ViewHolder vh;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.tweet_view, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final Tweet tweet = mTweets.get(position);
        vh.userName.setText(tweet.getUser().getName());
        vh.message.setText(tweet.getText());
        vh.retweetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRetweetButtonPressedListener != null) {
                    mOnRetweetButtonPressedListener.onRetweetButtonPressed(tweet.getId());
                }
            }
        });

        Picasso.with(mContext)
                .load(tweet.getUser().getProfileImageUrl())
                .noPlaceholder()
                .resize(500, 500)
                .onlyScaleDown()
                .into(vh.userImage);

        return convertView;
    }

    public void addItem(Tweet tweet) {
        if (!mTweetsIds.contains(tweet.getId())) {
            mTweets.add(tweet);
            notifyDataSetChanged();
        }
    }

    public interface OnRetweetButtonPressedListener {
        void onRetweetButtonPressed(long tweetId);
    }

    static class ViewHolder {
        @Bind(R.id.tweetUser)
        TextView userName;
        @Bind(R.id.tweetMessage)
        TextView message;
        @Bind(R.id.tweetUserImage)
        ImageView userImage;
        @Bind(R.id.retweetBtn)
        Button retweetBtn;

        public ViewHolder(View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }
}