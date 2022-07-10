package com.example.lenovo.myapplication.personage.bean.Ljf.Ljf;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import com.example.lenovo.myapplication.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCollectFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ImageButton mImgbMcBack;
    private ListView mLvMcCollect;
    private FragmentTransaction ft;
    private SlidingMenu slidingMenu;

    public MyCollectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.myshoucang, container, false);
        ft = getFragmentManager().beginTransaction();
        initView(view);
        return view;
    }
    private void initView(View view) {
        mLvMcCollect = (ListView) view.findViewById(R.id.lv_listview);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}
