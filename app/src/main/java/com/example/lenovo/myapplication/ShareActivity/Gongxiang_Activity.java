package com.example.lenovo.myapplication.ShareActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.myapplication.View.PublishBean;
import com.example.lenovo.myapplication.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

/**
 *
 */

public class Gongxiang_Activity extends AppCompatActivity {
    private List<PublishBean> list = new ArrayList<>();
    private String type;
    private ImageView mAFanhui;
    private ListView mLvListview;
    private int position;
    private TextView mTvText;
    private Map<Object, Object> mapp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_kechneg);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        position = intent.getIntExtra("position", 0);
        Log.e("bmob", type);
        Log.e("bmob", position + "");
        initView();
        xundata(position);
    }

    private void initView() {
        mAFanhui = (ImageView) findViewById(R.id.a_fanhui);
        mTvText = (TextView) findViewById(R.id.tv_text);
        mTvText.setText(type);
        mLvListview = (ListView) findViewById(R.id.lv_listview);
        mapp = new HashMap<>();
        mAFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mLvListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), KeCheng_Activity.class);
                intent.putExtra("content", list.get(position).getContent());
                intent.putExtra("price", list.get(position).getPrice());
                intent.putExtra("phone", list.get(position).getPhone());
                intent.putExtra("time", list.get(position).getTime());
                intent.putExtra("fabu", list.get(position).getCreatedAt());
                intent.putExtra("address", list.get(position).getAddress());
                intent.putExtra("biaoti", type);
                String o = (String) mapp.get(position);
                intent.putExtra("tp", o);
                startActivity(intent);

            }
        });
    }

    public void xundata(int type1) {
        BmobQuery<PublishBean> query = new BmobQuery<>();
        query.addWhereEqualTo("type", type1);
        query.include("author");
        query.findObjects(new FindListener<PublishBean>() {
            public void done(List<PublishBean> object, BmobException e) {
                if (e == null) {
                    Log.e("bmob", "查询成功：共" + object.size() + "条数据。");
                    for (PublishBean publishBean : object) {
                        String username = object.get(0).getUser().getUsername();
                        Log.e("mage", object.toString()+"aaaaaaaaaaaa"+username);
                        list.add(publishBean);
                    }
                    mLvListview.setAdapter(new MyAdapter(list));
                } else {
                    Log.e("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }


    class MyAdapter extends BaseAdapter {
        private List<PublishBean> list;

        public MyAdapter(List<PublishBean> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            Log.e("bmob", list.size() + "list");
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                view = getLayoutInflater().inflate(R.layout.activity_gongxiang1, null);
                holder.imageView = view.findViewById(R.id.image_user);
                holder.tv_biaoti = view.findViewById(R.id.tv_bt);
                holder.tv_jianjie = view.findViewById(R.id.tv_jj);
                holder.tv_didian = view.findViewById(R.id.tv_didian);
                holder.tv_item = view.findViewById(R.id.tv_item);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
//            BmobFile image = list.get(i).getImageview();
//            final File saveFile = new File(Environment.getExternalStorageDirectory(), image.getFilename());
//            image.download(saveFile, new DownloadFileListener() {
//                @Override
//                public void done(String savePath, BmobException e) {
//                    if (e == null) {
//                        mapp.put(i, savePath);
//                        holder.imageView.setImageURI(Uri.parse(savePath));
                        holder.tv_jianjie.setText(list.get(i).getContent());
                        holder.tv_didian.setText(list.get(i).getAddress());
                        holder.tv_item.setText(list.get(i).getCreatedAt());
                        holder.tv_biaoti.setText(type);
//                        Log.e("bmob", "下载成功,保存路径:" + savePath);
//                    } else {
//                        Log.e("bmob", "下载失败：" + e.getErrorCode() + "," + e.getMessage());
//                    }
//                }

//                @Override
//                public void onProgress(Integer value, long newworkSpeed) {
//                    Log.e("bmob", "下载进度：" + value + "," + newworkSpeed);
//                }
//            });
            return view;
        }
    }

    static class ViewHolder {
        ImageView imageView;
        TextView tv_biaoti;
        TextView tv_jianjie;
        TextView tv_didian;
        TextView tv_item;
    }




}
