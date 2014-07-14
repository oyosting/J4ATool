/**
 *
 * Copyright 2014 Cisco. All rights reserved.
 * WebViewFragment.java
 *
 */
package com.cisco.j4atool;

import com.umeng.analytics.MobclickAgent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * @author kevin
 * @date 2014年7月10日
 */
public class WebViewFragment extends Fragment
{
    public static final String WEB_ADDRESS = "web_url";

    public static final String ACTION_BAR_TITLE = "action_bar_title";

    private final String[] mSchemes =
    { "im://", "xmpp://", "ciscoim://", "ciscotel://", "tel://", "ciscojabber://" };

    private String mWebUrl;

    private String mActionBarTitle;

    private WebView mWebView;

    private View mErrorView;

    @Override
    public void onPause()
    {
        super.onPause();
        MobclickAgent.onPageEnd(WebViewFragment.class.toString());
    }

    @Override
    public void onResume()
    {
        super.onResume();
        MobclickAgent.onPageStart(WebViewFragment.class.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_webview, null);
        mWebView = (WebView) rootView.findViewById(R.id.web);
        mErrorView = rootView.findViewById(R.id.error_layout);
        initWebView();
        MyWebViewClient webViewClient = new MyWebViewClient();
        mWebView.setWebViewClient(webViewClient);
        // set action bar title
        mActionBarTitle = getArguments().getString(ACTION_BAR_TITLE);
        getActivity().setTitle(mActionBarTitle);
        // load target url here
        mWebUrl = getArguments().getString(WEB_ADDRESS);
        mWebView.loadUrl(mWebUrl);
        // set actionbar title here
        return rootView;
    }

    private class MyWebViewClient extends WebViewClient
    {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            if (url != null && isProvisionSupportedUrl(url))
            {
                view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                return true;
            }
            else
            {
                view.loadUrl(url);
                return false;
            }
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error)
        {
            handler.proceed();
        }

        /** start loading page, add tip later */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon)
        {
            super.onPageStarted(view, url, favicon);
        }

        /** finish loading page */
        @Override
        public void onPageFinished(WebView view, String url)
        {
            super.onPageFinished(view, url);
        }

        private boolean isProvisionSupportedUrl(String url)
        {
            boolean result = false;
            for (String item : mSchemes)
            {
                if (url.startsWith(item))
                {
                    result = true;
                    break;
                }
            }
            return result;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
        {
            //cannot load the page, show error tip
            mWebView.setVisibility(View.GONE);
            mErrorView.setVisibility(View.VISIBLE);
        }
        
    }

    private void initWebView()
    {
        if (mWebView != null)
        {
            mWebView.getSettings().setJavaScriptEnabled(true);
        }
    }

}
