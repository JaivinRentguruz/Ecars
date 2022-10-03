package com.rentguruz.app.b2b.galadariauto.model.common;

import java.io.Serializable;

public class DropDown implements Serializable {
    public int TableType,CompanyId;
    public Boolean IsActive,IsDefault;


    public DropDown(int tableType, int companyId, Boolean isActive, Boolean isDefault) {
        TableType = tableType;
        CompanyId = companyId;
        IsActive = isActive;
        IsDefault = isDefault;
    }
}
