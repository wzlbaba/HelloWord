package com.wang.b.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.wang.b.R;
import com.wang.b.adapter.FragmentAdapter;
import com.wang.b.fragment.HomeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mVp;
    private TabLayout mTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mVp = (ViewPager) findViewById(R.id.vp);
        mTab = (TabLayout) findViewById(R.id.tab);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        mTab.addTab(mTab.newTab().setIcon(R.drawable.select1).setText("首页"));
        mTab.addTab(mTab.newTab().setIcon(R.drawable.select2).setText("比赛"));
        mTab.addTab(mTab.newTab().setIcon(R.drawable.select3).setText("圈子"));
        mTab.addTab(mTab.newTab().setIcon(R.drawable.select4).setText("数据"));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        mVp.setAdapter(fragmentAdapter);
    }
}
