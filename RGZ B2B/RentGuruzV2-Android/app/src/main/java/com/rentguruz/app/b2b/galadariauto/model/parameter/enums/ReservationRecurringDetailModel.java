package com.rentguruz.app.b2b.galadariauto.model.parameter.enums;

import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.io.Serializable;

public class ReservationRecurringDetailModel extends BaseModel implements Serializable {

    public int BillingCycle,Duration,ReservationId,RecurringType,Id;
    public Boolean IsNoReturnDate;

}
