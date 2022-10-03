package com.rentguruz.app.b2b.model.reservation;

import com.rentguruz.app.b2b.model.base.BaseModel;

import java.io.Serializable;

public class ReservationVehicleType extends BaseModel implements Serializable {
    public int Id,RateId,TotalAvailableVehicle,NoOfBags,NoOfDoors,
            NoOfSeats,TotalMiles,TotalVehicle;
    public Double PerDayAmount,SecurityDeposit,TotalAmount;
    public String Name,Code,Description,OptionName,VehicleCategoryName,VehicleClassStandaredImagePath;
    public Boolean GetForReservation;

    public String TransmissionDesc,FuelTypeDesc;
}
