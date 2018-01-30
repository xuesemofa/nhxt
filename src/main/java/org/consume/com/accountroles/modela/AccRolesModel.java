package org.consume.com.accountroles.modela;

import java.io.Serializable;

/**
 * 账户角色关联关系
 */
public class AccRolesModel implements Serializable {
    //主键
    private String uuid;
    //角色主键
    private String roleId;
    //庄户主键
    private String accId;

    public AccRolesModel(String uuid, String roleId, String accId) {
        this.uuid = uuid;
        this.roleId = roleId;
        this.accId = accId;
    }

    public AccRolesModel() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    @Override
    public String toString() {
        return "AccRolesModel{" +
                "uuid='" + uuid + '\'' +
                ", roleId='" + roleId + '\'' +
                ", accId='" + accId + '\'' +
                '}';
    }
}
