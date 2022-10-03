package com.rentguruz.app.b2b.galadariauto.model.response;

import com.rentguruz.app.b2b.galadariauto.model.AddressesModel;

import java.io.Serializable;

public class User implements Serializable {
    public int CompanyId,UserType,UserFor,Id;
    public String UserName,Email;
    public Boolean IsSuperAdmin,IsAdmin;
    public AddressesModel addressesModel = new AddressesModel();
}
