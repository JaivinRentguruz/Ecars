package com.rentguruz.app.b2b.galadariauto.model.vehicle;

import com.rentguruz.app.b2b.galadariauto.model.AddressesModel;
import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;
import com.rentguruz.app.b2b.galadariauto.model.response.AttachmentsModel;

import java.io.Serializable;

public class VehicleVendor extends BaseModel implements Serializable {

    public String Name,Telephone,Email , ContactPersonEmail,ContactPersonName,ContactPersonTelephone;
    public int Id,Type;

    public AddressesModel AddressesModel = new AddressesModel();
    public AttachmentsModel AttachmentsModel = new AttachmentsModel();
}
