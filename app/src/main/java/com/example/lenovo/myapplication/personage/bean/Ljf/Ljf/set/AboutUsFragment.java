package com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.set;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.MySettingFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ImageButton mImgbMaBack;
    private ImageView mImgMaImage;
    private RelativeLayout mRlMaUpdata;
    private RelativeLayout mRlMaFeedback;
    private FragmentTransaction ft;
    private SlidingMenu slidingMenu;

    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        ft = getFragmentManager().beginTransaction();
        initView(view);
        ImageButton ibn = getActivity().findViewById(R.id.home_back);
        ibn.setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.ib_menu).setVisibility(View.INVISIBLE);
        ibn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.replace(R.id.fl_main, new MySettingFragment()).commit();
            }
        });
        TextView mTv = getActivity().findViewById(R.id.tv_title);
        mTv.setText("关于我们");
        return view;
    }

    private void initView(View view) {
        mImgMaImage = (ImageView) view.findViewById(R.id.img_ma_image);
        mRlMaUpdata = (RelativeLayout) view.findViewById(R.id.rl_ma_updata);
        mRlMaUpdata.setOnClickListener(this);
        mRlMaFeedback = (RelativeLayout) view.findViewById(R.id.rl_ma_feedback);
        mRlMaFeedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_ma_updata:
                break;
            case R.id.rl_ma_feedback:
                break;
        }
    }
}
