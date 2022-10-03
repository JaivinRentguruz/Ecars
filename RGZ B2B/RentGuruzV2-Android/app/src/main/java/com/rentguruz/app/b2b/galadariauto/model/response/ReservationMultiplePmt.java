package com.rentguruz.app.b2b.galadariauto.model.response;

import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.io.Serializable;
import java.util.ArrayList;

public class ReservationMultiplePmt implements Serializable {

    public ArrayList<ReservationPMT> ReservationPmt = new ArrayList<ReservationPMT>();
}
