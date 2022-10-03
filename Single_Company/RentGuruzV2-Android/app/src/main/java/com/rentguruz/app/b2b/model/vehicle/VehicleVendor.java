package com.rentguruz.app.b2b.model.vehicle;

import com.rentguruz.app.b2b.model.AddressesModel;
import com.rentguruz.app.b2b.model.base.BaseModel;
import com.rentguruz.app.b2b.model.response.AttachmentsModel;

import java.io.Serializable;

public class VehicleVendor extends BaseModel implements Serializable {

    public String Name,Telephone,Email , ContactPersonEmail,ContactPersonName,ContactPersonTelephone;
    public int Id,Type;

    public AddressesModel AddressesModel = new AddressesModel();
    public AttachmentsModel AttachmentsModel = new AttachmentsModel();
}
