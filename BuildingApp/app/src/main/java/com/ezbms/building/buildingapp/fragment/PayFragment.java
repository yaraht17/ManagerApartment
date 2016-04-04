package com.ezbms.building.buildingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.activity.MainActivity;
import com.ezbms.building.buildingapp.adapter.BillAdapter;
import com.ezbms.building.buildingapp.adapter.CategoryAdapter;
import com.ezbms.building.buildingapp.entity.BillEntity;

import java.util.ArrayList;

/**
 * Created by Hoang on 3/9/2016.
 */
public class PayFragment extends MyFragment {


    ImageView btnItemLeft;//header
    TextView txt_title;

    //nav
    ListView listCategoryPay;
    CategoryAdapter categoryAdapter;
    @Override
    public void refreshData(Intent intent) {

    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, R.layout.pay_fragment);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        txt_title = (TextView) findViewById(R.id.txt_title);
        btnItemLeft = (ImageView) findViewById(R.id.btnItemLeft);
        btnItemLeft.setImageResource(R.mipmap.menu_icon);
        btnItemLeft.setVisibility(View.VISIBLE);
        btnItemLeft.setOnClickListener(this);

        //nav diều hướng
        listCategoryPay = (ListView) findViewById(R.id.listCategoryPay);
        listCategoryPay.setVisibility(View.GONE);
        categoryAdapter = new CategoryAdapter(getActivity());
        listCategoryPay.setAdapter(categoryAdapter);
        //set click cho nav dieu huong
        listCategoryPay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listCategoryPay.setVisibility(View.GONE);
                selectCategory(position);

            }
        });
        displayCategory();
        //mac dinh cai cuoi dc bieu dien
        selectCategory(0);
    }

    private void selectCategory(int position) {
        switch (position){
            case 0:
                ((MainActivity) getActivity()).replaceFragment(new ElectricityAndWaterFragment(), R.id.frameMain1, false);
                txt_title.setText("Tiền Điện");
                break;
            case 1:
                txt_title.setText("Tiền Nước");
                ((MainActivity) getActivity()).replaceFragment(new ElectricityAndWaterFragment(), R.id.frameMain1, false);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                txt_title.setText("Tất Cả Hóa Đơn");
                ((MainActivity) getActivity()).replaceFragment(new AllPayFragment(), R.id.frameMain1, false);
                break;
        }

    }


    private void displayCategory() {
        categoryAdapter.add("Tiền Điện");
        categoryAdapter.add("Tiền Nước");
        categoryAdapter.add("Tiền Gửi Xe");
        categoryAdapter.add("Tiền Internet");
        categoryAdapter.add("Các cho phí khác");
        categoryAdapter.add("Tất cả hóa đơn");
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
