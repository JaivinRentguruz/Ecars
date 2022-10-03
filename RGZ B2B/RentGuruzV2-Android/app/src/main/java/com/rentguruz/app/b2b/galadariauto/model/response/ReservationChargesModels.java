package com.rentguruz.app.b2b.galadariauto.model.response;

import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.io.Serializable;

public class ReservationChargesModels extends BaseModel implements Serializable {
    public int ChargeFor,Amount,AmountType,ChargeType;
    public Boolean IsTaxable;
}
