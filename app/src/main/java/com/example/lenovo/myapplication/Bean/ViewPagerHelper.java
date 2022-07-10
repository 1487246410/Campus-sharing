package com.example.lenovo.myapplication.Bean;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/12/19.
 */

public class ViewPagerHelper {
    // 判断是否自动滑动
    private boolean mIsAuto;

    private ViewPager mViewPager;

    private List<View> mViews;

    private LinearLayout mIndicatorParents; // 指示器容器

    private PagerAdapter mPagerAdapter;

    private int mSelect;

    private int mUnSelect;

    private OnViewInstantiateListener mOnViewInstantiateListener;

    // 一个提供原子操作的Integer的类
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    // 广告不停的循环播放
    private boolean isContinue = true;

    /*
     * 每隔固定时间切换广告栏图片
     */
    private final Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == mViews.size()) {
                mViewPager.setCurrentItem(1);
            } else {
                mViewPager.setCurrentItem(msg.what);
            }

            super.handleMessage(msg);
        }

    };

    /**
     * View 被附加到viewpager的时候调用
     */
    public interface OnViewInstantiateListener {
        public void onInstantiate(int position, View view);
    }

    /**
     * @param isAuto              指定该ViewPager中的内容是否自动滑动，true为自动滑动，false为手动滑动
     * @param viewPager
     * @param views               加载到ViewPager中的View的集合
     * @param indicatorParents    导航页下面小圆点的父容器
     * @param selectDrawableRes   选中页显示的导航点的图片
     * @param unselectDrawableRes 未选中页的导航点的图片
     */
    public ViewPagerHelper(boolean isAuto, ViewPager viewPager, List<View> views, LinearLayout indicatorParents,
                           int selectDrawableRes, int unselectDrawableRes) {
        mIsAuto = isAuto;
        mViewPager = viewPager;
        mViews = views;
        mIndicatorParents = indicatorParents;
        mSelect = selectDrawableRes;
        mUnSelect = unselectDrawableRes;

        init();
    }

    /**
     * 设置atomicInteger
     */
    private void atomicOption() {
        atomicInteger.incrementAndGet();
        if (atomicInteger.get() > mViews.size() - 1) {
            atomicInteger.getAndAdd(-mViews.size());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public List<View> getViews() {
        return mViews;
    }

    public void setOnViewInstantiateListener(OnViewInstantiateListener listener) {
        mOnViewInstantiateListener = listener;
    }

    /**
     *
     */
    private void init() {
        mPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == (arg1);
            }

            @Override
            public int getItemPosition(Object object) {
                View view = (View) object;
                if (mViews.contains(view)) {
                    return mViews.indexOf(view);
                }

                return POSITION_NONE;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));// 删除页卡
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view, 0);

                if (mOnViewInstantiateListener != null) {
                    mOnViewInstantiateListener.onInstantiate(position, view);
                }

                return mViews.get(position);
            }
        };

        mViewPager.setAdapter(mPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 从1到2滑动，在1滑动前调用
            }

            @Override
            public void onPageSelected(int position) {
                // activity从1到2滑动，2被加载后掉用此方法
                switchBannerIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 状态有三个0空闲，1是正在滑行中，2目标加载完毕
            }
        });

        if (mIndicatorParents != null) {
            for (int i = 0; i < mViews.size(); i++) {
                // 导航点直接的间距
                int margin = 20;

                // 设置未选中的也没的导航点的图片
                ImageView view = new ImageView(mViewPager.getContext());
                view.setBackgroundResource(mUnSelect);

                // 设置图片的属性
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                if (i < mViews.size() - 1) {
                    params.rightMargin = margin;
                }

                // 将导航点加入到容器中
                mIndicatorParents.addView(view, params);
            }
            // 单独设置选中的导航点的图片
            mIndicatorParents.getChildAt(0).setBackgroundResource(mSelect);
        }

        /**
         * 判断是否自动更新View
         */
        if (mIsAuto) {
            // 启动线程，定时更改View
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (isContinue) {
                            viewHandler.sendEmptyMessage(atomicInteger.get());
                            atomicOption();
                        }
                    }
                }
            }).start();
        }

    }

    /**
     * 设置指示器当前页码
     */
    private void switchBannerIndicator(int index) {
        if (mIndicatorParents != null) {
            for (int i = 0; i < mIndicatorParents.getChildCount(); i++) {
                View view = mIndicatorParents.getChildAt(i);
                if (i == index) {
                    view.setBackgroundResource(mSelect);
                } else {
                    view.setBackgroundResource(mUnSelect);
                }
            }
        }
    }
}
