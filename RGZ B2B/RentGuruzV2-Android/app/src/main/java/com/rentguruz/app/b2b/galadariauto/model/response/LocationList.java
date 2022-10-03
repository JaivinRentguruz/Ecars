package com.rentguruz.app.b2b.galadariauto.model.response;

import com.rentguruz.app.b2b.galadariauto.model.AddressesModel;
import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.io.Serializable;

public class LocationList extends BaseModel implements Serializable {
    public String Name,PhoneNo;
    public AddressesModel AddressesModel = new AddressesModel();
    public int Id,ParentLocationId;
}
