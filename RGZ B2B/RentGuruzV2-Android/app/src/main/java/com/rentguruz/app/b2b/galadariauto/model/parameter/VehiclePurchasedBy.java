package com.rentguruz.app.b2b.galadariauto.model.parameter;

public enum VehiclePurchasedBy {

    Cash(1),
    Finance(2),
    Lease(3);


    public int inte;
    VehiclePurchasedBy(int i) {
        this.inte = i;
    }
    @Override
    public String toString() {
        return String.valueOf(inte);
    }

}
