package com.rentguruz.app.b2b.galadariauto.model.parameter;

public enum ReportType {

    AgreementPrint  (60),
    Refund   (2);

    public int inte;
    ReportType(int i) {
        this.inte = i;
    }
    @Override
    public String toString() {
        return String.valueOf(inte);
    }
}
