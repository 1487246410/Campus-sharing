package com.example.lenovo.myapplication.Denglu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myapplication.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 忘记密码
 */

public class PWDActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageButton mBtnClose;
    /**
     * 您的账号：
     */
    private TextView mTextView;
    private EditText mEtUser;
    /**
     * 请输入邮箱
     */
    private EditText mEtEmail;
    /**
     * 确定
     */
    private Button mBtnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        initView();


    }

    private void initView() {
        mBtnClose = (ImageButton) findViewById(R.id.btn_close);
        mBtnClose.setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.textView);
        mEtUser = (EditText) findViewById(R.id.et_user);
        mEtEmail = (EditText) findViewById(R.id.et_email);
        mBtnNext = (Button) findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_close:
                finish();
                break;
            case R.id.btn_next:
                String user = mEtUser.getText().toString();
                final String email = mEtEmail.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(user)) {
                    Toast.makeText(getApplicationContext(), "请补全信息", Toast.LENGTH_LONG).show();
                } else {
                    BmobUser.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(getApplicationContext(), "重置密码请求成功，请到" + email + "邮箱进行密码重置操作", Toast.LENGTH_LONG).show();
                            } else {
                                Log.e("bmob", "失败:" + e.getMessage());
                            }
                        }
                    });
                }
                break;
        }
    }
}
