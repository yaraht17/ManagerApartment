package com.ezbms.building.buildingapp.activity;

/**
 * Created by Hoang on 3/9/2016.
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
import android.view.View;
import android.widget.Toast;

import com.ezbms.building.buildingapp.R;
import com.ezbms.building.buildingapp.api.ApiConstants;
import com.ezbms.building.buildingapp.util.AppConstants;

/**
 * Created by Hoang on 12/26/2015.
 */
public abstract class MySlidingMenuActivity extends AppCompatActivity
        implements AppConstants, ApiConstants, View.OnClickListener {

    public ProgressDialog loadingDialog;
    public MySlidingMenuActivity activity;
    public FragmentManager fragmentManager;
    public boolean broadcastRegistered = false;

    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String classNames = intent.getStringExtra(KEY_CLASS_NAME);
            if (action.equals(ACTION_REFRESH) && classNames != null
                    && classNames.contains("" + getClassName())) {
                refreshData(intent);
            }
        }
    };

    public void setEnableReceiver() {
        IntentFilter filter = new IntentFilter(ACTION_REFRESH);
        registerReceiver(receiver, filter);
        broadcastRegistered = true;
    }

    @Override
    protected void onDestroy() {
        if (broadcastRegistered) {
            unregisterReceiver(receiver);
        }
        super.onDestroy();
    }

    public abstract void refreshData(Intent intent);

    public abstract String getClassName();


    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        activity = this;
        fragmentManager = getSupportFragmentManager();
    }

    public void showLoadingDialog() {
        showLoadingDialog(R.string.loading);
    }

    public void showLoadingDialog(int mesId) {
        showLoadingDialog(getString(mesId));
    }

    public void showLoadingDialog(String mes) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        try {
            if (loadingDialog == null) {
                loadingDialog = new ProgressDialog(activity);
            }
            loadingDialog.setMessage(mes);
            if (!loadingDialog.isShowing()) {
                loadingDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.cancel();
        }
    }

    public boolean isLoading() {
        if (loadingDialog == null) {
            return false;
        }
        return loadingDialog.isShowing();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void showToast(int stringId) {
        Toast.makeText(activity, stringId, Toast.LENGTH_SHORT).show();
    }

    public void showToast(String text) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }

    public void addFragment(Fragment fragment, int frameId,
                            boolean addToBackStack) {
        if (fragmentManager != null && fragmentManager.getFragments() != null
                && fragmentManager.getFragments().contains(fragment)) {
            return;
        }
        try {
            if (addToBackStack) {
                fragmentManager.beginTransaction().add(frameId, fragment, null)
                        .addToBackStack(null).commitAllowingStateLoss();
            } else {
                fragmentManager.beginTransaction().add(frameId, fragment, null)
                        .commitAllowingStateLoss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public void removeFragments() {
        try {
            fragmentManager.popBackStackImmediate(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction().addToBackStack(null).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

