package com.ezbms.building.buildingapp.entity;

/**
 * Created by Hoang on 3/19/2016.
 */
public class NotiEntity extends MyEntity {
    String title;
    String content;
    String time;
    boolean isHeader;

    public String getTime() {
        return time;
    }

    public boolean IsHeader() {
        return isHeader;
    }

    public NotiEntity(String title, String content,String time) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.isHeader = false;
    }

    public NotiEntity(String title) {
        this.title = title;
        this.isHeader = true;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
