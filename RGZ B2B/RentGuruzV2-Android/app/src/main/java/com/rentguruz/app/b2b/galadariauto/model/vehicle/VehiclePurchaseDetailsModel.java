package com.rentguruz.app.b2b.galadariauto.model.vehicle;

import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.io.Serializable;

public class VehiclePurchaseDetailsModel extends BaseModel implements Serializable {

    public Double AmountMonthly,DownPayment,PreTaxAmount,TaxAmount,TotalAmount,CarPriceBeforeTax;

    public int AnnualMilesAllowed,ApprovedBy,PurchasedBy,VehicleId,VendorMasterId;

    public String BankName,ChequeNumber,CompanyName,DateOfPayment,Email,LeaseTermsMonth,MobileNo,PaidBy,PaymentFrequency;

    public Boolean Reminder;

}
