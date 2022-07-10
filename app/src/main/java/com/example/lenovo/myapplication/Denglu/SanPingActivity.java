package com.example.lenovo.myapplication.Denglu;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.lenovo.myapplication.MainActivity;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.Util.NetWorkUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class SanPingActivity extends AppCompatActivity {
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//8b5823caa872d68bb28c42b3b906711b
        Bmob.initialize(this, "37f25482ba56f5a28b805cddd9ab0766");
        setContentView(R.layout.activity_san_ping);
        iv = (ImageView) findViewById(R.id.splash_iv);
        //设置透明度动画从无到有
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        //设置动画持续时间
        alphaAnimation.setDuration(500);
        //开始显示动画
        iv.startAnimation(alphaAnimation);
        //给动画设置监听，在动画结束的时候进行跳转
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始时执行
                Log.e("TAG", "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束时执行
                Log.e("TAG", "onAnimationEnd: ");
                boolean networkConnected = NetWorkUtils.isNetworkConnected(getApplicationContext());
                if (networkConnected) {
                    BmobUser currentUser = BmobUser.getCurrentUser();
                    Intent intent;
                    if (currentUser != null) {
                        // 允许用户使用应用
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                    } else {
                        intent = new Intent(getApplicationContext(), DengluActivity.class);
                        //缓存用户对象为空时， 可打开用户注册界面…
                    }
                    startActivity(intent);
                    finish();
                } else {

                    showNormalDialog();

                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //动画重复播放时执行
                Log.e("TAG", "onAnimationRepeat: ");
            }
        });
    }

    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(SanPingActivity.this);
        normalDialog.setTitle("没有网络！！！！");

        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        finish();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        finish();
                    }
                });
        // 显示
        normalDialog.show();
    }

}
