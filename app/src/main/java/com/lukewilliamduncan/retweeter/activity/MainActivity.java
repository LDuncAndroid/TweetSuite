package com.lukewilliamduncan.retweeter.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lukewilliamduncan.retweeter.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolBar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupToolbar();
    }

    private void setupToolbar() {
        mToolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(mToolbar);
    }
}