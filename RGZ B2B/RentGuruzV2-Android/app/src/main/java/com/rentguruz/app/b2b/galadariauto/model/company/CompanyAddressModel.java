package com.rentguruz.app.b2b.galadariauto.model.company;

import java.io.Serializable;

public class CompanyAddressModel   implements Serializable{
    public boolean IsActive;
    public String Street,UnitNo,City,ZipCode;
    public int CountryId,StateId;
    public Double Latitude,Longitude;
}
