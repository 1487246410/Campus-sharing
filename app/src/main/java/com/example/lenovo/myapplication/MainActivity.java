package com.example.lenovo.myapplication;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myapplication.Fragment.HomeFragment;
import com.example.lenovo.myapplication.Bean.MenuBean;
import com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.MyCollectFragment;
import com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.MyInformationFragment;
import com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.MySettingFragment;
import com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.MyTrendsFragment;
import com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.MymessageFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "MAINACTIVITY";
    //声明Slidemenu对象
    private SlidingMenu slidingMenu;
    private LinearLayout ll_edit;
    private TextView tv_title;
    private ArrayList<MenuBean> list;
    private FragmentTransaction beginTransaction;
    private Fragment[] fragments;
    private String[] name;
    private long oldTime;
    private android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout rl_top = findViewById(R.id.rl_top);
        ImageButton ib_menu = findViewById(R.id.ib_menu);
        ib_menu.setOnClickListener(this);
        ll_edit = findViewById(R.id.editText);
        tv_title = findViewById(R.id.tv_title);
        //替换主界面内容
        select(0);
        //实例化菜单控件
        slidingMenu = new SlidingMenu(this);
        //设置相关属性
        slidingMenu.setMode(SlidingMenu.LEFT);//菜单靠左
//        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏支持触摸拖拉
        slidingMenu.setBehindOffset(300);//设置菜单大小
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);//不包含ActionBar


        View view = View.inflate(getApplicationContext(), R.layout.leftmenu, null);
        ListView lv_menu = view.findViewById(R.id.lv_menu);
        TextView user_tv = view.findViewById(R.id.user_tv);
        ImageView user_image = view.findViewById(R.id.user_image);
//        user_tv.setText();
//        user_image.setImageBitmap();
        //存储侧滑菜单的信息
        list = new ArrayList<MenuBean>();
        int[] image = new int[]{R.drawable.zhuye, R.drawable.gerenxinxi, R.drawable.wodedongtia, R.drawable.xiaoxi, R.drawable.shoucang, R.drawable.shezhi};
        name = new String[]{"主界面", "个人信息", "我的动态", "我的消息", "我的收藏", "设置"};
        for (int i = 0; i < image.length; i++) {
            MenuBean menuBean = new MenuBean();
            menuBean.setImage(image[i]);
            menuBean.setName(name[i]);
            list.add(menuBean);
        }
        lv_menu.setAdapter(new MyAdapter());
        slidingMenu.setMenu(view);
        //设置监听事件，完成点击操作
        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        select(0);
                        break;
                    case 1:
                        select(1);
                        break;
                    case 2:
                        select(2);
                        break;
                    case 3:
                        select(3);
                        break;
                    case 4:
                        select(4);
                        break;
                    case 5:
                        select(5);
                        break;
                }
                slidingMenu.toggle();

            }
        });
        //替换掉菜单内容
    }

    private void select(int pos) {
        fragmentManager = getSupportFragmentManager();
        beginTransaction = fragmentManager.beginTransaction();
        initFragment(pos);
        if (pos > 0) {
            ll_edit.setVisibility(View.GONE);
            tv_title.setVisibility(View.VISIBLE);
            tv_title.setText(name[pos]);
            beginTransaction.addToBackStack("");
        }
        beginTransaction.commit();

    }


    private void initFragment(int pos) {
        if (fragments == null) {
            fragments = new Fragment[]{new HomeFragment(), new MymessageFragment(), new MyTrendsFragment(),
                    new MyInformationFragment(), new MyCollectFragment(), new MySettingFragment()};
        }
        beginTransaction.replace(R.id.fl_main, fragments[pos]);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_menu:
                select(0);
                ll_edit.setVisibility(View.VISIBLE);
                tv_title.setVisibility(View.GONE);
                slidingMenu.toggle();
//                if (i%2==0){
//                    slidingMenu.toggle();
//                    i++;
//                }else {
//                    select(0);
//                    i++;
//                }
                break;
        }
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public MenuBean getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (view == null) {
                view = View.inflate(getApplicationContext(), R.layout.menu_item, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            MenuBean item = getItem(i);
            Log.i(TAG, "getView: " + item.getImage());
            holder.mIvMenu.setImageResource(item.getImage());
            holder.mTvName.setText(item.getName());

            return view;
        }
    }

    static class ViewHolder {
        View view;
        ImageView mIvMenu;
        TextView mTvName;
        LinearLayout mRlItem;

        ViewHolder(View view) {
            this.view = view;
            this.mIvMenu = view.findViewById(R.id.iv_menu);
            this.mTvName = view.findViewById(R.id.tv_name);
            this.mRlItem = view.findViewById(R.id.rl_item);
        }
    }

    private long mExitTime;

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }


}
