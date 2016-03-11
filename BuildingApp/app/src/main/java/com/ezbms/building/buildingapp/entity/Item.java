package com.ezbms.building.buildingapp.entity;

/**
 * Created by Hoang on 3/9/2016.
 */
public class Item {
    int iconItem;
    int countItem;
    String titleItem;

    public Item(int iconItem, int countItem, String titleItem) {
        this.iconItem = iconItem;
        this.countItem = countItem;
        this.titleItem = titleItem;
    }

    public int getIconItem() {
        return iconItem;
    }

    public int getCountItem() {
        return countItem;
    }

    public String getTitleItem() {
        return titleItem;
    }
}
