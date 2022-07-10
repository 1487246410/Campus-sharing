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
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lenovo.myapplication.MainActivity;
import com.example.lenovo.myapplication.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**b
 * 注册页面
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageButton mBtnClose;
    /**
     * 请输入你的学号
     */
    private EditText mEtUsername;
    /**
     * 请输入6-12位数字或字母的密码
     */
    private EditText mEtPassword;
    /**
     * 请输入邮箱
     */
    private EditText mEtEmail;
    /**
     * 确认
     */
    private Button mBtnNext;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

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

    private void initView() {
        mBtnClose = (ImageButton) findViewById(R.id.btn_close);
        mBtnClose.setOnClickListener(this);
        mEtUsername = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mEtEmail = (EditText) findViewById(R.id.et_email);
        mBtnNext = (Button) findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                finish();
                break;
            case R.id.btn_next:
                final String et_usernaem = mEtUsername.getText().toString();
                String et_email = mEtEmail.getText().toString();
                final String et_pwd = mEtPassword.getText().toString();
                if (TextUtils.isEmpty(et_email) || TextUtils.isEmpty(et_usernaem) || TextUtils.isEmpty(et_pwd)) {
                    ShowToast("请补全信息");
                } else {
                    BmobUser user = new BmobUser();
                    user.setUsername(et_usernaem);
                    user.setPassword(et_pwd);
                    user.setEmail(et_email);
                    user.signUp(new SaveListener<BmobUser>() {
                        @Override
                        public void done(BmobUser bmobUser, BmobException e) {
                            if (e == null) {
                                ShowToast("注册成功");
                                BmobUser.loginByAccount(et_usernaem,et_pwd, new LogInListener<BmobUser>() {
                                    @Override
                                    public void done(BmobUser bmobUser, BmobException e) {
                                        if (e == null) {
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            Log.e("bmob", "用户登陆成功");
                                        }else {
                                            Log.e("bmob", "登陆失败:" + e.getErrorCode() + e.getMessage());
                                        }
                                    }
                                });
                            } else {
                                Log.e("bmob", "注册失败:" + e.getErrorCode() + e.getMessage());
                                if (e.getErrorCode() == 203) {
                                    ShowToast("邮箱重复");
                                }
                            }
                        }
                    });
                }
                break;
            default:
                break;
        }
    }
}
