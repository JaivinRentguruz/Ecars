package com.rentguruz.app.b2b.model.response;

import com.rentguruz.app.b2b.model.AddressesModel;
import com.rentguruz.app.b2b.model.base.BaseModel;

import java.io.Serializable;

public class LocationList extends BaseModel implements Serializable {
    public String Name,PhoneNo;
    public AddressesModel AddressesModel = new AddressesModel();
    public int Id;
}
