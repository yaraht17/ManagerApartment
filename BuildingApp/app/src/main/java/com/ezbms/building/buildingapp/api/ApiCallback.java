package com.ezbms.building.buildingapp.api;

/**
 * Created by Hoang on 2/27/2016.
 */
public interface ApiCallback {
    public void onProgress(String api);
    public void onComplete(String api, String result, String extra);
}
