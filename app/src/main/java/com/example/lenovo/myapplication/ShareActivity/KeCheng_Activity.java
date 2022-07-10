package com.example.lenovo.myapplication.ShareActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;

import static com.example.lenovo.myapplication.Bean.ShanYaoApplication.getContext;

/**
 * Created by Administrator on 2018/1/2.
 */

public class KeCheng_Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView fanhui;
    private TextView shoucang;
    private View inflate;
    String str1 = "收藏";
    String str2 = "已收藏";
    private LinearLayout ll_xiadan;
    private LinearLayout ll_lianxi;
    private LinearLayout ll_shoucang;
    private Dialog dialog;
    private String nameaa;
    private String contentaa;
    private String priceaa;
    private String phoneaa;
    private String timeaa;
    private String fabuaa;
    private String addressaa;
    private String biaoti;
    private String tp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.share_particulars);
        Intent intent = getIntent();
        nameaa = intent.getStringExtra("name");
        contentaa = intent.getStringExtra("content");
        priceaa = intent.getStringExtra("price");
        phoneaa = intent.getStringExtra("phone");
        timeaa = intent.getStringExtra("time");
        fabuaa = intent.getStringExtra("fabu");
        addressaa = intent.getStringExtra("address");
        biaoti = intent.getStringExtra("biaoti");
        tp = intent.getStringExtra("tp");
        initview();

    }

    private void initview() {
        TextView title = (TextView) findViewById(R.id.tv_title);
        TextView content = (TextView) findViewById(R.id.tv_content);
        ImageView image = (ImageView) findViewById(R.id.imageaa);

        if (!TextUtils.isEmpty(tp)) {
            image.setImageURI(Uri.parse(tp));
        }
        TextView aa = (TextView) findViewById(R.id.textView);
        TextView youxiaoqi = (TextView) findViewById(R.id.tv_youxiaoqi);
        TextView soujihao = (TextView) findViewById(R.id.tv_soujihao);
        TextView dizhi = (TextView) findViewById(R.id.tv_dizhi);
        TextView fb_tiem = (TextView) findViewById(R.id.fb_item);

        title.setText(biaoti);
        content.setText(contentaa);
        aa.setText("赏金" + priceaa + "元");
        youxiaoqi.setText("有效期限：" + timeaa + "天");
        soujihao.setText("手机号:" + phoneaa);
        dizhi.setText("详细地址：" + addressaa);
        fb_tiem.setText("发布时间：" + fabuaa);


        fanhui = (ImageView) findViewById(R.id.fanhui);
        ll_lianxi = (LinearLayout) findViewById(R.id.ll_lianxi);
        ll_shoucang = (LinearLayout) findViewById(R.id.ll_shoucang);
        ll_shoucang.setOnClickListener(this);
        shoucang = (TextView) findViewById(R.id.shoucang);
        fanhui.setOnClickListener(this);
        ll_xiadan = (LinearLayout) findViewById(R.id.ll_xia);
        ll_xiadan.setOnClickListener(this);
        ll_lianxi.setOnClickListener(this);
        shoucang.setOnClickListener(this);
        shoucang.setText(str1);//设置原来的文本
        shoucang.setTag(false);
        ll_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean flag = (boolean) shoucang.getTag();
//                boolean flag = (boolean) shoucang.getTag();//当点击时，首先判断是否已经点击过
                if (!flag) {//没有被点击过
                    shoucang.setText(str2);
                    shoucang.setTag(true);
                } else {//已经点击过了
                    final AlertDialog.Builder builder = new AlertDialog.Builder(KeCheng_Activity.this);
                    builder.setTitle("您确定要取消收藏吗？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            shoucang.setText(str1);
                            shoucang.setTag(false);
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.ll_xia:
                dialog = new Dialog(KeCheng_Activity.this, R.style.ActionSheetDialogStyle);
                //填充对话框的布局
                inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_layout, null);
                //初始化控件
                //将布局设置给Dialog
                View bue = inflate.findViewById(R.id.bue_quxiao);
                TextView tv_title = inflate.findViewById(R.id.tv_title);
                TextView tv_content = inflate.findViewById(R.id.tv_content);
                TextView tv_jiange = inflate.findViewById(R.id.tv_jiange);
                tv_title.setText(biaoti);
                tv_content.setText(contentaa);
                tv_jiange.setText("赏金" + priceaa + "元");
                bue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(inflate);
                //获取当前Activity所在的窗体
                Window dialogWindow = dialog.getWindow();
                //设置Dialog从窗体底部弹出
                dialogWindow.setGravity(Gravity.BOTTOM);
                //获得窗体的属性
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.y = 0;//设置Dialog距离底部的距离
                //将属性设置给窗体
                dialogWindow.setAttributes(lp);
                dialog.show(); //显示对话框
                WindowManager m = getWindowManager();
                Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
                WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
                p.height = (int) (d.getHeight() * 0.46);
                p.width = (int) (d.getWidth() * 1); //宽度设置为屏幕的0.5
                dialog.getWindow().setAttributes(p); //设置生效
//                Toast.makeText(getApplicationContext(), "为什么会挂掉呢", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_lianxi:
                dialog = new Dialog(KeCheng_Activity.this, R.style.ActionSheetDialogStyle);
                //填充对话框的布局
                inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout2, null);
                //初始化控件
                //将布局设置给Dialog
                View butttt = inflate.findViewById(R.id.butttt_quxiao);
                butttt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(inflate);
                //获取当前Activity所在的窗体
                Window window = dialog.getWindow();
                //设置Dialog从窗体底部弹出
                window.setGravity(Gravity.BOTTOM);
                //获得窗体的属性
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.y = 0;//设置Dialog距离底部的距离
                //将属性设置给窗体
                window.setAttributes(layoutParams);
                dialog.show();//显示对话框

                WindowManager manager = getWindowManager();
                Display display = manager.getDefaultDisplay(); //为获取屏幕宽、高
                WindowManager.LayoutParams params = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
                params.height = (int) (display.getHeight() * 0.44);
                params.width = (int) (display.getWidth() * 1); //宽度设置为屏幕的0.5
                dialog.getWindow().setAttributes(params); //设置生效
                break;
        }
    }
}
