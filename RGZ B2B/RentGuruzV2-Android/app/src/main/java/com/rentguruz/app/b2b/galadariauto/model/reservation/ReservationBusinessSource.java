package com.rentguruz.app.b2b.galadariauto.model.reservation;

import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.io.Serializable;
import java.util.ArrayList;

public class ReservationBusinessSource extends BaseModel implements Serializable {
    public String Name,ReferralName;
    public Boolean IsReferralSelect,IsRateSelect;
    public int ReferralId;

    public String Code,RateName,ReservationTypeName;

    public int ReservationTypeId,ReservationMainType,RateId,DefaultLocationId,DefaultVehicleType,InsuraceCoverId,Id;


    public String LocationMasterIds,VehicleTypeMasterIds;
}
