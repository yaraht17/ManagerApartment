package com.ezbms.building.buildingapp.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ezbms.building.buildingapp.api.ApiConstants;
import com.ezbms.building.buildingapp.util.AdapterListener;
import com.ezbms.building.buildingapp.util.AppConstants;
import com.ezbms.building.buildingapp.util.LoadDataMode;


/**
 * Created by Hoang on 2/27/2016.
 */
public class MyArrayAdapter<T> extends BaseAdapter implements AppConstants, ApiConstants {

    protected Context context;
    protected View emptyView;
    public ArrayList<T> listData = new ArrayList<T>();
    protected LoadDataMode mode = LoadDataMode.DEFAULT;
    public AdapterListener adapterListener;

    public MyArrayAdapter(Context context) {
        super();
        this.context = context;
    }

    public MyArrayAdapter(Context context, T[] objects) {
        super();
        this.context = context;
        this.listData = new ArrayList<T>(Arrays.asList(objects));
        notifyDataSetChanged();
    }

    public MyArrayAdapter(Context context, List<T> objects) {
        super();
        this.context = context;
        this.listData = new ArrayList<T>(objects);
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setAdapterListener(AdapterListener listener) {
        this.adapterListener = listener;
    }

    public AdapterListener getAdapterListener() {
        return adapterListener;
    }


//them view load(khi data chay den cuoi)
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    public View getEmptyView() {
        return emptyView;
    }

    public boolean isEmptyViewShow() {
        return (emptyView != null && emptyView.isShown());
    }

    public void showEmptyView(boolean show) {
        if (emptyView != null) {
            emptyView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public void clear() {
        listData.clear();
        notifyDataSetChanged();
    }

    public void add(T object) {
        listData.add(object);
        notifyDataSetChanged();
    }

    public void add(T object, int position) {
        listData.add(position, object);
        notifyDataSetChanged();
    }

    public ArrayList<T> getAllData() {
        return listData;
    }

    public void remove(T object) {
        listData.remove(object);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        remove(getItem(position));
    }

    public void addAll(Collection<? extends T> collection) {
        if (collection.size() > 0) {
            listData.addAll(collection);
        }
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends T> collection, boolean clear) {
        if (clear) {
            clear();
        }
        addAll(collection);
    }

    public void insert(T object, int position) {
        listData.add(position, object);
        notifyDataSetChanged();
    }

    public void setMode(LoadDataMode mode) {
        this.mode = mode;
    }

    public LoadDataMode getMode() {
        return mode;
    }

    public void receiveData(List<T> objects) {
        if (mode == LoadDataMode.REFRESH) {
            showEmptyView(false);
            addAll(objects, true);
            showEmptyView(getCount() == 0);
        } else if (mode == LoadDataMode.NEXT_PAGE) {
            if (objects.size() > 0) {
                showEmptyView(false);
                addAll(objects, false);
            }
        }
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public T getItem(int position) {
        if (position >= 0 && position < listData.size()) {
            return listData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}
