package com.ezbms.building.buildingapp.activity;

/**
 * Created by Hoang on 2/27/2016.
 */
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.api.ApiConstants;
import com.ezbms.building.buildingapp.util.AppConstants;

public abstract class MyActivity extends AppCompatActivity implements AppConstants, ApiConstants, OnClickListener{

    public ProgressDialog loadingDialog;
    public MyActivity activity;
    public boolean broadcastRegistered = false;
    public FragmentManager fragmentManager;

    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String classNames = intent.getStringExtra(KEY_CLASS_NAME);
            if(action.equals(ACTION_REFRESH) &&
                    classNames != null &&
                    classNames.contains(getClassName())){
                refreshData(intent);
            }
        }
    };

    public abstract void refreshData(Intent intent);
    public abstract String getClassName();

    public void setEnableReceiver(){
        IntentFilter filter = new IntentFilter(ACTION_REFRESH);
        registerReceiver(receiver, filter);
        broadcastRegistered = true;
    }

    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        activity = this;
        fragmentManager = getSupportFragmentManager();
    }


    @Override
    protected void onDestroy() {
        if(broadcastRegistered){
            unregisterReceiver(receiver);
        }
        super.onDestroy();
    }

    public void showLoadingDialog(){
        showLoadingDialog(R.string.loading);
    }

    public void showLoadingDialog(int mesId){
        showLoadingDialog(getString(mesId));
    }

    public void showLoadingDialog(String mes){
        if(activity == null || activity.isFinishing()){
            return;
        }
        try{
            if(loadingDialog == null){
                loadingDialog = new ProgressDialog(activity);
            }
            loadingDialog.setMessage(mes);
            if(!loadingDialog.isShowing()){
                loadingDialog.show();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void dismissLoadingDialog(){
        if(loadingDialog != null && loadingDialog.isShowing()){
            loadingDialog.cancel();
        }
    }

    public boolean isLoading(){
        if(loadingDialog == null){
            return false;
        }
        return loadingDialog.isShowing();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
    }

    public void showToast(int stringId){
        Toast.makeText(activity, stringId, Toast.LENGTH_SHORT).show();
    }

    public void showToast(String text){
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }

    //thay the fragment neu co
    public void replaceFragment(Fragment fragment, int frameId,
                                boolean addToBackStack) {
        if (fragmentManager != null && fragmentManager.getFragments() != null
                && fragmentManager.getFragments().contains(fragment)) {
            return;
        }
        try {
            if (addToBackStack) {
                fragmentManager.beginTransaction()
                        .replace(frameId, fragment, null).addToBackStack(null)
                        .commitAllowingStateLoss();
            } else {
                fragmentManager.beginTransaction()
                        .replace(frameId, fragment, null)
                        .commitAllowingStateLoss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*//su dung khi co nhieu fragment thi no quay lai tat ca cac fragment sau do moi den ham onPressButton trong activity
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (fragmentManager.getBackStackEntryCount() != 0) {
                fragmentManager.popBackStack();
                return true;
            }
            // If there are no fragments on stack perform the original back
            // button event
        }
        return super.onKeyDown(keyCode, event);
    }*/
}
