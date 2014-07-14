/**
 *
 * Copyright 2014 Cisco. All rights reserved.
 * HomeActivity.java
 *
 */
package com.cisco.j4atool;

import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

/**
 * @author kevin
 * @date 2014年7月10日 First activity shown to user when user open the app
 */
public class HomeActivity extends Activity
{
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        View view = View.inflate(this, R.layout.activity_first, null);
        setContentView(view);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        view.startAnimation(animation);
        animation.setAnimationListener(new AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation arg0)
            {
            }

            @Override
            public void onAnimationRepeat(Animation arg0)
            {
            }

            @Override
            public void onAnimationEnd(Animation arg0)
            {
                mHandler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        goHome();
                    }
                }, 500);
            }
        });
     // 禁止Umeng默认的页面统计方式--将Activity类名作为页面名称进行统计
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.updateOnlineConfig(this);
        MobclickAgent.setDebugMode(true);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        MobclickAgent.onPageStart(HomeActivity.class.toString());
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        MobclickAgent.onPageEnd(HomeActivity.class.toString());
        MobclickAgent.onPause(this);
    }

    private void goHome()
    {
        startActivity(new Intent(HomeActivity.this, MainActivity.class));
        finish();
    }

}
