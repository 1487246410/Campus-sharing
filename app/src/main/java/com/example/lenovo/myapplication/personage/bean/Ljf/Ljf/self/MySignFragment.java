package com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.self;


import android.content.Intent;
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
import com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.MymessageFragment;


/**
 *
 */
public class MySignFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ImageButton mImgbMsBack;
    /**
     * 保存
     */
    private Button mBtnMsSign;
    /**
     * 请输入内容
     */
    private EditText mEditMsSign;
    private FragmentTransaction ft;
    /**
     * 返回
     */
    private Button mBtnMsFanhiu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_sign, container, false);
        initView(view);
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
        mTv.setText("个性签名");
        return view;
    }


    private void initView(View view) {
        mEditMsSign = (EditText) view.findViewById(R.id.edit_ms_sign);
        mBtnMsFanhiu = (Button) view.findViewById(R.id.btn_ms_fanhiu);
        mBtnMsFanhiu.setOnClickListener(this);
        mBtnMsSign = (Button) view.findViewById(R.id.btn_ms_sign);
        mBtnMsSign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_ms_fanhiu:
                ft.replace(R.id.fl_main, new MymessageFragment()).commit();
                break;
            case R.id.btn_ms_sign:
                TextView sign = getActivity().findViewById(R.id.tv_user_Signature);
                sign.setText(123+"");
                break;
        }
    }
}
