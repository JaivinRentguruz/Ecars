package com.rentguruz.app.b2b.galadariauto.model.display;

import com.rentguruz.app.b2b.galadariauto.model.AttachmentsModel;
import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.util.ArrayList;

public class MobileBasicDetails extends BaseModel {
    public int Id;
    public String ContentType;
    public String DetailJson;
    public ArrayList<AttachmentsModel> IconAttachmentsModels = new ArrayList<>();
    public ArrayList<AttachmentsModel> LogoAttachmentsModels = new ArrayList<>();
    public ArrayList<AttachmentsModel> CreditCardAttachmentModel = new ArrayList<>();

}
