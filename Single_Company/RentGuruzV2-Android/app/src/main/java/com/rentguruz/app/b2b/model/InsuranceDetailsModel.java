package com.rentguruz.app.b2b.model;

import com.rentguruz.app.b2b.model.base.BaseModel;

import java.io.Serializable;

public class InsuranceDetailsModel extends BaseModel implements Serializable {
    public int InsuranceType,InsuranceFor,InsuranceCompanyId,Id;
    public String PolicyNo,Description;
    public String IssueDate,ExpiryDate;
    public int Deductible,CoverLimit,VerifiedBy;
    public InsuranceCompanyDetailsModel InsuranceCompanyDetailsModel = new InsuranceCompanyDetailsModel();

    public Boolean GetCompanyDetail;
}
