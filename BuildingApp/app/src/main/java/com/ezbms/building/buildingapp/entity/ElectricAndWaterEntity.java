package com.ezbms.building.buildingapp.entity;

/**
 * Created by Hoang on 3/18/2016.
 */
public class ElectricAndWaterEntity extends MyEntity {
    int month;
    long money;

    public ElectricAndWaterEntity(int month, long money) {
        this.month = month;
        this.money = money;
    }

    public int getMonth() {
        return month;
    }

    public long getMoney() {
        return money;
    }
}
