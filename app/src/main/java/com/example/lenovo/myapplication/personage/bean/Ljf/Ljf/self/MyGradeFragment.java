package com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.self;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.MymessageFragment;


/**
 *班级系别
 */
public class MyGradeFragment extends Fragment {

    private FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_grade, container, false);
        ImageButton ibn = getActivity().findViewById(R.id.home_back);
        ibn.setVisibility(View.VISIBLE);
        ft = getActivity().getSupportFragmentManager().beginTransaction();
        getActivity().findViewById(R.id.ib_menu).setVisibility(View.INVISIBLE);
        ibn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.replace(R.id.fl_main, new MymessageFragment()).commit();
            }
        });
        TextView mTv = getActivity().findViewById(R.id.tv_title);
        mTv.setText("系别班级");
        return view;
    }

}



