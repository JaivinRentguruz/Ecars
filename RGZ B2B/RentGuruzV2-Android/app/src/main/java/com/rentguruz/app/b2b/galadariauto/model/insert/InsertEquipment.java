package com.rentguruz.app.b2b.galadariauto.model.insert;

import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.util.ArrayList;

public class InsertEquipment extends BaseModel {

    public String Description, Make, Model, Name;
    public Boolean IsChargePerDay, UseMaxPrice;
    public Double MaxPrice, Price, ReplacementCost;

    public EquipmentDetailModels EquipmentDetailModels;
    public ArrayList<EquipmentTaxMappingModel> EquipmentTaxMappingModel = new ArrayList<EquipmentTaxMappingModel>();

}
