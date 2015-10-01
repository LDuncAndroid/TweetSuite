package com.lukewilliamduncan.retweeter.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lukewilliamduncan.retweeter.util.ViewUtils;

/**
 * Created by luke on 30/09/15.
 */
public class SearchEditText extends EditText implements TextView.OnEditorActionListener {

    private OnSearchPressedListener mOnSearchPressedListener;

    public SearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setSingleLine();
        setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (mOnSearchPressedListener != null) {
                mOnSearchPressedListener.searchPressed(String.valueOf(getText()));
            }
        }
        return false;
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        if (!isInEditMode()) {
            if (visibility == View.VISIBLE) {
                requestFocus();
                ViewUtils.showKeyboard(getContext(), this);
            } else {
                clearFocus();
                ViewUtils.hideKeyboard(getContext(), this);
            }
        }
    }

    public void setOnSearchPressedListener(OnSearchPressedListener listener) {
        mOnSearchPressedListener = listener;
    }

    public interface OnSearchPressedListener {
        void searchPressed(String searchTerm);
    }

}
