package com.example.lenovo.myapplication.personage.bean.Ljf.Ljf;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.lenovo.myapplication.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyInformationFragment extends Fragment {
    private View view;
    private ImageButton mImgbMiBack;
    private FragmentTransaction ft;
    private SlidingMenu slidingMenu;

    public MyInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_information, container, false);
        return view;
    }


}
