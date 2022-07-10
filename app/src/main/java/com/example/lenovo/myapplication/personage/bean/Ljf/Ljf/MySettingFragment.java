package com.example.lenovo.myapplication.personage.bean.Ljf.Ljf;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myapplication.Denglu.DengluActivity;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.set.AboutUsFragment;
import com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.set.ChangePsdFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import cn.bmob.v3.BmobUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class MySettingFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ImageButton mImgbMsBack;
    private FragmentTransaction ft;
    private RelativeLayout mRlMsRevamp;
    private SlidingMenu slidingMenu;
    /**
     * 仅wifi下加载图片
     */
    private TextView mTextView;
    private RelativeLayout mRlMsWifi;
    private RelativeLayout mRlMsWe;
    private RelativeLayout mRlMsUpdate;
    private Switch mSwWife;
    public MySettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_setting, container, false);
        ft = getFragmentManager().beginTransaction();
        initView(view);
        ImageButton ibn = getActivity().findViewById(R.id.home_back);
        ibn.setVisibility(View.INVISIBLE);
        getActivity().findViewById(R.id.ib_menu).setVisibility(View.VISIBLE);
        TextView mTv = getActivity().findViewById(R.id.tv_title);
        mTv.setText("设置");
        return view;
    }

    private void initView(View view) {
        mSwWife = view.findViewById(R.id.sw_wife);
        mSwWife.setOnClickListener(this);
        mRlMsRevamp = (RelativeLayout) view.findViewById(R.id.rl_ms_revamp);
        mRlMsRevamp.setOnClickListener(this);
        mTextView = (TextView) view.findViewById(R.id.textView);
        mRlMsWifi = (RelativeLayout) view.findViewById(R.id.rl_ms_wifi);
        mRlMsWifi.setOnClickListener(this);
        mRlMsWe = (RelativeLayout) view.findViewById(R.id.rl_ms_we);
        mRlMsWe.setOnClickListener(this);
        mRlMsUpdate = (RelativeLayout) view.findViewById(R.id.rl_ms_update);
        mRlMsUpdate.setOnClickListener(this);
        Button but_dropout = view.findViewById(R.id.btn_dropout);
        but_dropout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_ms_revamp:
                beginTransaction.replace(R.id.fl_main, new ChangePsdFragment());
                break;
            case R.id.sw_wife:
                Toast.makeText(getActivity(), "设置成功",Toast.LENGTH_LONG).show();
                break;
            case R.id.rl_ms_we:
                beginTransaction.replace(R.id.fl_main, new AboutUsFragment());
                break;
            case R.id.rl_ms_update:
                break;
            case R.id.btn_dropout:
                BmobUser.logOut();   //清除缓存用户对象
                BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
                if (currentUser == null) {
                    startActivity(new Intent(getActivity().getApplicationContext(), DengluActivity.class));
                    getActivity().finish();
                }
                break;
        }
        beginTransaction.commit();
    }
}
