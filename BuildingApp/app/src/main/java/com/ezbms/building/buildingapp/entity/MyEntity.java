package com.ezbms.building.buildingapp.entity;

/**
 * Created by Hoang on 2/27/2016.
 */
import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;


import android.text.TextUtils;

import com.ezbms.building.buildingapp.api.ApiConstants;
import com.ezbms.building.buildingapp.util.AppConstants;


public class MyEntity implements Serializable, AppConstants, ApiConstants {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public String jsonString = "";

    public MyEntity(){
    }

    public MyEntity(JSONObject jsonObject){
        if(jsonObject != null){
            jsonString = jsonObject.toString();
        }
    }

    public JSONObject getJsonObject(){
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public void setJsonObject(JSONObject jsonObject){
        if(jsonObject != null){
            jsonString = jsonObject.toString();
        }
        else{
            jsonString = "";
        }
    }

    public String getJsonString(){
        return jsonString;
    }

    public String optString(JSONObject jsonObject, String key){
        if(jsonObject == null){
            return "";
        }
        return parseString(jsonObject.optString(key, ""));
    }

    public int optInt(JSONObject jsonObject, String key, int defaultValue){
        if(jsonObject == null){
            return defaultValue;
        }
        return jsonObject.optInt(key, defaultValue);
    }

    public long optLong(JSONObject jsonObject, String key, long defaultValue){
        if(jsonObject == null){
            return defaultValue;
        }
        return jsonObject.optLong(key, defaultValue);
    }

    public double optDouble(JSONObject jsonObject, String key, double defaultValue){
        if(jsonObject == null){
            return defaultValue;
        }
        return jsonObject.optDouble(key, defaultValue);
    }

    public boolean optBoolean(JSONObject jsonObject, String key, boolean defaultValue){
        if(jsonObject == null){
            return defaultValue;
        }
        return jsonObject.optBoolean(key, defaultValue);
    }

    public String parseString(String s){
        if(TextUtils.isEmpty(s)){
            return "";
        }
        else if(s.toLowerCase().trim().equals("null")){
            return "";
        }
        return s.trim();
    }

    public int parseInt(String s, int defaultValue){
        try{
            return Integer.parseInt(s);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        return defaultValue;
    }

    public float parseFloat(String s, float defaultValue){
        try{
            return Float.parseFloat(s);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        return defaultValue;
    }

    public double parseDouble(String s, double defaultValue){
        try{
            return Double.parseDouble(s);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        return defaultValue;
    }

    public boolean parseBoolean(String s, boolean defaultValue){
        try{
            return Boolean.parseBoolean(s);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        return defaultValue;
    }

}
