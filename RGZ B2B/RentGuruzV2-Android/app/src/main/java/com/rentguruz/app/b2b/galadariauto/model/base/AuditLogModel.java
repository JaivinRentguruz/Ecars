package com.rentguruz.app.b2b.galadariauto.model.base;

import java.io.Serializable;

public class AuditLogModel  implements Serializable {

    public int id,companyId,auditType,auditFor,createdBy,createdDate;

}
