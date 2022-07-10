package com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.set;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.MySettingFragment;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.UpdateListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePsdFragment extends Fragment implements View.OnClickListener {


    private FragmentTransaction ft;

    /**
     * 旧密码
     */
    private EditText mEditOldMi;
    /**
     * 新密码
     */
    private EditText mEditNewMi;
    /**
     * 再次输入新密码
     */
    private EditText mEditNewRemi;
    /**
     * 显示密码
     */
    private CheckBox mCheMima;
    /**
     * 确认
     */
    private Button mBtnMiQueren;


    public ChangePsdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chage_psd, container, false);
        ft = getActivity().getSupportFragmentManager().beginTransaction();
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
        mTv.setText("修改密码");
        return view;
    }

    private void initView(View view) {

        mEditOldMi = (EditText) view.findViewById(R.id.edit_old_mi);
        mEditNewMi = (EditText) view.findViewById(R.id.edit_new_mi);
        mEditNewRemi = (EditText) view.findViewById(R.id.edit_new_remi);
        mCheMima = (CheckBox) view.findViewById(R.id.che_mima);
        mCheMima.setOnClickListener(this);
        mBtnMiQueren = (Button) view.findViewById(R.id.btn_mi_queren);
        mBtnMiQueren.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.che_mima:


                break;
            case R.id.btn_mi_queren:
                final String newmi = mEditNewMi.getText().toString();
                String newremi = mEditNewRemi.getText().toString();
                final String oldmi = mEditOldMi.getText().toString();
                boolean oldmia = Emptyq(oldmi);
                boolean newmia = Emptyq(newmi);
                boolean newremia = Emptyq(newremi);
                if (!oldmia && !newmia && !newremia) {
                    boolean empty = Empty(newmi, newremi);
                    if (empty) {
                        BmobUser currentUser = BmobUser.getCurrentUser();
                        BmobUser.loginByAccount(currentUser.getUsername(), oldmi, new LogInListener<BmobUser>() {
                            @Override
                            public void done(BmobUser bmobUser, BmobException e) {
                                if (e == null) {
                                    BmobUser currentUser = BmobUser.getCurrentUser();
                                    currentUser.setPassword(newmi);
                                    currentUser.update(bmobUser.getObjectId(), new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if (e == null) {
                                                Log.e("Bmob", "更新用户信息成功");
                                            } else {
                                                Log.e("bmob", "更新用户信息失败:" + e.getMessage());
                                            }
                                        }
                                    });
                                    Log.e("bmob", "登录成功");
                                } else {
                                    Log.e("bmob", "登录失败:" + e.getMessage());
                                }


                            }
                        });
                    } else {
                        Log.e("bmob", "caaaww");
                    }
                } else {
                    Log.e("bmob", "cww");
                }
                break;
        }
    }


    public boolean Emptyq(String a) {
        if (a.equals("") || a == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Empty(String a, String b) {
        return a.equals(b) ? true : false;
    }

}
