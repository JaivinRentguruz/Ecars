package com.rentguruz.app.b2b.model.response;

import java.io.Serializable;

public class AttachmentsModel implements Serializable {
    public int Id,AttachmentType,AttachmentFor,FileUploadMasterId;
    public String Filename,FileType,AttachmentPath;
    public Boolean IsActive,IsDeleteOld,IsTempFile,IsWithOutBaseUrl;
}
