package com.ezbms.building.buildingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.adapter.NotiAdapter;
import com.ezbms.building.buildingapp.entity.NotiEntity;

/**
 * Created by Hoang on 3/19/2016.
 */
public class NotificationFragment extends MyFragment {

    ListView listView;
    NotiAdapter adapter;
    ImageView btnItemLeft;//header
    TextView txt_title;

    @Override
    public void refreshData(Intent intent) {

    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater,container, R.layout.notification_fragment);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //header
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText("THÔNG BÁO TỪ BQL TÒA NHÀ");
        btnItemLeft = (ImageView) findViewById(R.id.btnItemLeft);
        btnItemLeft.setImageResource(R.drawable.back);
        btnItemLeft.setVisibility(View.VISIBLE);
        btnItemLeft.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new NotiAdapter(getActivity());
        adapter.add(new NotiEntity("Đóng tiền điện","Tổng tiền: 2.500.000 VND\nThời gian đóng: 24/1/2015 - 24/2/2015","24/1/2015"));
        adapter.add(new NotiEntity("Đóng tiền điện","Tổng tiền: 2.500.000 VND\nThời gian đóng: 24/1/2015 - 24/2/2015","24/1/2015"));
        adapter.add(new NotiEntity("Đóng tiền điện","Tổng tiền: 2.500.000 VND\nThời gian đóng: 24/1/2015 - 24/2/2015","24/1/2015"));
        adapter.add(new NotiEntity("Hôm qua"));
        adapter.add(new NotiEntity("Đóng tiền điện","Tổng tiền: 2.500.000 VND\nThời gian đóng: 24/1/2015 - 24/2/2015","24/1/2015"));
        adapter.add(new NotiEntity("Đóng tiền điện","Tổng tiền: 2.500.000 VND\nThời gian đóng: 24/1/2015 - 24/2/2015","24/1/2015"));
        adapter.add(new NotiEntity("Đóng tiền điện","Tổng tiền: 2.500.000 VND\nThời gian đóng: 24/1/2015 - 24/2/2015","24/1/2015"));
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnItemLeft){
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }
}
