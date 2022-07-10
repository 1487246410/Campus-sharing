package com.example.lenovo.myapplication.personage.bean.Ljf.Ljf;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.self.MyGradeFragment;
import com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.self.MySignFragment;
import com.example.lenovo.myapplication.personage.bean.Ljf.Ljf.self.NicknameFragment;

import static com.example.lenovo.myapplication.View.Utilea.getSmallBitmap;


/**
 * 个人信息
 */
public class MymessageFragment extends Fragment implements View.OnClickListener {
    private static final int RESULT_OK = 0;
    private FragmentTransaction ft;
    private View view;
    private ImageButton mImageUser;
    private RelativeLayout mRlMmTouxiang;
    /**
     * 某某某
     */
    private TextView mTvUserName;
    private RelativeLayout mRlMmNickname;
    /**
     * 男
     */
    private TextView mTvUserSex;
    /**
     * 某某某
     */
    private TextView mTvUserClbum;
    private RelativeLayout mRlMmCampus;
    /**
     * 爱笑的女孩运气不会差
     */
    private TextView mTvUserSignature;
    private RelativeLayout mRlMmSign;
    private ImageButton mImageUserScanlife;
    private RelativeLayout mRlMmQr;
    private String imageurl;
    private ImageSwitcher mImagebut;
    private FragmentTransaction fragmentTransaction;
    private RelativeLayout mRlUserTouxiang;
    private RelativeLayout mRlUserName;
    private RelativeLayout mRlUserSex;
    private RelativeLayout mRlUserClbum;
    private RelativeLayout mRlUserSign;
    private RelativeLayout mRlUserQr;
    private int XINGB=1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mymessage, container, false);
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        initView(view);
        ImageButton ibn = getActivity().findViewById(R.id.home_back);
        ibn.setVisibility(View.INVISIBLE);
        getActivity().findViewById(R.id.ib_menu).setVisibility(View.VISIBLE);
        TextView mTv = getActivity().findViewById(R.id.tv_title);
        mTv.setText("个人信息");
        return view;
    }


    private void initView(View view) {
        mImageUser = (ImageButton) view.findViewById(R.id.image_user);
        mRlUserTouxiang = (RelativeLayout) view.findViewById(R.id.rl_user_touxiang);
        mRlUserTouxiang.setOnClickListener(this);
        mTvUserName = (TextView) view.findViewById(R.id.tv_user_name);
        mRlUserName = (RelativeLayout) view.findViewById(R.id.rl_user_name);
        mRlUserName.setOnClickListener(this);
        mTvUserSex = (TextView) view.findViewById(R.id.tv_user_sex);
        mRlUserSex = (RelativeLayout) view.findViewById(R.id.rl_user_sex);
        mRlUserSex.setOnClickListener(this);
        mTvUserClbum = (TextView) view.findViewById(R.id.tv_user_clbum);
        mRlUserClbum = (RelativeLayout) view.findViewById(R.id.rl_user_clbum);
        mRlUserClbum.setOnClickListener(this);
        mTvUserSignature = (TextView) view.findViewById(R.id.tv_user_Signature);
        mRlUserSign = (RelativeLayout) view.findViewById(R.id.rl_user_sign);
        mRlUserSign.setOnClickListener(this);
        mImageUserScanlife = (ImageButton) view.findViewById(R.id.image_user_scanlife);
        mRlUserQr = (RelativeLayout) view.findViewById(R.id.rl_user_qr);
        mRlUserQr.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_user_touxiang:
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(intent, 1);
                break;
            case R.id.rl_user_name:
//                fragmentTransaction.replace(R.id.fl_main, new NicknameFragment()).commit();
                break;
            case R.id.rl_user_sex:
                setxingbie();
                break;
            case R.id.rl_user_clbum:
                fragmentTransaction.replace(R.id.fl_main, new MyGradeFragment()).commit();
                break;
            case R.id.rl_user_sign:
                fragmentTransaction.replace(R.id.fl_main, new MySignFragment()).commit();
                break;
            case R.id.rl_user_qr:

                break;
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            imageurl = getSmallBitmap(uri);
            mImageUser.setImageURI(Uri.parse(imageurl));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setxingbie() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.tubiao1);
        builder.setTitle("请选择性别");
        final String[] sex = {"男", "女"};
        builder.setSingleChoiceItems(sex, XINGB, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("bmob", "性别为：" + sex[which]);
                mTvUserSex.setText( sex[which]);
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("Bmob",which+"");
                XINGB=which+1;

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
