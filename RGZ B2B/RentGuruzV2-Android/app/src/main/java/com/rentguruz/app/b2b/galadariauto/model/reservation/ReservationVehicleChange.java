package com.rentguruz.app.b2b.galadariauto.model.reservation;

import com.rentguruz.app.b2b.galadariauto.model.response.ReservationOriginDataModels;

import java.util.ArrayList;

public class ReservationVehicleChange {

    public Boolean IsNoExtraCharge,SendNotificationToCustomer;
    public int ReservationId,VehicleId,VehicleTypeId;

    public ArrayList<ReservationOriginDataModels> ReservationOriginDataModels = new ArrayList<ReservationOriginDataModels>();
}
