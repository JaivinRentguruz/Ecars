package com.rentguruz.app.b2b.galadariauto.model.display;

import com.rentguruz.app.b2b.galadariauto.model.AttachmentsModel;
import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.util.ArrayList;

public class SplashScreen extends BaseModel {

    public int Id;
    public String ContentType;
    public String DetailJson;
    public ArrayList<AttachmentsModel> AttachmentsModels = new ArrayList<>();
    public ArrayList<CMSManagementModels> CMSManagementModels = new ArrayList<>();
}
