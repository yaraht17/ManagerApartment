package com.ezbms.building.buildingapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;

/**
 * Created by Hoang on 3/10/2016.
 */
//listview dieu huong khi click vao menu
public class CategoryAdapter extends MyArrayAdapter<String> {
    public CategoryAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.category_item, null);
            holder.txt_nameCategory = (TextView) convertView.findViewById(R.id.txt_nameCategory);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_nameCategory.setText(getItem(position));
        return convertView;
    }

    public class ViewHolder {
        public TextView txt_nameCategory;
    }
}
