package com.ezbms.building.buildingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.adapter.CategoryAdapter;
import com.ezbms.building.buildingapp.adapter.ServiceAdapter;
import com.ezbms.building.buildingapp.entity.ServiceEntity;

import java.util.ArrayList;

/**
 * Created by Hoang on 3/9/2016.
 */
public class ServiceFragment extends MyFragment {
    @Override
    public void refreshData(Intent intent) {

    }

    ImageView btnItemLeft;//header
    TextView txt_title;

    //nav
    ListView listCategoryPay;
    CategoryAdapter categoryAdapter;

    ListView listService;
    ServiceAdapter serviceAdapter;

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, R.layout.service_fragment);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //header
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText("Dịch vụ vệ sinh");
        btnItemLeft = (ImageView) findViewById(R.id.btnItemLeft);
        btnItemLeft.setImageResource(R.drawable.menu_icon);
        btnItemLeft.setVisibility(View.VISIBLE);

        //nav diều hướng
        listCategoryPay = (ListView) findViewById(R.id.listCategoryPay);
        listCategoryPay.setVisibility(View.GONE);
        categoryAdapter = new CategoryAdapter(getActivity());
        listCategoryPay.setAdapter(categoryAdapter);
        displayCategory();

        btnItemLeft.setOnClickListener(this);

        listService = (ListView) findViewById(R.id.listService);
        serviceAdapter = new ServiceAdapter(getActivity());
        listService.setAdapter(serviceAdapter);
        displayListService();
    }

    private void displayCategory() {
        categoryAdapter.add("Dịch vụ vệ sinh");
        categoryAdapter.add("Dịch vụ Massage");
        categoryAdapter.add("Dịch vụ Gái Gọi");
    }

    private void displayListService() {
        serviceAdapter.add(new ServiceEntity("Dịch vụ vệ sinh","SHINY CLEAN","I know this might be a stupid question but what language are you using? Java or Javascript?\n" +
                "Where can i learn Java",""));
        serviceAdapter.add(new ServiceEntity("Dịch vụ vệ sinh","SHINY CLEAN","I know this might be a stupid question but what language are you using? Java or Javascript?\n" +
                "Where can i learn Java",""));
        serviceAdapter.add(new ServiceEntity("Dịch vụ vệ sinh","SHINY CLEAN","I know this might be a stupid question but what language are you using? Java or Javascript?\n" +
                "Where can i learn Java",""));
        serviceAdapter.add(new ServiceEntity("Dịch vụ vệ sinh","SHINY CLEAN","I know this might be a stupid question but what language are you using? Java or Javascript?\n" +
                "Where can i learn Java",""));
        serviceAdapter.add(new ServiceEntity("Dịch vụ vệ sinh","SHINY CLEAN","I know this might be a stupid question but what language are you using? Java or Javascript?\n" +
                "Where can i learn Java",""));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnItemLeft){
            if(listCategoryPay.getVisibility() == View.VISIBLE){
                listCategoryPay.setVisibility(View.GONE);
            }else {
                listCategoryPay.setVisibility(View.VISIBLE);
            }
        }
    }
}
