package com.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_permission")
public class UserPermission {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "permission_id")
    private Integer permissionId;

    public Integer getRoleId() {
        return userId;
    }

    public void setRoleId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

}
