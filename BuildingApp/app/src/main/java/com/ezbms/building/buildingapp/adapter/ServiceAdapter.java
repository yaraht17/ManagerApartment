package com.ezbms.building.buildingapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.entity.ServiceEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoang on 3/9/2016.
 */
public class ServiceAdapter extends MyArrayAdapter<ServiceEntity> {
    public ServiceAdapter(Context context) {
        super(context);
    }

    public ServiceAdapter(Context context, List<ServiceEntity> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.service_item, null);
            holder.txt_nameService = (TextView) convertView.findViewById(R.id.txt_nameService);
            holder.txt_desService = (TextView) convertView.findViewById(R.id.txt_desService);
            holder.imageViewService = (ImageView) convertView.findViewById(R.id.imageView);
            holder.btnContactService = (Button) convertView.findViewById(R.id.btnContactService);
            holder.btnRegisterService = (Button) convertView.findViewById(R.id.btnRegisterService);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_nameService.setText(getItem(position).getNameService());
        holder.txt_desService.setText(getItem(position).getDesService());
        holder.btnRegisterService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.btnContactService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*Picasso.with(context).load(R.drawable.img_clearer)
                .resize(120,120)
                .into(holder.imageViewService);*/

        return convertView;
    }

    public class ViewHolder {
        public TextView txt_nameService;
        public TextView txt_desService;
        public ImageView imageViewService;
        public Button btnRegisterService,btnContactService;
    }


}
