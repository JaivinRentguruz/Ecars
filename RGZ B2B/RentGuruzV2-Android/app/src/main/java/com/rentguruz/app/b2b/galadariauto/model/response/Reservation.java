package com.rentguruz.app.b2b.galadariauto.model.response;

import androidx.databinding.Observable;

import com.rentguruz.app.b2b.galadariauto.adapters.DateConvert;
import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;
import com.rentguruz.app.b2b.galadariauto.model.parameter.DateType;

import java.io.Serializable;

import kotlin.text._OneToManyTitlecaseMappingsKt;

public class Reservation  extends BaseModel implements Serializable {

    public String ReservationTypeName,ReservationNo,CustomerName,PickUpLocationName,DropLocationName,VehicleName;
    public int CustomerId,Id,PickUpLocation,DropLocation;
    //public int CompanyId;
    public String Email,MobileNo;

    public String CheckInDate,CheckOutDate,VehicleId,VehicleYear;
    public int ReservationStatus;

    public String VehicleNumber,VinNumber,LicenseNumber,VehicleImagePath;
    public String ReservationStatusDesc;
    //= DateConvert.DateConverter(DateType.fulldate,CheckInDate, DateType.yyyyMMddS);

    public int NoOfBags,NoOfDoors,NoOfSeats,Transmission;
    public String TransmissionDesc,VehicleTypeName;
}
