package com.example.lenovo.myapplication.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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

import org.w3c.dom.Text;

import static com.example.lenovo.myapplication.Bean.ShanYaoApplication.getContext;

/**
 * Created by Administrator on 2018/1/2.
 */

public class Help_Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_fanhui;
    private LinearLayout ll_bangzhu;
    private LinearLayout ll_lianxi;
    private LinearLayout ll_fenxiang;
    private Dialog dialog;
    private View inflate;
    private ImageView mImgFanhui;
    private ImageView mImageView;
    /**
     * 数学笔记有吗 我信息系的。急需，有的老铁联系我。
     */
    private TextView mTvContent;
    private ImageView mImage;
    /**
     * 赏金5元
     */
    private TextView mTextView;
    private LinearLayout mLlBangzhu;
    private LinearLayout mLlLianxi;
    /**
     * 分享
     */
    private TextView mShoucang;
    private LinearLayout mLlFenxiang;
    private String nameaa;
    private String contentaa;
    private String priceaa;
    private String phoneaa;
    private String addressaa;
    private TextView tvanem;
    private String timeaa;
    private String fabuaa;
    private String biaoti;
    private String image;
    private String tp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_particulars2);
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
        ininview();
    }


    private void ininview() {
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


        img_fanhui = (ImageView) findViewById(R.id.img_fanhui);
        ll_bangzhu = (LinearLayout) findViewById(R.id.ll_bangzhu);
        ll_lianxi = (LinearLayout) findViewById(R.id.ll_lianxi);
        ll_fenxiang = (LinearLayout) findViewById(R.id.ll_fenxiang);
        img_fanhui.setOnClickListener(this);
        ll_bangzhu.setOnClickListener(this);
        ll_lianxi.setOnClickListener(this);
        ll_fenxiang.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_fanhui:
                finish();
                break;
            case R.id.ll_bangzhu:
                dialog = new Dialog(Help_Activity.this, R.style.ActionSheetDialogStyle);
                //填充对话框的布局
                inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout3, null);
                TextView tvtia = inflate.findViewById(R.id.tv_title);
                TextView tvtib = inflate.findViewById(R.id.tv_content);
                TextView tvtic = inflate.findViewById(R.id.jiage);
                tvtia.setText(biaoti);
                tvtib.setText(contentaa);
                tvtic.setText("赏金" + priceaa + "元");


                //初始化控件
                //将布局设置给Dialog
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
            case R.id.ll_lianxi:
                dialog = new Dialog(Help_Activity.this, R.style.ActionSheetDialogStyle);
                //填充对话框的布局
                inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_layout4, null);
                TextView tv_conent = inflate.findViewById(R.id.tv_content);
                tv_conent.setText(contentaa);

                //初始化控件
                //将布局设置给Dialog
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
                break;
            case R.id.ll_fenxiang:
                dialog = new Dialog(Help_Activity.this, R.style.ActionSheetDialogStyle);
                //填充对话框的布局
                inflate = LayoutInflater.from(Help_Activity.this).inflate(R.layout.dialog_transpond, null);
                //将布局设置给Dialog
                dialog.setContentView(inflate);
                //获取当前Activity所在的窗体
                Window dialog1 = dialog.getWindow();
                //获得窗体的属性
                WindowManager.LayoutParams lpp = dialog1.getAttributes();
                lpp.y = 0;//设置Dialog距离底部的距离
                //将属性设置给窗体
                dialog1.setAttributes(lpp);
                dialog.show();//显示对话框
                WindowManager mm = getWindowManager();
                Display dd = mm.getDefaultDisplay(); //为获取屏幕宽、高
                WindowManager.LayoutParams pp = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
                pp.width = (int) (dd.getWidth() * 1.0); //宽度设置为屏幕的0.5
                dialog.getWindow().setAttributes(pp); //设置生效
                break;
        }
    }
}
