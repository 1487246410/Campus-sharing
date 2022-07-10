package com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.self;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;


/**
 * 昵称
 */
public class NicknameFragment extends Fragment  {


    private View view;
    private ImageButton mImgbMnBack;
    /**
     * 确认
     */
    private Button mBtnMn;
    /**
     * 你的名字我的心事
     */
    private EditText mEditMn;
    private FragmentTransaction ft;
    private TextView mTvMmNickName;
    /**
     * 取消
     */
    private Button mBtnQx;

    public NicknameFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nickname, container, false);
        ft = getFragmentManager().beginTransaction();
        return view;
    }
}
