package com.rentguruz.app.b2b.galadariauto.model.response;

import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.io.Serializable;

public class RateFeaturesModel extends BaseModel implements Serializable {
    public String Name;
    public Boolean IsUnlimitedMiles,IsDepositManDatory;
    public int GraceMinutes,OverUsageChagred,OverUsageChagredValue,PayNowDiscount,PayNowDiscountType,
            PrepaidTankCharge,TotalRecord,UpgradeCostAmount;

    public Double MonthlyMilesAllowed,DailyMilesAllowed,WeeklyMilesAllowed,ExtraMilesCharges,FuelCharge,SecurityDeposit;

    public int DetailId;
    public String DetailIds;
}
