package com.lukewilliamduncan.retweeter.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lukewilliamduncan.retweeter.R;
import com.lukewilliamduncan.retweeter.fragment.TweetSearchFragment;
import com.lukewilliamduncan.retweeter.view.SearchEditText;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SearchEditText.OnSearchPressedListener {

    /**
     * Views
     */
    @Bind(R.id.toolBar)
    Toolbar mToolbar;

    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;

    @Bind(R.id.searchEditText)
    SearchEditText mSearchEditText;

    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    @Bind(R.id.noTabText)
    TextView mNoTabText;

    private final ArrayList<String> mSearchTerms = new ArrayList<>();

    private TweetSearchTabPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupToolbar();
        setupTabLayout();
        setupSearchEditText();
        setupViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mSearchTerms.size() > 0) {
            menu.findItem(R.id.action_remove_tab).setEnabled(true);
        } else {
            menu.findItem(R.id.action_remove_tab).setEnabled(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_tab:
                mSearchEditText.setVisibility(View.VISIBLE);
                return true;
            case R.id.action_remove_tab:
                if (mSearchTerms.size() > 0) {
                    removeCurrentTab();
                }
                return true;
        }
        return false;
    }

    private void setupToolbar() {
        mToolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(mToolbar);
    }

    private void setupTabLayout() {
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupSearchEditText() {
        mSearchEditText.setOnSearchPressedListener(this);
    }

    private void setupViewPager() {
        mPagerAdapter = new TweetSearchTabPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void addNewTab(String tabTitle) {
        mSearchTerms.add(tabTitle);
        mPagerAdapter.notifyDataSetChanged();
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle));
        mTabLayout.getTabAt(mTabLayout.getTabCount() - 1).select();
        if (mTabLayout.getVisibility() == View.GONE) {
            mTabLayout.setVisibility(View.VISIBLE);
        }
        invalidateOptionsMenu();
        if(mNoTabText.getVisibility() == View.VISIBLE) {
            mNoTabText.setVisibility(View.GONE);
        }
    }

    private void removeCurrentTab() {
        int currentPosition = mViewPager.getCurrentItem();
        mSearchTerms.remove(currentPosition);
        mPagerAdapter.notifyDataSetChanged();

        /**
         * This hack is here because TabLayout provided by Android doesn't check if the child is
         * null before checking if it's selected causing a null pointer exception when removing
         * the last tab
         */
        if (mTabLayout.getTabCount() == 1) {
            mTabLayout.setVisibility(View.GONE);
            mTabLayout.removeAllTabs();
        } else {
            mTabLayout.removeTabAt(currentPosition);
        }
        invalidateOptionsMenu();
        if(mTabLayout.getTabCount() == 0) {
            mNoTabText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void searchPressed(String searchTerm) {
        if (searchTerm != null && searchTerm.replace(" ", "").length() > 0) {
            addNewTab(searchTerm);
            mSearchEditText.setVisibility(View.GONE);
            mSearchEditText.setText(null);
        }
    }

    private class TweetSearchTabPagerAdapter extends FragmentStatePagerAdapter {

        public TweetSearchTabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mSearchTerms.size();
        }

        @Override
        public Fragment getItem(int position) {
            return TweetSearchFragment.newInstance(mSearchTerms.get(position).split(" "));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mSearchTerms.get(position);
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}