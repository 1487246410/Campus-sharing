package com.example.lenovo.myapplication.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.lenovo.myapplication.Bean.ShanYaoApplication;
import com.example.lenovo.myapplication.R;
import java.util.ArrayList;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 需求发布
 */

public class BFragment extends Fragment implements View.OnClickListener {
    private String[] na = {"课程笔记", "体育用品", "帮拿快递", "电影资源", "游戏资源", "电影资源", "其他资源"};
    /**
     * 详细信息。
     */
    private EditText mEtDemandContent;
    /**
     * 赏金
     */
    private EditText mEtDemandPrice;
    /**
     * 请输入您的手机号
     */
    private EditText mEtDemandPhone;
    /**
     * 时间
     */
    private EditText mEtDemandTime;
    /**
     * 请输入详细地址
     */
    private EditText mEtDemandAddress;
    /**
     * 确定
     */
    private Button mBtnDemandPublish;
    private int position1;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bfragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mEtDemandContent = (EditText) view.findViewById(R.id.et_demand_content);
        mEtDemandPrice = (EditText) view.findViewById(R.id.et_demand_price);
        mEtDemandPhone = (EditText) view.findViewById(R.id.et_demand_phone);
        mEtDemandTime = (EditText) view.findViewById(R.id.et_demand_time);
        mEtDemandAddress = (EditText) view.findViewById(R.id.et_demand_address);
        mBtnDemandPublish = (Button) view.findViewById(R.id.btn_demand_publish);
        mBtnDemandPublish.setOnClickListener(this);
        Spinner spinner = view.findViewById(R.id.spinner);
       /*设置数据源*/
        ArrayList<String> list = new ArrayList<String>();
        list.add("课程笔记");
        list.add("帮拿快递");
        list.add("寻物启示");
        list.add("游戏资源");
        list.add("体育用品");
        list.add("电影资源");
        list.add("其他资源");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position1 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_demand_publish:
                String demandContent = mEtDemandContent.getText().toString();
                String demandPrice = mEtDemandPrice.getText().toString();
                String demandPhone = mEtDemandPhone.getText().toString();
                String demandTime = mEtDemandTime.getText().toString();
                String demandAddress = mEtDemandAddress.getText().toString();
                if (Empty(demandContent) || Empty(demandPrice) || Empty(demandPhone) || Empty(demandTime) || Empty(demandAddress)) {
                    Toast.makeText(ShanYaoApplication.getContext(), "您的信息还没完善", Toast.LENGTH_SHORT).show();
                } else {
                    BmobUser bmobUser = BmobUser.getCurrentUser();
                    DemandBean d = new DemandBean();
                    d.setUser(bmobUser);
                    d.setContent(demandContent);
                    d.setPrice(demandPrice);
                    d.setPhone(demandPhone);
                    d.setTime(demandTime);
                    d.setAddress(demandAddress);
                    d.setBiaoti(na[position1]);
                    d.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Toast.makeText(getActivity(),"上传成功", Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                                Log.e("bmob", "成功" + s.toString());
                            } else {
                                Toast.makeText(getActivity(),"上传失败:"+e.toString(), Toast.LENGTH_SHORT).show();
                                Log.e("bmob", "失败" + e.toString());
                            }
                        }
                    });
                }
                break;
        }
    }

    public boolean Empty(String a) {
        if (a.equals("") || a == null) {
            return true;
        } else {
            return false;
        }
    }


}
