package com.rentguruz.app.b2b.model.reservation;

import java.io.Serializable;

public class ReservationFlightAndHotelModel implements Serializable {

    public String ArrFlightNo,DepFlightNo,HotelName,ArrFlightDate,DepFlightDate,PhoneNumber,FlightBookingReference,Remarks;

    public Boolean IsAirportDelivery,IsAirportDropOff,IsArrAirportShuttle,IsDepAirportShuttle;

    public int FlightPersonInCharge;
}
