package com.ezbms.building.buildingapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.entity.InternalMailboxEntity;

import java.util.List;

/**
 * Created by Hoang on 3/19/2016.
 */
public class InternalMailBoxAdapter extends MyArrayAdapter<InternalMailboxEntity> {
    public InternalMailBoxAdapter(Context context) {
        super(context);
    }

    public InternalMailBoxAdapter(Context context, List<InternalMailboxEntity> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.internal_mail_box_item, null);
            holder.txt_nameMail = (TextView) convertView.findViewById(R.id.txt_nameMail);
            holder.txt_desMail= (TextView) convertView.findViewById(R.id.txt_desMail);
            holder.imageViewMail = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_nameMail.setText(getItem(position).getNameMail());
        holder.txt_desMail.setText(getItem(position).getDes());
        return convertView;
    }

    public class ViewHolder {
        public TextView txt_nameMail;
        public TextView txt_desMail;
        public ImageView imageViewMail;
    }

}
