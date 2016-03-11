package com.ezbms.building.buildingapp.entity;

import org.json.JSONObject;

/**
 * Created by Hoang on 3/9/2016.
 */
public class ServiceEntity extends MyEntity {
    String typeService;
    String nameService;
    String desService;
    String imgService;

    public ServiceEntity(JSONObject jsonObject) {
        super(jsonObject);
    }

    public ServiceEntity(String typeService, String nameService, String desService, String imgService) {
        this.typeService = typeService;
        this.nameService = nameService;
        this.desService = desService;
        this.imgService = imgService;
    }

    public String getTypeService() {
        return typeService;
    }

    public String getNameService() {
        return nameService;
    }

    public String getDesService() {
        return desService;
    }

    public String getImgService() {
        return imgService;
    }
}
