package com.example.lenovo.myapplication.JobActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.myapplication.R;

/**
 * Created by Administrator on 2018/1/5.
 */

public class Jianzhi2_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_job2);
        initview();
    }

    private void initview() {
//        ImageView fanhui = (ImageView) findViewById(R.id.a_fanhui);
//        fanhui.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

    }
}
