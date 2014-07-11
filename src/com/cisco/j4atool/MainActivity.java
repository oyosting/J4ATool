package com.cisco.j4atool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.umeng.analytics.MobclickAgent;

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
     * ��ʼ�������˵�
     */
    private void initSlidingMenu(Bundle savedInstanceState)
    {
        // ��������״̬��Ϊ����õ�WebViewFragment������ʵ����WebViewFragment
        if (savedInstanceState != null)
            mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
        if (mContent == null)
        {
            Bundle bundle = new Bundle();
            mContent = new WebViewFragment();
            bundle = new Bundle();
            bundle.putString(WebViewFragment.WEB_ADDRESS, "http://www.yestops.com/xmpp.php");
            bundle.putString(WebViewFragment.ACTION_BAR_TITLE, getResources().getString(R.string.menu_sendim));
            mContent.setArguments(bundle);
        }

        // ��������ͼ����
        setContentView(R.layout.content_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mContent).commit();

        // ���û����˵���ͼ����
        setBehindContentView(R.layout.menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new MyMenuFragment()).commit();

        // ���û����˵�������ֵ
        //getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        getSlidingMenu().setShadowWidthRes(R.dimen.shadow_width);
        getSlidingMenu().setShadowDrawable(R.drawable.shadow);
        getSlidingMenu().setBehindOffsetRes(R.dimen.slidingmenu_offset);
        getSlidingMenu().setFadeDegree(0.35f);

    }

    /**
     * �л�Fragment��Ҳ���л���ͼ������
     */
    public void switchContent(Fragment fragment)
    {
        mContent = fragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        getSlidingMenu().showContent();
    }

    /**
     * �˵���ť����¼���ͨ�����ActionBar��Homeͼ�갴ť���򿪻����˵�
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
     * ����Fragment��״̬
     */
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mContent", mContent);
    }

}
