package com.ezbms.building.buildingapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.entity.Item;

import java.util.List;

/**
 * Created by Hoang on 3/9/2016.
 */
public class ItemAdapter extends MyArrayAdapter<Item> {
    public ItemAdapter(Context context) {
        super(context);
    }

    public ItemAdapter(Context context, List<Item> objects) {
        super(context, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_item, null);
            holder.txtTitleItem = (TextView) convertView.findViewById(R.id.txt_titleItem);
            holder.txtCountItem = (TextView) convertView.findViewById(R.id.txt_countItem);
            holder.imgIconItem = (ImageView) convertView.findViewById(R.id.imgIconItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtTitleItem.setText(getItem(position).getTitleItem());
        if(getItem(position).getCountItem()>0)
                holder.txtCountItem.setText(getItem(position).getCountItem()+"");
        else
                holder.txtCountItem.setVisibility(View.GONE);
        holder.imgIconItem.setImageResource(getItem(position).getIconItem());
        return convertView;
    }

    public class ViewHolder {
        public TextView txtTitleItem;
        public TextView txtCountItem;
        public ImageView imgIconItem;
    }
}