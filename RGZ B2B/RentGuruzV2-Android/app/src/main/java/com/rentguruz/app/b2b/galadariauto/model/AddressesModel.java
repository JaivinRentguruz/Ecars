package com.rentguruz.app.b2b.galadariauto.model;

import androidx.databinding.Observable;

import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.io.Serializable;

public class AddressesModel extends BaseModel implements Serializable, Observable {
    public String Street, UnitNo, City, ZipCode,  StateName, CountryName;
    public int AddressType, AddressFor, CountryId, StateId, ZoneId;

    public String Drivername, Fname,Lname;
    public int Id;

    public String Latitude, Longitude;
    public String PreviewAddress;


}
