package com.ezbms.building.buildingapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.entity.ContactOnlineEntity;
import com.ezbms.building.buildingapp.util.AppUtil;

import java.util.List;

/**
 * Created by Hoang on 3/19/2016.
 */
public class ContactOnlineAdapter extends MyArrayAdapter<ContactOnlineEntity> {
    public ContactOnlineAdapter(Context context) {
        super(context);
    }

    public ContactOnlineAdapter(Context context, List<ContactOnlineEntity> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.contact_online_item, null);
            holder.txt_nameContactOnline = (TextView) convertView.findViewById(R.id.txt_nameContactOnline);
            holder.txt_positionContactOnline = (TextView) convertView.findViewById(R.id.txt_positionContactOnline);
            holder.txt_phoneContactOnline = (TextView) convertView.findViewById(R.id.txt_phoneContactOnline);
            holder.imageViewContactOnline = (ImageView) convertView.findViewById(R.id.imageView);
            holder.btnCall = (ImageView) convertView.findViewById(R.id.btnCall);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_nameContactOnline.setText(getItem(position).getNameContactOnline());
        holder.txt_positionContactOnline.setText("( " + getItem(position).getPostionContactOnline() + " )");
        holder.txt_phoneContactOnline.setText("Số điện thoại: " + getItem(position).getPhoneContactOnline());
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.getIntentCall("123456789");
            }
        });
        return convertView;
    }

    public class ViewHolder {
        public TextView txt_nameContactOnline,txt_positionContactOnline,txt_phoneContactOnline;
        public ImageView imageViewContactOnline,btnCall;
    }

}
