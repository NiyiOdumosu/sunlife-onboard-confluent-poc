package org.sunlife.confluent.sunlifepoc.model.rbac;

import lombok.Data;

/*
More information on Confluent RBAC can be found here
https://docs.confluent.io/platform/current/security/rbac/rbac-predefined-roles.html
 */
@Data
public class RBAC {
    private SuperUser superUser;
    private SystemAdmin systemAdmin;
    private ClusterAdmin clusterAdmin;
    private UserAdmin userAdmin;
    private SecurityAdmin securityAdmin;
    private AuditAdmin auditAdmin;
    private Operator operator;
    private ResourceOwner resourceOwner;
    private DeveloperRead developerRead;
    private DeveloperWrite developerWrite;
    private DeveloperManage developerManage;

}
