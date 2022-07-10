package com.example.lenovo.myapplication.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.example.lenovo.myapplication.R;
import java.util.ArrayList;
import java.util.List;
import shanyao.tabpagerindictor.TabPageIndicator;

public class CenterActivity extends AppCompatActivity {

    private TabPageIndicator mIndicator;
    private ViewPager mMViewPager;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        initView();
    }

    private void initView() {
        mIndicator = (TabPageIndicator) findViewById(R.id.indicator);
        mMViewPager = (ViewPager) findViewById(R.id.mViewPager);
        AFragment aFragment = new AFragment();
        BFragment bFragment = new BFragment();
        fragmentList.add(aFragment);
        fragmentList.add(bFragment);
        BasePagerAdapter adapter = new BasePagerAdapter(getSupportFragmentManager(), fragmentList);
        mMViewPager.setAdapter(adapter);
        mIndicator.setViewPager(mMViewPager);
        setTabPagerIndicator();
    }


    private void setTabPagerIndicator() {
        mIndicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME);// 设置模式，一定要先设置模式
        mIndicator.setDividerColor(Color.parseColor("#00bbcf"));// 设置分割线的颜色
        mIndicator.setDividerPadding(15);//设置
        mIndicator.setIndicatorColor(Color.parseColor("#4CAF50"));// 设置底部导航线的颜色
        mIndicator.setTextColorSelected(Color.parseColor("#4CAF50"));// 设置tab标题选中的颜色
        mIndicator.setTextColor(Color.parseColor("#CEBABCBC"));// 设置tab标题未被选中的颜色
        mIndicator.setTextSize(60);// 设置字体大小
    }


    class BasePagerAdapter extends FragmentPagerAdapter {
        String[] titles;
        public FragmentManager fm;
        public List<Fragment> list;

        public BasePagerAdapter(FragmentManager fm) {
            super(fm);
            this.titles = CommonUtils.getStringArray(R.array.no_expand_title);
        }

        public BasePagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.fm = fm;
            this.list = list;
            this.titles = CommonUtils.getStringArray(R.array.no_expand_title);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            fragment = list.get(position);
            Bundle bundle = new Bundle();
            bundle.putString("id", "" + position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container,
                    position);
            fm.beginTransaction().show(fragment).commit();
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Fragment fragment = (Fragment) super.instantiateItem(container,
                    position);
            fm.beginTransaction().show(fragment).commit();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }
}
