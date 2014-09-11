package com.cisco.j4atool;

import java.util.ArrayList;
import java.util.List;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
  *
  * @ClassName: MyMenuFragment
  * @Description: 
  *
  * @Author: kevin
  * @Date: 2014年7月10日
  *
 */
public class MyMenuFragment extends ListFragment implements OnClickListener
{
    private TextView mFeedBackTextView;

    private TextView mAboutTextView;
    
    private MainActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.list, null);
        mappComponents(rootView);
        initListeners();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        mActivity=(MainActivity)activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<MyMenuItem> dataAdapter = new MyDataAdapter<MyMenuItem>(getActivity(), getMenuItems());

        setListAdapter(dataAdapter);
    }

    @Override
    public void onListItemClick(ListView lv, View v, int position, long id)
    {
        Fragment newContent = null;
        Bundle bundle = null;
        switch (position)
        {
            case 0:
                //Send IM
                newContent = new WebViewFragment();
                bundle = new Bundle();
                bundle.putString(WebViewFragment.WEB_ADDRESS, "https://www.yestops.com/xmpp.php");
                bundle.putString(WebViewFragment.ACTION_BAR_TITLE, getResources().getString(R.string.menu_sendim));
                newContent.setArguments(bundle);
                //设置SlidingMenu拖拽模式
                mActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case 1:
                //CrossLaunch
                newContent = new WebViewFragment();
                bundle = new Bundle();
                bundle.putString(WebViewFragment.WEB_ADDRESS, "https://www.yestops.com/crosslaunch.php");
                bundle.putString(WebViewFragment.ACTION_BAR_TITLE, getResources().getString(R.string.menu_crosslaunch));
                newContent.setArguments(bundle);
                //设置SlidingMenu拖拽模式
                mActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case 2:
                //Make P2P Call
                newContent = new WebViewFragment();
                bundle = new Bundle();
                bundle.putString(WebViewFragment.WEB_ADDRESS, "http://10.140.80.88/p2p.php");
                bundle.putString(WebViewFragment.ACTION_BAR_TITLE, getResources().getString(R.string.menu_p2p));
                newContent.setArguments(bundle);
                //设置SlidingMenu拖拽模式
                mActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case 3:
                //Daily Builds
                newContent = new WebViewFragment();
                bundle = new Bundle();
                bundle.putString(WebViewFragment.WEB_ADDRESS, "http://crdc-android-bld1.cisco.com/builds");
                bundle.putString(WebViewFragment.ACTION_BAR_TITLE, getResources().getString(R.string.menu_jabberdownload));
                newContent.setArguments(bundle);
                //设置SlidingMenu拖拽模式
                mActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                break;
            case 4:
                //IQA System
                newContent = new WebViewFragment();
                bundle = new Bundle();
                bundle.putString(WebViewFragment.WEB_ADDRESS, "http://cmbu.cisco.com/");
                bundle.putString(WebViewFragment.ACTION_BAR_TITLE, getResources().getString(R.string.menu_iqa));
                newContent.setArguments(bundle);
                //设置SlidingMenu拖拽模式
                mActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case 5:
                //PRT System
                newContent = new WebViewFragment();
                bundle = new Bundle();
                bundle.putString(WebViewFragment.WEB_ADDRESS, "http://cmbu.cisco.com/qd/prt.php");
                bundle.putString(WebViewFragment.ACTION_BAR_TITLE, getResources().getString(R.string.menu_prt));
                newContent.setArguments(bundle);
                //设置SlidingMenu拖拽模式
                mActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                break;
            case 6:
                //Automation
                //设置SlidingMenu拖拽模式
                newContent = new WebViewFragment();
                bundle = new Bundle();
                bundle.putString(WebViewFragment.WEB_ADDRESS, "https://apprtc.appspot.com/");
                bundle.putString(WebViewFragment.ACTION_BAR_TITLE, getResources().getString(R.string.menu_automation));
                newContent.setArguments(bundle);
                mActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                break;
        }
        if (newContent != null)
            switchFragment(newContent);
    }

    
    private void switchFragment(Fragment fragment)
    {
        if (getActivity() == null)
            return;

        if (getActivity() instanceof MainActivity)
        {
            MainActivity fca = (MainActivity) getActivity();
            fca.switchContent(fragment);
        }
    }

    /** initiate menu data */
    private List<MyMenuItem> getMenuItems()
    {
        List<MyMenuItem> items = new ArrayList<MyMenuItem>();
        // No.0
        MyMenuItem sendIM = new MyMenuItem();
        sendIM.setIcon(getResources().getDrawable(R.drawable.menu_sendim));
        sendIM.setName(getResources().getString(R.string.menu_sendim));
        items.add(sendIM);
        // No.1
        MyMenuItem crossLaunch = new MyMenuItem();
        crossLaunch.setIcon(getResources().getDrawable(R.drawable.menu_crosslaunch));
        crossLaunch.setName(getResources().getString(R.string.menu_crosslaunch));
        items.add(crossLaunch);
        // No.2
        MyMenuItem p2pCall = new MyMenuItem();
        p2pCall.setIcon(getResources().getDrawable(R.drawable.menu_p2p));
        p2pCall.setName(getResources().getString(R.string.menu_p2p));
        items.add(p2pCall);
        // No.3
        MyMenuItem jabberDownload = new MyMenuItem();
        jabberDownload.setIcon(getResources().getDrawable(R.drawable.menu_jabberdownload));
        jabberDownload.setName(getResources().getString(R.string.menu_jabberdownload));
        items.add(jabberDownload);
        // No.4
        MyMenuItem iqa = new MyMenuItem();
        iqa.setIcon(getResources().getDrawable(R.drawable.menu_iqa));
        iqa.setName(getResources().getString(R.string.menu_iqa));
        items.add(iqa);
        // No.5
        MyMenuItem prt = new MyMenuItem();
        prt.setIcon(getResources().getDrawable(R.drawable.menu_prt));
        prt.setName(getResources().getString(R.string.menu_prt));
        items.add(prt);
        // No.6
        MyMenuItem worldcup = new MyMenuItem();
        worldcup.setIcon(getResources().getDrawable(R.drawable.menu_worldcup));
        worldcup.setName(getResources().getString(R.string.menu_automation));
        items.add(worldcup);
        return items;
    }

    private void mappComponents(View rootView)
    {
        mFeedBackTextView = (TextView) rootView.findViewById(R.id.button_feedback);
        mAboutTextView = (TextView) rootView.findViewById(R.id.button_about);
    }

    private void initListeners()
    {
        mFeedBackTextView.setOnClickListener(this);
        mAboutTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.button_about:
                Fragment aboutFragment = new AboutFragment();
                switchFragment(aboutFragment);
                Log.d("Cisco", "Switch to AboutFragment");
                break;
            case R.id.button_feedback:
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"jabber-android-tiger@cisco.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback For J4ATool");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Enter your feedback below:\n");
                emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(emailIntent, "Send Email"));
                break;
            default:
                break;
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        MobclickAgent.onPageEnd(MyMenuFragment.class.toString());
    }

    @Override
    public void onResume()
    {
        super.onResume();
        MobclickAgent.onPageStart(MyMenuFragment.class.toString());
    }

    private class MyDataAdapter<MenuItem> extends ArrayAdapter<MenuItem>
    {
        private LayoutInflater mInflater;

        private List<MenuItem> mItems;

        public MyDataAdapter(Context context, List<MenuItem> objects)
        {
            super(context, 0, objects);
            mInflater = LayoutInflater.from(context);
            mItems = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder;
            if (convertView == null)
            {
                convertView = mInflater.inflate(R.layout.row, null);
                holder = new ViewHolder();
                holder.iconView = (ImageView) convertView.findViewById(R.id.row_icon);
                holder.nameView = (TextView) convertView.findViewById(R.id.row_title);
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) convertView.getTag();
            }

            MyMenuItem myItem = (MyMenuItem) mItems.get(position);
            holder.iconView.setImageDrawable(myItem.getIcon());
            holder.nameView.setText(myItem.getName());
            return convertView;
        }

    }

    private static class ViewHolder
    {
        public ImageView iconView;

        public TextView nameView;
    }

}
