package com.rentguruz.app.b2b.model.response;

import java.io.Serializable;

public class SeasonRatesModels  implements Serializable {

    public SeasonalRateModel SeasonalRateModel = new SeasonalRateModel();

    public Double DailyRate,HalfDayRate,HourlyRate,MonthlyRate,WeeklyRate;
}
