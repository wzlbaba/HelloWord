package com.wang.day_01;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wang.day_01.fragment.HomeFragment;
import com.wang.day_01.fragment.ScFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity{

    private TextView mTooTv;
    private Toolbar mToolbar;
    private ViewPager mVp;
    private TabLayout mTab;
    private ArrayList<Fragment> fragments;
    private NavigationView mNav;
    private DrawerLayout mDr;
    private long countlengt;
    private long count;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EventBus.getDefault().register(this);
        initView();
        initData();
        initTan();
    }

    private void initTan() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.item3, null);
        PopupWindow popupWindow = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        btn = inflate.findViewById(R.id.dow_btn);
//        popupWindow.showAsDropDown(mDr);
        final ProgressBar pb = inflate.findViewById(R.id.dow_pb);
        final TextView tv = inflate.findViewById(R.id.dow_tv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* pb.setMax((int) count);
                pb.setProgress((int) countlengt);
                tv.setText(100*count/countlengt+"%");*/
            }
        });
    }

    private void initData() {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_tv:
                         NotificationManager systemService = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                                 Notification build = new NotificationCompat.Builder(HomeActivity.this)
                                         .setContentText("通知的内容")
                                         .setContentTitle("奇妙的标题")
                                         .setSmallIcon(R.mipmap.ic_launcher)
                                         .build();
                                 systemService.notify(1, build);

                        break;
                    case R.id.menu_tv2:
                        Intent intent = new Intent(HomeActivity.this, MyReceiver.class);
                        intent.putExtra("name1","机器人");
                        intent.getStringExtra("name1");
                        sendBroadcast(intent);
                        break;
                }
                return false;
            }
        });
    }

    private void initView() {

        mTooTv = (TextView) findViewById(R.id.too_tv);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mVp = (ViewPager) findViewById(R.id.vp);
        mTab = (TabLayout) findViewById(R.id.tab);
        mNav = (NavigationView) findViewById(R.id.nav);
        mDr = (DrawerLayout) findViewById(R.id.dr);
        mTab.addTab(mTab.newTab().setText("首页"));
        mTab.addTab(mTab.newTab().setText("收藏"));
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDr, mToolbar, R.string.app_name, R.string.app_name);
        toggle.syncState();
        mDr.addDrawerListener(toggle);
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ScFragment());
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
                mTooTv.setText(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
        Vp_TabAdapter vp_tabAdapter = new Vp_TabAdapter(getSupportFragmentManager(), fragments);
        mVp.setAdapter(vp_tabAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"上传");
        menu.add(0,2,0,"下载");
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:

                break;
            case 2:
                Intent intent = new Intent(HomeActivity.this,DowLoadService.class);
                startService(intent);

                break;
        }

        return super.onOptionsItemSelected(item);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(EnBean enBean){
        count = enBean.getCount();
        countlengt = enBean.getCountlengt();
    }
    @Override
    protected void onDestroy() {
        Intent intent = new Intent(HomeActivity.this,DowLoadService.class);
        stopService(intent);
        EventBus.getDefault().unregister(intent);
        super.onDestroy();
    }

}
