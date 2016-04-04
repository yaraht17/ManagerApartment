package com.ezbms.building.buildingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.activity.MainActivity;

/**
 * Created by Hoang on 3/20/2016.
 */
public class AddNewMailFragment extends MyFragment {

    ImageView btnItemLeft;//header
    TextView txt_title;
    ImageButton btnItemRight;

    @Override
    public void refreshData(Intent intent) {

    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater,container, R.layout.add_new_email);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //header
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText("VIẾT MAIL MỚI");
        btnItemLeft = (ImageView) findViewById(R.id.btnItemLeft);
        btnItemLeft.setImageResource(R.mipmap.back);
        btnItemLeft.setVisibility(View.VISIBLE);
        btnItemLeft.setOnClickListener(this);

        btnItemRight = (ImageButton) findViewById(R.id.btnItemRight);
        btnItemRight.setImageResource(R.mipmap.send);
        btnItemRight.setVisibility(View.VISIBLE);
        btnItemRight.setBackgroundColor(getResources().getColor(R.color.trans));
        btnItemRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnItemLeft){
            getActivity().getSupportFragmentManager().popBackStack();
        }else if(v.getId() == R.id.btnItemRight){

        }
    }
}
