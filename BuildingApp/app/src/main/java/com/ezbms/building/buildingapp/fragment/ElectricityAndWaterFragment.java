package com.ezbms.building.buildingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.activity.MainActivity;

/**
 * Created by Hoang on 3/18/2016.
 */
public class ElectricityAndWaterFragment extends MyFragment {

    LinearLayout layout_history,layout_current;
    @Override
    public void refreshData(Intent intent) {

    }

    @Override
    public String getClassName() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, R.layout.electricity_and_water_fragment);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layout_current = (LinearLayout) findViewById(R.id.layout_cureent);
        layout_history = (LinearLayout) findViewById(R.id.layout_history);
        layout_history.setOnClickListener(this);
        layout_current.setOnClickListener(this);
        layout_current.performClick();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.layout_cureent){
            layout_current.setBackgroundColor(getResources().getColor(R.color.click_navbar));
            layout_history.setBackgroundColor(getResources().getColor(R.color.tablayout));
            ((MainActivity) getActivity()).replaceFragment(new CurrentElectricityAndWaterFragment(), R.id.frameMain2, false);
        }else {
            layout_current.setBackgroundColor(getResources().getColor(R.color.tablayout));
            layout_history.setBackgroundColor(getResources().getColor(R.color.click_navbar));
            ((MainActivity) getActivity()).replaceFragment(new HistoryElectricityAndWaterFragment(), R.id.frameMain2, false);
        }
    }
}
