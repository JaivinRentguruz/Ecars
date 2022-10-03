package com.rentguruz.app.b2b.galadariauto.model;

import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.io.Serializable;

public class BusinessSource extends BaseModel implements Serializable {
    public int Id,TableType;
    public String Name;
    public Boolean IsDefault;
}
