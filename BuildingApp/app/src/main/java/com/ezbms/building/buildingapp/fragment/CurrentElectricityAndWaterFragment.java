package com.ezbms.building.buildingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.adapter.ElectricAndWaterAdapter;
import com.ezbms.building.buildingapp.entity.ElectricAndWaterEntity;

import java.util.ArrayList;

/**
 * Created by Hoang on 3/18/2016.
 */
public class CurrentElectricityAndWaterFragment extends MyFragment {
    ListView listView;
    ElectricAndWaterAdapter adapter;
    ArrayList<ElectricAndWaterEntity> arrayList = new ArrayList<>();

    @Override
    public void refreshData(Intent intent) {

    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, R.layout.current_electtricity_fragment);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) findViewById(R.id.listview);
        initData();
    }

    private void initData() {
        adapter = new ElectricAndWaterAdapter(getActivity());
        adapter.add(new ElectricAndWaterEntity(6,123456));
        adapter.add(new ElectricAndWaterEntity(7,12345));
        adapter.add(new ElectricAndWaterEntity(8,1234));
        adapter.add(new ElectricAndWaterEntity(9,123));
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }
}
