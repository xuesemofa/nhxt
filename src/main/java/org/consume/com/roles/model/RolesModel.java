package org.consume.com.roles.model;

import java.io.Serializable;

/**
 * 角色
 */
public class RolesModel implements Serializable {
    //主键
    private String uuid;
    //角色名称
    private String rolesName;

    public RolesModel() {
        super();
    }

    public RolesModel(String uuid, String rolesName) {
        this.uuid = uuid;
        this.rolesName = rolesName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    @Override
    public String toString() {
        return "RolesModel{" +
                "uuid='" + uuid + '\'' +
                ", rolesName='" + rolesName + '\'' +
                '}';
    }
}
