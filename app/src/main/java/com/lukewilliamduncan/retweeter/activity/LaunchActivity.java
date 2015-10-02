package com.lukewilliamduncan.retweeter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lukewilliamduncan.retweeter.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by luke on 01/10/15.
 */
public class LaunchActivity extends AppCompatActivity {

    @Bind(R.id.loginButton)
    TwitterLoginButton mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!hasTwitterSession()) {
            setContentView(R.layout.activity_launch);

            ButterKnife.bind(this);

            mLoginButton.setCallback(new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    startMainActivity();
                }

                @Override
                public void failure(TwitterException exception) {
                    Toast.makeText(LaunchActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            startMainActivity();
        }
    }

    private boolean hasTwitterSession() {
        return Twitter.getSessionManager().getActiveSession() != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        mLoginButton.onActivityResult(requestCode, resultCode, data);
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
