package com.ezbms.building.buildingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;

/**
 * Created by Hoang on 3/9/2016.
 */
public class AccountFragment extends MyFragment {
    ImageView imageView;
    TextView txt_nameAccount,txt_addressAccount,txt_phoneAccount,txt_emailAccount,txt_moreInforAccount;

    ImageButton btnItemRight;//header
    TextView txt_title;
    @Override
    public void refreshData(Intent intent) {

    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, R.layout.account_fragment);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageView = (ImageView) findViewById(R.id.imageView);
        txt_nameAccount = (TextView) findViewById(R.id.txt_nameAccount);
        txt_addressAccount = (TextView) findViewById(R.id.txt_addressAccount);
        txt_phoneAccount = (TextView) findViewById(R.id.txt_phoneAccount);
        txt_emailAccount = (TextView) findViewById(R.id.txt_emailAccount);
        txt_moreInforAccount = (TextView) findViewById(R.id.txt_moreInforAccount);
        //header
        btnItemRight = (ImageButton) findViewById(R.id.btnItemRight);
        btnItemRight.setVisibility(View.VISIBLE);
        btnItemRight.setImageResource(R.drawable.btn_setting_click);
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText("Thông tin cá nhân");
    }

    @Override
    public void onClick(View v) {

    }
}
