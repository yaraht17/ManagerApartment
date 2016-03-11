package com.ezbms.building.buildingapp.entity;

import org.json.JSONObject;

/**
 * Created by Hoang on 3/9/2016.
 */
public class BillEntity extends MyEntity {
    String nameBill;
    String moneyBill;
    boolean isPayBill;
    boolean isChecked;

    public BillEntity(JSONObject jsonObject) {
        super(jsonObject);
    }

    public BillEntity(String nameBill, String moneyBill, boolean isPayBill) {
        this.nameBill = nameBill;
        this.moneyBill = moneyBill;
        this.isPayBill = isPayBill;
    }

    public String getNameBill() {
        return nameBill;
    }

    public String getMoneyBill() {
        return moneyBill;
    }

    public boolean getIsPayBill() {
        return isPayBill;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
