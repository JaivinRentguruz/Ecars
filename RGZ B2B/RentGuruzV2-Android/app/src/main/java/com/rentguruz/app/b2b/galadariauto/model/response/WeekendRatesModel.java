package com.rentguruz.app.b2b.galadariauto.model.response;

import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.io.Serializable;

public class WeekendRatesModel extends BaseModel implements Serializable {

    public int  TotalFriday, TotalSaturday, TotalSunday, TotalWeekendDays ;

    public Double DailyRate, FridayRate, HalfDayRate, HourlyRate, MonthlyRate, SaturdayRate, SundayRate,WeekendAverageRate,WeeklyRate;
}
