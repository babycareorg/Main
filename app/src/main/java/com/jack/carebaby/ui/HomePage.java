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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.utils.UnreadMsgUtils;
import com.flyco.tablayout.widget.MsgView;
import com.jack.carebaby.R;
import com.jack.carebaby.base.BasePage;
import com.jack.carebaby.entity.TabEntity;
import com.jack.carebaby.utils.ViewFindUtils;

import java.util.ArrayList;
import java.util.Random;

import cn.bgbsk.babycare.global.Data;

public class HomePage extends BasePage {
    private Context mContext = this;



    //babybox模式和older模式切换
    private RelativeLayout babyboxs;
    private RelativeLayout olderboxs;
    private static boolean boxsStatus=true;


    /**婴儿模式*/
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



    /**老人模式*/
    private ArrayList<Fragment> mFragments_older = new ArrayList<>();
    private String[] mTitles_older = { "监控页","老人工具", "个人中心"};
    private int[] mIconUnselectIds_older = {
             R.mipmap.tab_speech_unselect,R.mipmap.tab_more_unselect,
            R.mipmap.tab_contact_unselect};
    private int[] mIconSelectIds_older = {
             R.mipmap.tab_speech_select,R.mipmap.tab_more_select,
            R.mipmap.tab_contact_select };
    private ArrayList<CustomTabEntity> mTabEntities_older = new ArrayList<>();
    private View mDecorView_older;
    private ViewPager mViewPager_older;
    private CommonTabLayout TabLayout_Home_older;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //初始化组件
        initView();

        initFragment();

        initFragmentOlder();

        /**Babyboxs页面初始化设置*/

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

        /*//设置未读消息背景
        TabLayout_Home.showMsg(3, 5);
        TabLayout_Home.setMsgMargin(3, 0, 5);
        MsgView rtv_2_3 = TabLayout_Home.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }*/


        /**Olderboxs页面设置*/

        for (int i = 0; i < mTitles_older.length; i++) {
            mTabEntities_older.add(new TabEntity(mTitles_older[i], mIconSelectIds_older[i], mIconUnselectIds_older[i]));
        }

        mDecorView_older = getWindow().getDecorView();
        mViewPager_older = ViewFindUtils.find(mDecorView_older, R.id.olderboxs_home_viewpage);
        mViewPager_older.setAdapter(new MyPagerAdapter_older(getSupportFragmentManager()));

        TabLayout_Home_older = ViewFindUtils.find(mDecorView_older, R.id.olderboxs_home_tablayout);

        Home_Select_older();
        /*//两位数
        TabLayout_Home_older.showMsg(0, 6);
        TabLayout_Home_older.setMsgMargin(0, -5, 5);

        //设置未读消息红点
        TabLayout_Home_older.showDot(1);
        MsgView rtv_2_2_older = TabLayout_Home_older.getMsgView(2);
        if (rtv_2_2_older != null) {
            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
        }*/

    }


    private void initView(){
        babyboxs=findViewById(R.id.babyboxs);
        olderboxs=findViewById(R.id.olderboxs);


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
                //模式切换
                if (!boxsStatus){
                    olderboxs.setVisibility(View.INVISIBLE);
                    babyboxs.setVisibility(View.VISIBLE);
                    boxsStatus=!boxsStatus;
                    Toast.makeText(this, "切换为婴儿看照模式", Toast.LENGTH_SHORT).show();

                }
                else{
                    olderboxs.setVisibility(View.VISIBLE);
                    babyboxs.setVisibility(View.INVISIBLE);
                    boxsStatus=!boxsStatus;
                    Toast.makeText(this, "切换为老人照顾模式", Toast.LENGTH_SHORT).show();

                }
                break;

            case R.id.notes:
                Intent intent=new Intent(HomePage.this,com.memorandum.MainActivity.class);
                startActivity(intent);
                break;

            case R.id.help:
                Intent intent2=new Intent(this,HelpAllActivity.class);
                startActivity(intent2);
                break;

            case R.id.setting:
                Intent intent3=new Intent(this,SettingActivity.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.system_title_body_4:
                if(Data.getLoginStatus()==1)
                    startActivity(new Intent("com.jack.carebaby.ui.PlanBillActivity"));
                else
                    Toast.makeText(this, "请先登录", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void initFragment(){
        mFragments.add(new DataShowFragment()); //数据单
        mFragments.add(new SystemFragment()); //生态圈
        mFragments.add(new PersonFragment());//个人中心

    }

    public void initFragmentOlder(){

        mFragments_older.add(new CameraFragmentOlder()); //生态圈
        mFragments_older.add(new ToolsFragmentOlder()); //数据单
        mFragments_older.add(new PersonFragmentOlder());//个人中心

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


    private void Home_Select_older() {
        TabLayout_Home_older.setTabData(mTabEntities_older);
        TabLayout_Home_older.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager_older.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    TabLayout_Home_older.showMsg(0, mRandom.nextInt(100) + 1);
//                    UnreadMsgUtils.show(TabLayout_Home.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });

        mViewPager_older.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabLayout_Home_older.setCurrentTab(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager_older.setCurrentItem(1);
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


    private class MyPagerAdapter_older extends FragmentPagerAdapter {
        public MyPagerAdapter_older(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments_older.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles_older[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments_older.get(position);
        }
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


}
