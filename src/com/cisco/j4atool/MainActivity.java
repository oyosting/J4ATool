package com.cisco.j4atool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

/**
 * 
  *
  * @ClassName: MainActivity
  * @Description: 
  *
  * @Author: kevin
  * @Date: 2014年7月10日
  *
 */
public class MainActivity extends SlidingFragmentActivity
{

    private Fragment mContent;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setTitle("Choose Function");

        initSlidingMenu(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(getResources().getDrawable(R.drawable.button_check_menu));
        //detect updates
        UmengUpdateAgent.update(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    /**
     * 初始化滑动菜单
     */
    private void initSlidingMenu(Bundle savedInstanceState)
    {
     // 如果保存的状态不为空则得到WebViewFragment，否则实例化WebViewFragment
        if (savedInstanceState != null)
            mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
        if (mContent == null)
        {
            Bundle bundle = new Bundle();
            mContent = new WebViewFragment();
            bundle = new Bundle();
            bundle.putString(WebViewFragment.WEB_ADDRESS, "https://www.yestops.com/xmpp.php");
            bundle.putString(WebViewFragment.ACTION_BAR_TITLE, getResources().getString(R.string.menu_sendim));
            mContent.setArguments(bundle);
        }

        // 设置主视图界面
        setContentView(R.layout.content_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mContent).commit();

        // 设置滑动菜单视图界面
        setBehindContentView(R.layout.menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new MyMenuFragment()).commit();

        // 设置滑动菜单的属性值
        //getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        getSlidingMenu().setShadowWidthRes(R.dimen.shadow_width);
        getSlidingMenu().setShadowDrawable(R.drawable.shadow);
        getSlidingMenu().setBehindOffsetRes(R.dimen.slidingmenu_offset);
        getSlidingMenu().setFadeDegree(0.35f);

    }

    /**
     * 切换Fragment，也是切换视图的内容
     */
    public void switchContent(Fragment fragment)
    {
        mContent = fragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        getSlidingMenu().showContent();
    }

    /**
     * 菜单按钮点击事件，通过点击ActionBar的Home图标按钮来打开滑动菜单
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                toggle();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 保存Fragment的状态
     */
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mContent", mContent);
    }

}
