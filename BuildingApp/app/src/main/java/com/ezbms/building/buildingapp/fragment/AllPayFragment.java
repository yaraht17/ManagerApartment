package com.ezbms.building.buildingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.adapter.BillAdapter;
import com.ezbms.building.buildingapp.entity.BillEntity;

import java.util.ArrayList;

/**
 * Created by Hoang on 3/18/2016.
 */
public class AllPayFragment extends MyFragment implements CompoundButton.OnCheckedChangeListener {

    ListView listAllBill;
    ArrayList<BillEntity> billEntities = new ArrayList<>();
    BillAdapter billAdapter;
    long allMoneyPay = 0;
    TextView txtAllMoney;
    Button btnPayAll;

    @Override
    public void refreshData(Intent intent) {

    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, R.layout.all_pay_fragment);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listAllBill = (ListView) findViewById(R.id.listAllBill);
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

    @Override
    public void onClick(View v) {

    }
}
