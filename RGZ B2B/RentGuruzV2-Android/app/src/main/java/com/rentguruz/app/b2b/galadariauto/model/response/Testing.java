package com.rentguruz.app.b2b.galadariauto.model.response;

import java.io.Serializable;

public class Testing<T> implements Serializable {
    public Boolean Status;
    public String Message;
    public T Data;
}
