package com.example.lenovo.myapplication.NewsActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.myapplication.R;

/**
 * Created by Administrator on 2017/12/29.
 */

public class C_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_c);
        initview();
    }

    private void initview() {
        ImageView c_fanhui = (ImageView) findViewById(R.id.c_fanhui);
        c_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }
}
