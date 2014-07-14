/**
 *
 * Copyright 2014 Cisco. All rights reserved.
 * AboutFragment.java
 *
 */
package com.cisco.j4atool;

import com.umeng.analytics.MobclickAgent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *@author kevin
 *@date 2014年7月10日
 */
public class AboutFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_about,null);
        return rootView;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        MobclickAgent.onPageEnd(AboutFragment.class.toString());
    }

    @Override
    public void onResume()
    {
        super.onResume();
        MobclickAgent.onPageStart(AboutFragment.class.toString());
    }

    
}
