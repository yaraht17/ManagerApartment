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

/**
 * Created by Hoang on 3/19/2016.
 */
public class RequestFragment extends MyFragment {
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
        return createView(inflater,container, R.layout.request_fragment);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //header
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText("YÊU CẦU");
        btnItemLeft = (ImageView) findViewById(R.id.btnItemLeft);
        btnItemLeft.setImageResource(R.drawable.back);
        btnItemLeft.setVisibility(View.VISIBLE);
        btnItemLeft.setOnClickListener(this);

        btnItemRight = (ImageButton) findViewById(R.id.btnItemRight);
        btnItemRight.setImageResource(R.drawable.send);
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
