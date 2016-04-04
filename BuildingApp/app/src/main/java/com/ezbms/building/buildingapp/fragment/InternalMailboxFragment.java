package com.ezbms.building.buildingapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.activity.MainActivity;
import com.ezbms.building.buildingapp.adapter.InternalMailBoxAdapter;
import com.ezbms.building.buildingapp.entity.InternalMailboxEntity;

/**
 * Created by Hoang on 3/19/2016.
 */
public class InternalMailboxFragment extends MyFragment {
    SwipeMenuListView listView;
    InternalMailBoxAdapter adapter;

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
        return createView(inflater, container, R.layout.internal_mail_box_fragment);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //header
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText("HÒM THƯ NỘI BỘ");
        btnItemLeft = (ImageView) findViewById(R.id.btnItemLeft);
        btnItemLeft.setImageResource(R.mipmap.back);
        btnItemLeft.setVisibility(View.VISIBLE);
        btnItemLeft.setOnClickListener(this);

        btnItemRight = (ImageButton) findViewById(R.id.btnItemRight);
        btnItemRight.setImageResource(R.mipmap.new_mail_icon);
        btnItemRight.setVisibility(View.VISIBLE);
        btnItemRight.setBackgroundColor(getResources().getColor(R.color.trans));
        btnItemRight.setOnClickListener(this);


        listView = (SwipeMenuListView) findViewById(R.id.listView);
        initListView();
        adapter = new InternalMailBoxAdapter(getActivity());
        adapter.add(new InternalMailboxEntity("Sửa Nhà","Tổng tiền 5.500.000 VND\nChi tiết công...","adbc"));
        adapter.add(new InternalMailboxEntity("Massage","Tổng tiền 5.500.000 VND\nChi tiết công...","adbc"));
        adapter.add(new InternalMailboxEntity("Đóng tiền nước","Tổng tiền 5.500.000 VND\nChi tiết công...","adbc"));
        adapter.add(new InternalMailboxEntity("Họp khu dân cư","Tổng tiền 5.500.000 VND\nChi tiết công...","adbc"));
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
                editItem.setBackground(R.mipmap.reply_bg);
                // set item width
                editItem.setWidth(dp2px(90));
                // set item title
                editItem.setTitle("Reply");

                editItem.setIcon(R.mipmap.reply_icon);
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
                deleteItem.setBackground(R.mipmap.del_bg);
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.mipmap.del_icon);

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
            ((MainActivity) getActivity()).replaceFragment(new AddNewMailFragment(),R.id.frameMain, true);
        }
    }
}
