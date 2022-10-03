package com.rentguruz.app.b2b.galadariauto.model.reservation;

import java.io.Serializable;

public class ReservationNoteModel implements Serializable {

    public String ExternalNote,ExternalRemDate,InternalNote,InternalRemDate;
    public Boolean ExternalReminder,ExternalPrintOnAGR,ExternalSendSMS,InternalPrintOnAGR,InternalReminder,InternalSendSMS;
}
