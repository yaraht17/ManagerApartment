package com.ezbms.building.buildingapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.activity.MainActivity;
import com.ezbms.building.buildingapp.adapter.ContactOnlineAdapter;
import com.ezbms.building.buildingapp.adapter.InternalMailBoxAdapter;
import com.ezbms.building.buildingapp.entity.ContactOnlineEntity;
import com.ezbms.building.buildingapp.entity.InternalMailboxEntity;

/**
 * Created by Hoang on 3/19/2016.
 */
public class ContactOnlineFragment extends MyFragment {
    SwipeMenuListView listView;
    ContactOnlineAdapter adapter;

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
        return createView(inflater,container, R.layout.contact_online_fragment);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //header
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText("LIÊN HỆ TRỰC TUYẾN");
        btnItemLeft = (ImageView) findViewById(R.id.btnItemLeft);
        btnItemLeft.setImageResource(R.drawable.back);
        btnItemLeft.setVisibility(View.VISIBLE);
        btnItemLeft.setOnClickListener(this);

        btnItemRight = (ImageButton) findViewById(R.id.btnItemRight);
        btnItemRight.setImageResource(R.drawable.add);
        btnItemRight.setVisibility(View.VISIBLE);
        btnItemRight.setBackgroundColor(getResources().getColor(R.color.trans));
        btnItemRight.setOnClickListener(this);


        listView = (SwipeMenuListView) findViewById(R.id.listView);
        initListView();
        adapter = new ContactOnlineAdapter(getActivity());
        adapter.add(new ContactOnlineEntity("Nguyễn Minh Quang","01659544034","Ban Quản Lí Tòa Nhà","abdc"));
        adapter.add(new ContactOnlineEntity("Nguyễn Minh Quang","01659544034","Ban Quản Lí Tòa Nhà","abdc"));
        adapter.add(new ContactOnlineEntity("Nguyễn Minh Quang","01659544034","Ban Quản Lí Tòa Nhà","abdc"));
        adapter.add(new ContactOnlineEntity("Nguyễn Minh Quang","01659544034","Ban Quản Lí Tòa Nhà","abdc"));
        adapter.add(new ContactOnlineEntity("Nguyễn Minh Quang","01659544034","Ban Quản Lí Tòa Nhà","abdc"));
        adapter.add(new ContactOnlineEntity("Nguyễn Minh Quang","01659544034","Ban Quản Lí Tòa Nhà","abdc"));
        listView.setAdapter(adapter);
    }

    private void initListView() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem editItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                editItem.setBackground(R.drawable.reply_bg);
                // set item width
                editItem.setWidth(dp2px(90));
                // set item title
                editItem.setTitle("Reply");

                editItem.setIcon(R.drawable.reply_icon);
                // set item title fontsize
                editItem.setTitleSize(15);
                // set item title font color
                editItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(editItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                deleteItem.setBackground(R.drawable.del_bg);
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.del_icon);

                deleteItem.setTitleSize(15);
                deleteItem.setTitle("Delete");
                deleteItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        break;
                    case 1:
                        // delete
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        // Left
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
    }

    private int dp2px(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (dp * scale + 0.5f);
        return pixels;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnItemLeft){
            getActivity().getSupportFragmentManager().popBackStack();
        }else if(v.getId() == R.id.btnItemRight){
            ((MainActivity) getActivity()).replaceFragment(new AddNewContactFragment(),R.id.frameMain, true);
        }
    }
}
