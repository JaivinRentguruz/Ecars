package com.rentguruz.app.b2b.galadariauto.model.parameter;

public enum UserTypes {

    SuperAdmin(1),
    Admin(2),
    User(3),
    Customer(4);


    public int inte;
    UserTypes(int i) {
        this.inte = i;
    }
    @Override
    public String toString() {
        return String.valueOf(inte);
    }
}
