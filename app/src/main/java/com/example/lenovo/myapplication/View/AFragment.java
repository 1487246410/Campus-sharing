package com.example.lenovo.myapplication.View;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.lenovo.myapplication.Bean.ShanYaoApplication;
import com.example.lenovo.myapplication.R;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

import static android.app.Activity.RESULT_OK;
import static com.example.lenovo.myapplication.View.Utilea.getSmallBitmap;

/**
 * 发布共享
 */

public class AFragment extends Fragment implements View.OnClickListener {


    /**
     * 详细信息及图片添加。
     */
    private EditText mEtShareContent;
    /**
     * 价格
     */
    private EditText mEtSharePrice;
    /**
     * 押金
     */
    private EditText mEtSharePledge;
    /**
     * 请输入您的手机号
     */
    private EditText mEtSharePhone;
    /**
     * 时间
     */
    private EditText mEtShareTime;
    /**
     * 请输入详细地址
     */
    private EditText mEtShareAddress;
    /**
     * 确定
     */
    private Button mBtnSharePublish;
    private ImageButton mImagebut;
    private String imageurl;
    private int TYEP = 0;//类型

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.afragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mEtShareContent = (EditText) view.findViewById(R.id.et_share_content);
        mEtSharePrice = (EditText) view.findViewById(R.id.et_share_price);
        mEtSharePledge = (EditText) view.findViewById(R.id.et_share_pledge);
        mEtSharePhone = (EditText) view.findViewById(R.id.et_share_phone);
        mEtShareTime = (EditText) view.findViewById(R.id.et_share_time);
        mEtShareAddress = (EditText) view.findViewById(R.id.et_share_address);
        mBtnSharePublish = (Button) view.findViewById(R.id.btn_share_publish);
        mBtnSharePublish.setOnClickListener(this);
        mEtShareContent.setOnClickListener(this);
//        mImagebut = (ImageButton) view.findViewById(R.id.imagebut);
        Spinner spinner = view.findViewById(R.id.sp_lx);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String[] stringArray = getResources().getStringArray(R.array.share_type);
//                Log.e("bmob", "你点击的是" + position + stringArray[position]);
//                TYEP = position;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        adapter.add("课程笔记");
        adapter.add("帮拿快递");
        adapter.add("寻物启示");
        adapter.add("游戏资源");
        adapter.add("体育用品");
        adapter.add("电影资源");
        adapter.add("其他资源");
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                TYEP = arg2;
                Log.e("type", "" + arg2);
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
//        mImagebut.setOnClickListener(this);
        mEtSharePrice.setOnClickListener(this);
        mEtSharePledge.setOnClickListener(this);
        mEtSharePhone.setOnClickListener(this);
        mEtShareTime.setOnClickListener(this);
        mEtShareAddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_share_publish:
                final String shareContent = mEtShareContent.getText().toString();
                final String sharePrice = mEtSharePrice.getText().toString();
                final String sharePledge = mEtSharePledge.getText().toString();
                final String sharePhone = mEtSharePhone.getText().toString();
                final String shareTime = mEtShareTime.getText().toString();
                final String shareAddress = mEtShareAddress.getText().toString();
                if (Empty(shareAddress) || Empty(shareContent) || Empty(sharePrice) || Empty(sharePledge) || Empty(sharePhone) || Empty(shareTime)) {
                    Toast.makeText(ShanYaoApplication.getContext(), "您的信息还没完善", Toast.LENGTH_SHORT).show();
                } else {
//                    final BmobFile bmobFile = new BmobFile(new File(imageurl));
//                    bmobFile.uploadblock(new UploadFileListener() {
//                        @Override
//                        public void done(BmobException e) {
//                            if (e == null) {
                                BmobUser bmobUser = BmobUser.getCurrentUser();
                                PublishBean p = new PublishBean();
                                p.setUser(bmobUser);
                                p.setContent(shareContent);
                                p.setType(TYEP);
                                p.setPrice(sharePrice);
                                p.setPledge(sharePledge);
                                p.setPhone(sharePhone);
                                p.setTime(shareTime);
                                p.setAddress(shareAddress);
//                                p.setImageview(bmobFile);
                                p.save(new SaveListener<String>() {
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
//                                Log.e("bmob", "上传文件成功:" + bmobFile.toString());
//                            } else {
//
//                                Log.e("bmob", "上传文件失败：" + e.getMessage());
//                            }
//                        }

//                        @Override
//                        public void onProgress(Integer value) {
//                            // 返回的上传进度（百分比）
//                            Log.e("bmob", "图片" + value);
//                        }
//                    });
                }
                break;

//            case R.id.imagebut://Intent.ACTION_PICK
//                Intent intent = new Intent();
//                /* 开启Pictures画面Type设定为image */
//                intent.setType("image/*");
//                /* 使用Intent.ACTION_GET_CONTENT这个Action */
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                /* 取得相片后返回本画面 */
//                startActivityForResult(intent, 5);
//                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            imageurl = getSmallBitmap(uri);
            mImagebut.setImageURI(Uri.parse(imageurl));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public boolean Empty(String a) {
        if (a.equals("") || a == null) {
            return true;
        } else {
            return false;
        }
    }

}
