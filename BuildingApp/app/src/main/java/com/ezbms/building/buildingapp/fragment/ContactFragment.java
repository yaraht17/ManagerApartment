package com.ezbms.building.buildingapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.activity.MainActivity;
import com.ezbms.building.buildingapp.adapter.ItemAdapter;
import com.ezbms.building.buildingapp.entity.Item;

import java.util.ArrayList;

/**
 * Created by Hoang on 3/9/2016.
 */
public class ContactFragment extends MyFragment {
    @Override
    public void refreshData(Intent intent) {

    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    ArrayList<Item> items;
    ItemAdapter adapter;
    GridView gridView;
    private int columnWidth = 100;

    TextView txt_title;//header

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, R.layout.contact_fragment);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        txt_title = (TextView) findViewById(R.id.txt_title);//header
        txt_title.setText("LIÊN HỆ");

        items = new ArrayList<>();
        gridView = (GridView) findViewById(R.id.gridView);
        InitilizeGridLayout();
        //them du lieu

        adapter = new ItemAdapter(getActivity());
        gridView.setAdapter(adapter);
        //them du lieu bang ham sau
        adapter.add(new Item(R.mipmap.mail,10,"HÒM THƯ"));
        adapter.add(new Item(R.mipmap.chat_icon,0,"CHAT ONLINE"));
        adapter.add(new Item(R.mipmap.lien_he_truc_tuyen,0,"LIÊN HỆ"));
        adapter.add(new Item(R.mipmap.yeu_cau_icon,0,"YÊU CẦU"));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        ((MainActivity) getActivity()).replaceFragment(new InternalMailboxFragment(),R.id.frameMain, true);
                        break;
                    case 1:
                        break;
                    case 2:
                        ((MainActivity) getActivity()).replaceFragment(new ContactOnlineFragment(),R.id.frameMain, true);
                        break;
                    case 3:
                        ((MainActivity) getActivity()).replaceFragment(new RequestFragment(),R.id.frameMain, true);
                        break;
                }
            }
        });

    }

    /**
     * Method to calculate the grid dimensions Calculates number columns and
     * columns width in grid
     * */
    private void InitilizeGridLayout() {
        Resources r = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                8, r.getDisplayMetrics());

        columnWidth = (int) ((getScreenWidth() - ((2 + 1) * padding)) / 2);

        gridView.setNumColumns(2);
        gridView.setColumnWidth(columnWidth);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setPadding((int) padding, (int) padding, (int) padding,
                (int) padding);
        gridView.setHorizontalSpacing((int) padding);
        gridView.setVerticalSpacing((int) padding);
    }

    public int getScreenWidth() {
        int columnWidth;
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (java.lang.NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth = point.x;
        return columnWidth;
    }

    @Override
    public void onClick(View v) {

    }
}
