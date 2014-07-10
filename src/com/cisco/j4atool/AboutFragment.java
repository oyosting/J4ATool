/**
 *
 * Copyright 2014 Cisco. All rights reserved.
 * AboutFragment.java
 *
 */
package com.cisco.j4atool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *@author kevin
 *@date 2014Äê7ÔÂ10ÈÕ
 */
public class AboutFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_about,null);
        return rootView;
    }

}
