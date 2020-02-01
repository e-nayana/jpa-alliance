package com.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_permit")
public class UserPermit {

    @Id
    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "permission_id")
    private String permissionId;

    public String getUserEmail() {
        return userEmail;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public String getUserId() {
        return userId;
    }

    public String getPermissionId() {
        return permissionId;
    }

}
