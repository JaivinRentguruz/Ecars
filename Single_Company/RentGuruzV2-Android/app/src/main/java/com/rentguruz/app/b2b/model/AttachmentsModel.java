package com.rentguruz.app.b2b.model;

import com.rentguruz.app.b2b.model.base.BaseModel;

import java.io.Serializable;

public class AttachmentsModel  extends BaseModel implements Serializable {
    public String AttachmentPath,Filename,FileType,AttachmentTypeName;

    public int Id,AttachmentType,AttachmentFor,FileUploadMasterId;
    public Boolean IsDeleteOld,IsTempFile,IsWithOutBaseUrl;

}
