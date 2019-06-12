package com.wang.day03_lx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wang.day03_lx.fragment.AttentFragment;
import com.wang.day03_lx.fragment.SchoolBlank;

import java.util.ArrayList;

public class SchoolActivity extends AppCompatActivity {

    private TabLayout mTab;
    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        initView();
    }

    private void initView() {
        mTab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
        final ArrayList<String> title = new ArrayList<>();
        final ArrayList<Fragment> fragments = new ArrayList<>();
        title.add("校门");
        title.add("关注");
        fragments.add(new SchoolBlank());
        fragments.add(new AttentFragment());
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title.get(position);
            }
        };
        mVp.setAdapter(adapter);
        mTab.setupWithViewPager(mVp);
    }
}
