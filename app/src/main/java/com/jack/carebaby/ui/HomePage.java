package com.jack.carebaby.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.utils.UnreadMsgUtils;
import com.flyco.tablayout.widget.MsgView;
import com.jack.carebaby.R;
import com.jack.carebaby.base.BasePage;
import com.jack.carebaby.entity.TabEntity;
import com.jack.carebaby.service.FloatWindowServer;
import com.jack.carebaby.utils.ViewFindUtils;

import java.util.ArrayList;
import java.util.Random;

public class HomePage extends BasePage {
    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    private String[] mTitles = { "数据单","生态圈", "个人中心"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_more_unselect, R.mipmap.tab_home_unselect,/*R.mipmap.tab_speech_unselect,*/
            R.mipmap.tab_contact_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_more_select, R.mipmap.tab_home_select, /*R.mipmap.tab_speech_select,*/
            R.mipmap.tab_contact_select };
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private View mDecorView;
    private ViewPager mViewPager;
    private CommonTabLayout TabLayout_Home;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initFragment();


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mDecorView = getWindow().getDecorView();
        mViewPager = ViewFindUtils.find(mDecorView, R.id.home_viewpage);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        TabLayout_Home = ViewFindUtils.find(mDecorView, R.id.home_tablayout);

        Home_Select();
        //两位数
        TabLayout_Home.showMsg(0, 6);
        TabLayout_Home.setMsgMargin(0, -5, 5);

        //设置未读消息红点
        TabLayout_Home.showDot(1);
        MsgView rtv_2_2 = TabLayout_Home.getMsgView(2);
        if (rtv_2_2 != null) {
            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
        }

        //设置未读消息背景
        /*TabLayout_Home.showMsg(3, 5);
        TabLayout_Home.setMsgMargin(3, 0, 5);
        MsgView rtv_2_3 = TabLayout_Home.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }*/
    }

    //actionbar添加
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //监听菜单点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.actionbar:
                /*Intent intent1=new Intent(HomePage.this,System.class);
                startActivity(intent1);*/
                break;

            case R.id.notes:

                Intent intent=new Intent(HomePage.this,com.memorandum.MainActivity.class);
                startActivity(intent);
                break;

            case R.id.help:
                Intent intent2=new Intent(this,FloatWindowServer.class);
                startService(intent2);
                break;

            case R.id.more:
                /*Intent intent3=new Intent(MainActivity.this,NoticeActivity.class);
                startActivity(intent3);*/
                Intent intent3=new Intent(this,FloatWindowServer.class);
                stopService(intent3);


                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void initFragment(){
        mFragments.add(new DataShowFragment()); //数据单
        mFragments.add(new SystemFragment()); //生态圈
        mFragments.add(new PersonFragment());//个人中心

    }

    Random mRandom = new Random();

    private void Home_Select() {
        TabLayout_Home.setTabData(mTabEntities);
        TabLayout_Home.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    TabLayout_Home.showMsg(0, mRandom.nextInt(100) + 1);
//                    UnreadMsgUtils.show(TabLayout_Home.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabLayout_Home.setCurrentTab(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(1);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
