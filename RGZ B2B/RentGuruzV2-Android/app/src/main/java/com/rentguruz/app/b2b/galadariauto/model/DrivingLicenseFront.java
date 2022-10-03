package com.rentguruz.app.b2b.galadariauto.model;

import com.rentguruz.app.b2b.galadariauto.model.base.BaseModel;

import java.io.Serializable;

public class DrivingLicenseFront extends BaseModel implements Serializable {

    public String AttachmentPath,Filename,FileType;

    public int Id,AttachmentType,AttachmentFor,FileUploadMasterId;
    public Boolean IsDeleteOld,IsTempFile,IsWithOutBaseUrl;
}
