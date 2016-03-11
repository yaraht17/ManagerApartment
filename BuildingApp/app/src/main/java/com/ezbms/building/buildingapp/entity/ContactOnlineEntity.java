package com.ezbms.building.buildingapp.entity;

import org.json.JSONObject;

/**
 * Created by Hoang on 3/9/2016.
 */
public class ContactOnlineEntity extends MyEntity {
    String nameContactOnline;
    String phoneContactOnline;
    String postionContactOnline;
    String imgContactOnline;

    public ContactOnlineEntity(String nameContactOnline, String phoneContactOnline, String postionContactOnline, String imgContactOnline) {
        this.nameContactOnline = nameContactOnline;
        this.phoneContactOnline = phoneContactOnline;
        this.postionContactOnline = postionContactOnline;
        this.imgContactOnline = imgContactOnline;
    }

    public ContactOnlineEntity(JSONObject jsonObject) {
        super(jsonObject);
    }

    public String getNameContactOnline() {
        return nameContactOnline;
    }

    public String getPhoneContactOnline() {
        return phoneContactOnline;
    }

    public String getPostionContactOnline() {
        return postionContactOnline;
    }

    public String getImgContactOnline() {
        return imgContactOnline;
    }
}
