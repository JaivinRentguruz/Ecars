package com.rentguruz.app.b2b.galadariauto.model.insert;

import androidx.annotation.NonNull;

import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class TrafficTickets extends BaseModel implements Serializable {
    public int Id,ReservationId,VehicleId;

    public String Name,Notes,OffenseCode,TicketDate;

    public Double Surcharge,TicketCharges,TotalPayable;

    public Boolean ChargeCustomer,IsTrasferTicketToDriver;

}
