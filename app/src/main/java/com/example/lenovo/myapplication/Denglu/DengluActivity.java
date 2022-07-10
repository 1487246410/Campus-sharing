package com.example.lenovo.myapplication.Denglu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lenovo.myapplication.MainActivity;
import com.example.lenovo.myapplication.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * 登录页面
 */
public class DengluActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 用户名
     */
    private EditText mEtLoginUsername;
    /**
     * 用户名
     */
    private EditText mEtLoginPassword;
    /**
     * 登陆
     */
    private Button mBtnLogin;
    private LinearLayout mLlRetrievePwd;
    private LinearLayout mLlRegister;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_denglu);
        initViews();
    }


    public void initViews() {
        mEtLoginUsername = (EditText) findViewById(R.id.et_login_username);
        mEtLoginPassword = (EditText) findViewById(R.id.et_login_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mLlRetrievePwd = (LinearLayout) findViewById(R.id.ll_retrieve_pwd);
        mLlRetrievePwd.setOnClickListener(this);
        mLlRegister = (LinearLayout) findViewById(R.id.ll_register);
        mLlRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String username = mEtLoginUsername.getText().toString().trim();
                String password = mEtLoginPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username))
                    ShowToast("账号不能为空");
                else if (TextUtils.isEmpty(password)) {
                    ShowToast("密码不能为空");
                } else {
                    //网络
                    BmobUser user = new BmobUser();
                    user.loginByAccount(username, password, new LogInListener<BmobUser>() {
                        @Override
                        public void done(BmobUser bmobUser, BmobException e) {
                            if (e == null) {
                                Log.e("aaa", "成功" + bmobUser.toString());
                                startActivity(new Intent(getApplication(), MainActivity.class));
                                finish();
                            } else {
                                if (e.getErrorCode() == 101)
                                    Log.e("aaa", "失败" + e.toString());
                                ShowToast("密码或用户名错误");
                            }
                        }
                    });
                }
                break;
            case R.id.ll_retrieve_pwd:
                startActivity(new Intent(getApplicationContext(), PWDActivity.class));
                break;
            case R.id.ll_register:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            default:
                break;
        }
    }

    Toast mToast;

    public void ShowToast(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }

}
