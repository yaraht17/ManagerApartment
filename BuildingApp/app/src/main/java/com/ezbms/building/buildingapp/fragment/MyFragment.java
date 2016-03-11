package com.ezbms.building.buildingapp.fragment;

/**
 * Created by Hoang on 2/27/2016.
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezbms.building.buildingapp.api.ApiConstants;
import com.ezbms.building.buildingapp.util.AppConstants;


// TODO: Auto-generated Javadoc
/**
 * The Class MyFragment.
 */
public abstract class MyFragment extends Fragment implements AppConstants, ApiConstants,View.OnClickListener {

    /** The activity. */
    protected Activity activity;

    /** The fragment manager. */
    protected FragmentManager fragmentManager;
    protected LayoutInflater inflater;
    public boolean broadcastRegistered = false;


    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(ACTION_REFRESH)){
                refreshData(intent);
            }
        }
    };

    public void setEnableReceiver(){
        IntentFilter filter = new IntentFilter(ACTION_REFRESH);
        activity.registerReceiver(receiver, filter);
        broadcastRegistered = true;
    }

    /**
     * Instantiates a new my fragment.
     */
    public MyFragment(){
        super();
        setArguments(new Bundle());
    }

    /**
     * Creates the view.
     *
     * @param inflater the inflater
     * @param layoutId the layout id
     */
    public View createView(LayoutInflater inflater, ViewGroup container, int layoutId){
        return inflater.inflate(layoutId, container, false);
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onAttach(android.app.Activity)
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        this.fragmentManager = getFragmentManager();
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void onDestroyView() {
        if(broadcastRegistered){
            activity.unregisterReceiver(receiver);
        }
        super.onDestroyView();
    }

    /**
     * Find view by id.
     *
     * @param id the id
     * @return the view
     */
    public View findViewById(int id){
        return getView().findViewById(id);
    }

    /**
     * On back.
     */
    public void onBack(){
    }

    public abstract void refreshData(Intent intent);
    public abstract String getClassName();

    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

}

