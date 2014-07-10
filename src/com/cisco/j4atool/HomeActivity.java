/**
 *
 * Copyright 2014 Cisco. All rights reserved.
 * HomeActivity.java
 *
 */
package com.cisco.j4atool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

/**
 *@author kevin
 *@date 2014Äê7ÔÂ10ÈÕ
 *First activity shown to user when user open the app
 */
public class HomeActivity extends Activity
{
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        View view = View.inflate(this, R.layout.activity_first, null);
        setContentView(view);
        getActionBar().hide();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        view.startAnimation(animation);
        animation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goHome();
                    }
                }, 500);
            }
        });
//        UmengUpdateAgent.setUpdateOnlyWifi(false);
//        UmengUpdateAgent.update(this);
    }

    private void goHome() {
        startActivity(new Intent(HomeActivity.this,MainActivity.class));
        finish();
    }
    
}
