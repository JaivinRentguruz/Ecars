package com.rentguruz.app.b2b.galadariauto.model.vehicle;

import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.io.Serializable;

public class VehicleOtherDetailsModel extends BaseModel implements Serializable {

    public Double AdditionalExpenses;

    public String Description,MOTExpiryDate,Memo,ModelCode,PHVExpiryDate,PHVNumber,TaxDiscExpiryDate;

    public int RateId,VehicleId;
}
