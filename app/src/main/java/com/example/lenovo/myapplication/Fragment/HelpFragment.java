package com.example.lenovo.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.myapplication.Bean.XCRoundImageView;
import com.example.lenovo.myapplication.View.CenterActivity;
import com.example.lenovo.myapplication.View.DemandBean;
import com.example.lenovo.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 *
 */

public class HelpFragment extends Fragment {
    private ListView mLstHelp;
    private LinearLayout ll_one1;

    private XCRoundImageView img_center1;
    private List<DemandBean> lista = new ArrayList<>();
    private HelpAdapter helpAdapter;
    private LinearLayout ll_two2;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_help, null);
        initView(view);
        initData();
        mLstHelp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),Help_Activity.class);
                intent.putExtra("content", lista.get(i).getContent());
                intent.putExtra("price", lista.get(i).getPrice());
                intent.putExtra("phone", lista.get(i).getPhone());
                intent.putExtra("time", lista.get(i).getTime());
                intent.putExtra("address", lista.get(i).getAddress());
                intent.putExtra("fabu", lista.get(i).getCreatedAt());
                intent.putExtra("biaoti", lista.get(i).getBiaoti());
                startActivity(intent);
            }
        });
        return view;
    }

    private void initData() {
        BmobQuery<DemandBean> query = new BmobQuery<>();
        query.findObjects(new FindListener<DemandBean>() {
            public void done(List<DemandBean> object, BmobException e) {
                if (e == null) {
                    Log.e("bmob", "查询成功：共" + object.size() + "条数据。");
                    for (DemandBean demandBean : object) {
                        lista.add(demandBean);
                    }
                    helpAdapter = new HelpAdapter(lista);
                    mLstHelp.setAdapter(helpAdapter);
                } else {
                    Log.e("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });

    }


    class HelpAdapter extends BaseAdapter {
        private List<DemandBean> lista;

        public HelpAdapter(List<DemandBean> lista) {
            this.lista = lista;
        }

        @Override
        public int getCount() {
            return lista.size();
        }

        @Override
        public Object getItem(int i) {
            return lista.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ViewHolder holder;
            if (view == null) {
                view = inflater.inflate(R.layout.item_help, null);
                holder = new ViewHolder();
                holder.biaoti = view.findViewById(R.id.tv_biaoti);
                holder.qian = view.findViewById(R.id.tv_qian);
                holder.dizhi = view.findViewById(R.id.tv_dizhi);
                holder.tiem = view.findViewById(R.id.tv_time);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.biaoti.setText(lista.get(i).getBiaoti());
            holder.qian.setText(lista.get(i).getPrice());
            holder.dizhi.setText(lista.get(i).getAddress());
            holder.tiem.setText(lista.get(i).getCreatedAt());
            return view;
        }

    }

    static class ViewHolder {
        TextView biaoti;
        TextView qian;
        TextView dizhi;
        TextView tiem;
    }

    private void initView(View view) {
        mLstHelp = (ListView) view.findViewById(R.id.lst_help);
        ll_one1 = (LinearLayout) view.findViewById(R.id.ll_one1);
        ll_two2 = (LinearLayout) view.findViewById(R.id.ll_two2);
        img_center1 = (XCRoundImageView) view.findViewById(R.id.img_center1);
        img_center1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CenterActivity.class);
//                helpAdapter.notifyDataSetChanged();
                startActivity(intent);
            }
        });

        ll_one1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
//                helpAdapter.notifyDataSetChanged();
                fm.beginTransaction().replace(R.id.fl_main, new HomeFragment()).commit();
            }
        });
    }
}