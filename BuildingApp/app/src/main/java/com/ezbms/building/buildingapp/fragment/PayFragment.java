package com.ezbms.building.buildingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.adapter.BillAdapter;
import com.ezbms.building.buildingapp.adapter.CategoryAdapter;
import com.ezbms.building.buildingapp.entity.BillEntity;

import java.util.ArrayList;

/**
 * Created by Hoang on 3/9/2016.
 */
public class PayFragment extends MyFragment implements CompoundButton.OnCheckedChangeListener {
    ListView listAllBill;
    ArrayList<BillEntity> billEntities = new ArrayList<>();
    BillAdapter billAdapter;
    long allMoneyPay = 0;
    TextView txtAllMoney;
    Button btnPayAll;

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
        listAllBill = (ListView) findViewById(R.id.listAllBill);
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText("Tất cả hóa đơn");
        btnItemLeft = (ImageView) findViewById(R.id.btnItemLeft);
        btnItemLeft.setImageResource(R.drawable.menu_icon);
        btnItemLeft.setVisibility(View.VISIBLE);
        btnItemLeft.setOnClickListener(this);

        //nav diều hướng
        listCategoryPay = (ListView) findViewById(R.id.listCategoryPay);
        listCategoryPay.setVisibility(View.GONE);
        categoryAdapter = new CategoryAdapter(getActivity());
        listCategoryPay.setAdapter(categoryAdapter);
        displayCategory();

        txtAllMoney = (TextView) findViewById(R.id.txtAllMoney);
        btnPayAll = (Button) findViewById(R.id.btnPayAll);
        txtAllMoney.setText("0 VND");
        displayAllBills();
    }

    private void displayAllBills() {
        billEntities.add(new BillEntity("Tiền Nhà", "200.000 VND", false));
        billEntities.add(new BillEntity("Tiền Điện","200.000 VND",false));
        billEntities.add(new BillEntity("Tiền Nước", "200.000 VND", true));
        billEntities.add(new BillEntity("Tiền Gửi Xe", "200.000 VND", false));
        billEntities.add(new BillEntity("Tiền Net","200.000 VND",true));
        billEntities.add(new BillEntity("Tiền Dịch Vụ", "200.000 VND", false));
        billAdapter = new BillAdapter(getActivity(),billEntities,this);
        listAllBill.setAdapter(billAdapter);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = listAllBill.getPositionForView(buttonView);
        if(pos != ListView.INVALID_POSITION){
            billEntities.get(pos).setIsChecked(isChecked);

            allMoneyPay = 0;
            for(int i=0;i<billEntities.size();i++)
                if(billEntities.get(i).isChecked())
                    allMoneyPay+=convertStringMoney(billEntities.get(i).getMoneyBill());

            txtAllMoney.setText(allMoneyPay+" VND");
        }
    }

    public long convertStringMoney(String money){
        long longMoney = 0;
        String s = money.replaceAll("[^\\d.]", "");
        s = s.replaceAll("\\.","");
        longMoney = Long.parseLong(s);
        return longMoney;
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
