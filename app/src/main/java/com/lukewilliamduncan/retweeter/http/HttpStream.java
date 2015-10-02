package com.lukewilliamduncan.retweeter.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by luke on 01/10/15.
 */
public class HttpStream implements Runnable {

    private final URL mUrl;
    private final OnLineReadListener mOnLineReadListener;

    private InputStream mInputStream;
    private InputStreamReader mInputStreamReader;
    private BufferedReader mReader;
    private HttpURLConnection mConnection;

    private boolean mIsRunning;

    public HttpStream(URL url, OnLineReadListener listener) {
        mUrl = url;
        mOnLineReadListener = listener;
    }

    /**
     * This method could do with some refactoring to handle the Try/Catches better
     */
    @Override
    public void run() {
        try {
            if (mUrl != null) {
                mConnection = (HttpURLConnection) mUrl.openConnection();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (mConnection != null) {
                mInputStream = mConnection.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mInputStream != null) {
            mInputStreamReader = new InputStreamReader(mInputStream);
        }

        if (mInputStreamReader != null) {
            mReader = new BufferedReader(mInputStreamReader);
        }

        mIsRunning = true;
        while (mIsRunning && mReader != null) {
            String line = null;
            try {
                line = mReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null || line.length() == 0)
                break;
            if (mOnLineReadListener != null) {
                mOnLineReadListener.lineRead(line);
            }
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (mConnection != null) {
            mConnection.disconnect();
        }
    }

    /**
     * Set the mIsRunning field to false to exit loop in run()
     */
    public void finish() {
        mIsRunning = false;
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    public interface OnLineReadListener {
        void lineRead(String line);
    }

}
