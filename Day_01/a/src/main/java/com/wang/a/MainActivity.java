package com.wang.a;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.wang.a.fragment.BsFragment;
import com.wang.a.fragment.QzFragment;
import com.wang.a.fragment.SjFragment;
import com.wang.a.fragment.SyFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTabTv;
    private Toolbar mToolbar;
    private ViewPager mVp;
    private TabLayout mTab;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mTabTv = (TextView) findViewById(R.id.tab_tv);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mVp = (ViewPager) findViewById(R.id.vp);
        mTab = (TabLayout) findViewById(R.id.tab);
        fragments = new ArrayList<>();
        fragments.add(new SyFragment());
        fragments.add(new BsFragment());
        fragments.add(new QzFragment());
        fragments.add(new SjFragment());
        mTab.addTab(mTab.newTab().setIcon(R.drawable.select1).setText("首页"));
        mTab.addTab(mTab.newTab().setIcon(R.drawable.select2).setText("比赛"));
        mTab.addTab(mTab.newTab().setIcon(R.drawable.select3).setText("圈子"));
        mTab.addTab(mTab.newTab().setIcon(R.drawable.select4).setText("数据"));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                     @Override
                     public void onTabSelected(TabLayout.Tab tab) {
                         mVp.setCurrentItem(tab.getPosition());
                         mTabTv.setText(tab.getText());
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
                //mTab.setupWithViewPager(mVp);
    }
}
