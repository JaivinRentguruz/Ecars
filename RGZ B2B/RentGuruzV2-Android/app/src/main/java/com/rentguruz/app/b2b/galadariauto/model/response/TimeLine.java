package com.rentguruz.app.b2b.galadariauto.model.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TimeLine implements Serializable {
   public String Datetime;
   public List<TimelineData> items = new ArrayList<>();

}
