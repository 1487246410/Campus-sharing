package com.example.lenovo.myapplication.personage.bean.Ljf.Ljf;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.Bean.XCRoundImageView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyTrendsFragment extends Fragment {


    private FragmentTransaction ft;
    private View view;
    private ImageButton mImgbMtBack;
    private XCRoundImageView mMtTouxiang;
    private RelativeLayout mRlMtBack;
    private SlidingMenu slidingMenu;
    public MyTrendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_trends, container, false);
        ft = getFragmentManager().beginTransaction();
        return view;
    }

}
