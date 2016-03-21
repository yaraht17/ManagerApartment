package com.ezbms.building.buildingapp.entity;

/**
 * Created by Hoang on 3/19/2016.
 */
public class InternalMailboxEntity extends MyEntity {
    String nameMail;
    String des;
    String avata;

    public InternalMailboxEntity(String nameMail, String des, String avata) {
        this.nameMail = nameMail;
        this.des = des;
        this.avata = avata;
    }

    public String getNameMail() {
        return nameMail;
    }

    public String getDes() {
        return des;
    }

    public String getAvata() {
        return avata;
    }
}
