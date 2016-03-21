package com.ezbms.building.buildingapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.entity.NotiEntity;

import java.util.List;

/**
 * Created by Hoang on 3/19/2016.
 */
public class NotiAdapter extends MyArrayAdapter<NotiEntity> {
    public NotiAdapter(Context context) {
        super(context);
    }

    public NotiAdapter(Context context, List<NotiEntity> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.notification_item, null);
            holder.txt_title = (TextView) convertView.findViewById(R.id.txtTilteNoti);
            holder.txt_content = (TextView) convertView.findViewById(R.id.txtContentNoti);
            holder.txt_header = (TextView) convertView.findViewById(R.id.txtTitleDate);
            holder.lineNoti = (TextView) convertView.findViewById(R.id.lineNoti);
            holder.layoutHeader = (LinearLayout) convertView.findViewById(R.id.layout_headerNoti);
            holder.layoutContent = (LinearLayout) convertView.findViewById(R.id.layout_contentNoti);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(getItem(position).IsHeader()){
            holder.layoutHeader.setVisibility(View.VISIBLE);
            holder.layoutContent.setVisibility(View.GONE);
            holder.txt_header.setText(getItem(position).getTitle());
        }else {
            holder.layoutHeader.setVisibility(View.GONE);
            holder.layoutContent.setVisibility(View.VISIBLE);
            holder.txt_title.setText(getItem(position).getTitle());
            holder.txt_content.setText(getItem(position).getContent());
            if(position+1 < getCount()){
                if(getItem(position+1).IsHeader()){
                    holder.lineNoti.setVisibility(View.GONE);
                }
            }
        }

        return convertView;
    }

    public class ViewHolder {
        public TextView txt_title,txt_content,txt_header,lineNoti;
        public LinearLayout layoutHeader,layoutContent;
    }

}
