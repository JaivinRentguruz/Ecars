package com.rentguruz.app.b2b.galadariauto.model;

import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.io.Serializable;

public class ReservationBusinessSource extends BaseModel implements Serializable {
    public String Name,Code,RateName,ReservationTypeName;

    public int ReservationTypeId,ReservationMainType,RateId,DefaultLocationId,DefaultVehicleType,InsuraceCoverId,Id;
}
