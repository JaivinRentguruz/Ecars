package com.rentguruz.app.b2b.model.reservation;

import com.rentguruz.app.b2b.model.response.ReservationOriginDataModels;

import java.util.ArrayList;

public class ReservationVehicleChange {

    public Boolean IsNoExtraCharge,SendNotificationToCustomer;
    public int ReservationId,VehicleId,VehicleTypeId;

    public ArrayList<ReservationOriginDataModels> ReservationOriginDataModels = new ArrayList<ReservationOriginDataModels>();
}
