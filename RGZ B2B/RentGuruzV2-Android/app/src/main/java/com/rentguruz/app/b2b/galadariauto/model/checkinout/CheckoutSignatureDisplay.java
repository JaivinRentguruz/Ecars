package com.rentguruz.app.b2b.galadariauto.model.checkinout;

import com.rentguruz.app.b2b.galadariauto.model.AttachmentsModel;
import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

public class CheckoutSignatureDisplay extends BaseModel {
    public int ReservationId, SignType;

   public AttachmentsModel AttachmentsModel = new AttachmentsModel();
}
