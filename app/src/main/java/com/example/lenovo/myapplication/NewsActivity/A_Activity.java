package com.example.lenovo.myapplication.NewsActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.myapplication.R;

/**
 * Created by Administrator on 2017/12/28.
 */

public class A_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_a);
        initview();
    }

    private void initview() {
        ImageView a_fanhui = (ImageView) findViewById(R.id.a_fanhui);
        a_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }
}
