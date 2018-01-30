package org.consume.com.userJurisdiction.model;

import java.io.Serializable;

/*
* 角色权限关联关系
*
* */
public class UserJurisdictionModel implements Serializable {
    //主键
    private String uuid;
    //角色主键
    private String userId;
    //权限主键
    private String jurisdictionId;

    public UserJurisdictionModel(String uuid) {
        this.uuid = uuid;
    }

    public UserJurisdictionModel(String uuid, String userId, String jurisdictionId) {
        this.uuid = uuid;
        this.userId = userId;
        this.jurisdictionId = jurisdictionId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(String jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

    @Override
    public String toString() {
        return "UserJurisdictionModel{" +
                "uuid='" + uuid + '\'' +
                ", userId='" + userId + '\'' +
                ", jurisdictionId='" + jurisdictionId + '\'' +
                '}';
    }

    public UserJurisdictionModel() {
    }
}
