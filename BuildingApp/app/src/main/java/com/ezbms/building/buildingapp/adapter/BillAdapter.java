package com.ezbms.building.buildingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.entity.BillEntity;
import com.ezbms.building.buildingapp.fragment.AllPayFragment;
import com.ezbms.building.buildingapp.fragment.PayFragment;

import java.util.List;

/**
 * Created by Hoang on 3/9/2016.
 */
public class BillAdapter extends ArrayAdapter<BillEntity> {
    AllPayFragment payFragment;

    private List<BillEntity> billEntities;
    private Context context;

    public BillAdapter(Context context, List<BillEntity> objects,AllPayFragment payFragment) {
        super(context, R.layout.bill_item, objects);
        this.billEntities = objects;
        this.context = context;
        this.payFragment = payFragment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        ViewHolder holder = new ViewHolder();
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.bill_item, null);

            holder.txt_nameBill = (TextView) v.findViewById(R.id.txt_nameBill);
            holder.txt_moneyBill = (TextView) v.findViewById(R.id.txt_moneyBill);
            holder.img_tickBill = (ImageView) v.findViewById(R.id.img_tickBill);
            holder.cb_checkbox = (CheckBox) v.findViewById(R.id.checkbox);

            holder.cb_checkbox.setOnCheckedChangeListener(payFragment);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.txt_nameBill.setText(getItem(position).getNameBill());
        holder.txt_moneyBill.setText(getItem(position).getMoneyBill());
        //kho nhat la o day bat 2 su kien trong 1 item trong apdater(1 la checkbox va 1 la click)
        if(getItem(position).getIsPayBill()){
            holder.cb_checkbox.setVisibility(View.GONE);
            holder.img_tickBill.setVisibility(View.VISIBLE);
        } else{
            holder.cb_checkbox.setVisibility(View.VISIBLE);
            holder.img_tickBill.setVisibility(View.GONE);
        }
        holder.cb_checkbox.setTag(getItem(position));
        return v;
    }

    private static class ViewHolder {
        public TextView txt_nameBill;
        public TextView txt_moneyBill;
        public ImageView img_tickBill;
        public CheckBox cb_checkbox;
    }
}
