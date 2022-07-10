package com.example.lenovo.myapplication.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myapplication.ShareActivity.Gongxiang_Activity;
import com.example.lenovo.myapplication.NewsActivity.A_Activity;
import com.example.lenovo.myapplication.NewsActivity.B_Activity;
import com.example.lenovo.myapplication.NewsActivity.C_Activity;
import com.example.lenovo.myapplication.JobActivity.Jianzhi2_Activity;
import com.example.lenovo.myapplication.JobActivity.Jianzhi3_Activity;
import com.example.lenovo.myapplication.Bean.ShanYaoApplication;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.JobActivity.Jianzhi1_Activity;
import com.example.lenovo.myapplication.Bean.ViewPagerHelper;
import com.example.lenovo.myapplication.Bean.XCRoundImageView;
import com.example.lenovo.myapplication.View.CenterActivity;
import com.example.lenovo.myapplication.Bean.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/18.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    private String[] headtext = {"课程笔记", "帮拿快递", "寻物启示", "游戏资源", "体育用品", "电影资源", "其他资源"};
    private static final String TAG = "HomeFragment";
    private MyViewPager mViewPager;
    private ImageView mImageView;
    private LinearLayout mLlOne;
    private LinearLayout mLlTwo;
    private LinearLayout mRelativeLayout;
    private XCRoundImageView mImgCenter;
    private View[] fragments;
    private TextView ll_text1;
    private TextView ll_text2;
    private TextView ll_text3;
    private View view2;
    private View view1;
    private View view3;
    private ViewPager vp_lunbo;
    private LinearLayout ll_lunbo;
    private ImageView selector;
    private View ll_jianzhi1;
    private View ll_jianzhi2;
    private View ll_jianzhi3;
    private int TYPE = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        initView(view);
        initview1();
        initview2();
        initview3();
        fragments = new View[]{view1, view2, view3};
        mViewPager.setAdapter(new MyAdapter());
        selectorPosition();
        return view;
    }


    private void initview1() {
        view1 = View.inflate(ShanYaoApplication.getContext(), R.layout.view_share1, null);
        ListView list_view = view1.findViewById(R.id.list_aaa);
        list_view.setAdapter(new ListADapter());
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Gongxiang_Activity.class);
                intent.putExtra("position", position);
                intent.putExtra("type", headtext[position]);
                startActivity(intent);

            }
        });
    }


    private void initview2() {
        view2 = View.inflate(ShanYaoApplication.getContext(), R.layout.view_share2, null);
        ll_jianzhi1 = view2.findViewById(R.id.ll_jianzhi1);
        ll_jianzhi3 = view2.findViewById(R.id.ll_jianzhi3);
        ll_jianzhi2 = view2.findViewById(R.id.ll_jianzhi2);
        ll_jianzhi1.setOnClickListener(this);
        ll_jianzhi2.setOnClickListener(this);
        ll_jianzhi3.setOnClickListener(this);
    }

    private void initview3() {
        view3 = View.inflate(ShanYaoApplication.getContext(), R.layout.view_share3, null);
        View ll_share3_1 = view3.findViewById(R.id.ll_share3_1);
        ll_share3_1.setOnClickListener(this);
        View ll_share3_2 = view3.findViewById(R.id.ll_share3_2);
        ll_share3_2.setOnClickListener(this);
        View ll_share3_3 = view3.findViewById(R.id.ll_share3_3);
        ll_share3_3.setOnClickListener(this);
    }


    private int lastPosition = 0; // 记录ViewPger上一个界面的position信息
    private int sWidth;//屏幕的宽度

    //设置选择器图片的初始位置
    private void selectorPosition() {
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        sWidth = d.getWidth();
        //计算出哪个选择器图片的真实宽度，不是ImageView的宽度
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.changfangti);
        int bWidth = bitmap.getWidth();
        int offset = ((sWidth / 3) - bWidth) / 2; //求出初始位置
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        selector.setImageMatrix(matrix);//设置图片的初始位置
    }


    private void initView(View view) {
        ll_text1 = view.findViewById(R.id.ll_text1);
        ll_text2 = view.findViewById(R.id.ll_text2);
        ll_text3 = view.findViewById(R.id.ll_text3);
        ll_text1.setOnClickListener(this);
        ll_text2.setOnClickListener(this);
        ll_text3.setOnClickListener(this);
        selector = view.findViewById(R.id.ii_category_selector);
        ll_lunbo = view.findViewById(R.id.ll_lunbo);
        vp_lunbo = view.findViewById(R.id.vp_lunbo);
        mViewPager = view.findViewById(R.id.viewPager);
        mViewPager.setOnClickListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        // fromXDelta toXDelta:相对于图片初始位置需要增加的量
                        lastPosition = position;//记录出当前显示的page
                        TranslateAnimation animation = new TranslateAnimation(lastPosition * sWidth / 3, position * sWidth / 3, 0, 0);
                        animation.setDuration(1200);
                        animation.setFillAfter(true);// 移动完后停留到终点
                        selector.startAnimation(animation);
                        //page切换后，更改标题文字的色彩 1：还原所有，2：根据当前设置
                        ll_text1.setTextColor(Color.BLACK);
                        ll_text2.setTextColor(Color.BLACK);
                        ll_text3.setTextColor(Color.BLACK);
                        switch (position) {
                            case 0:
                                ll_text1.setTextColor(Color.RED);
                                break;
                            case 1:
                                ll_text2.setTextColor(Color.RED);
                                break;
                            case 2:
                                ll_text3.setTextColor(Color.RED);
                                break;
                        }
                    }

                    @Override
                    public void onPageSelected(int position) {
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });
            }
        }).start();
        mImageView = view.findViewById(R.id.imageView);
        mImageView.setOnClickListener(this);
        mLlOne = view.findViewById(R.id.ll_one);
        mLlOne.setOnClickListener(this);
        mLlTwo = view.findViewById(R.id.ll_two);
        mLlTwo.setOnClickListener(this);
        mRelativeLayout = view.findViewById(R.id.relativeLayout);
        mRelativeLayout.setOnClickListener(this);
        mImgCenter = view.findViewById(R.id.img_center);
        mImgCenter.setOnClickListener(this);
        List<View> viewList = new ArrayList<>();
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.lunbo_one, null);
        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.lunbo_two, null);
        View view3 = LayoutInflater.from(getActivity()).inflate(R.layout.lunbo_three, null);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        ViewPagerHelper vp = new ViewPagerHelper(true, vp_lunbo, viewList, ll_lunbo, R.drawable.lunbo_true, R.drawable.lunbo_no);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewPager:
                break;
            case R.id.imageView:
                break;
            case R.id.ll_one:
                break;
            case R.id.ll_two:
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.fl_main, new HelpFragment()).commit();
                break;
            case R.id.relativeLayout:
                break;
            case R.id.img_center:
                Intent intent = new Intent(getActivity(), CenterActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_text1:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.ll_text2:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.ll_text3:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.ll_share3_1:
                startActivity(new Intent(getActivity(), A_Activity.class));
                break;
            case R.id.ll_share3_2:
                startActivity(new Intent(getActivity(), B_Activity.class));
                break;
            case R.id.ll_share3_3:
                startActivity(new Intent(getActivity(), C_Activity.class));
                break;
            case R.id.ll_jianzhi1:
                startActivity(new Intent(getActivity(), Jianzhi1_Activity.class));
                break;
            case R.id.ll_jianzhi2:
                startActivity(new Intent(getActivity(), Jianzhi2_Activity.class));
                break;
            case R.id.ll_jianzhi3:
                startActivity(new Intent(getActivity(), Jianzhi3_Activity.class));
                break;
        }


    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = fragments[position];
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    class ListADapter extends BaseAdapter {

        @Override
        public int getCount() {
            return headtext.length;
        }

        @Override
        public Object getItem(int position) {
            return headtext[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View viewa = View.inflate(getActivity(), R.layout.fragment_home_aa, null);
            TextView viewById = viewa.findViewById(R.id.tv_type1);
            viewById.setText(headtext[position]);
            return viewa;
        }
    }

}
