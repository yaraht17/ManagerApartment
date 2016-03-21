package com.ezbms.building.buildingapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.entity.ElectricAndWaterEntity;

import java.util.List;

/**
 * Created by Hoang on 3/18/2016.
 */
public class ElectricAndWaterAdapter extends MyArrayAdapter<ElectricAndWaterEntity> {
    public ElectricAndWaterAdapter(Context context) {
        super(context);
    }

    public ElectricAndWaterAdapter(Context context, List<ElectricAndWaterEntity> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.electtric_and_water_item, null);
            holder.txtMonth = (TextView) convertView.findViewById(R.id.txt_month);
            holder.txtMoney = (TextView) convertView.findViewById(R.id.txtMoney);
            holder.layout = (LinearLayout) convertView.findViewById(R.id.layout_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        switch (position%4){
            case 0:
                holder.layout.setBackgroundResource(R.drawable.do_bg);
                break;
            case 1:
                holder.layout.setBackgroundResource(R.drawable.xanh_bg);
                break;
            case 2:
                holder.layout.setBackgroundResource(R.drawable.vang_bg);
                break;
            case 3:
                holder.layout.setBackgroundResource(R.drawable.nau_bg);
                break;
        }
        holder.txtMoney.setText(getItem(position).getMoney()+" vnd");
        holder.txtMonth.setText("Tháng "+getItem(position).getMonth());
        return convertView;
    }

    public class ViewHolder {
        public TextView txtMonth;
        public TextView txtMoney;
        public LinearLayout layout;
    }
}
