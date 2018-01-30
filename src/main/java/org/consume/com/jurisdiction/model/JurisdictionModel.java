package org.consume.com.jurisdiction.model;

import java.io.Serializable;

/**
 * 权限
 */
public class JurisdictionModel implements Serializable {
    //主键
    private String uuid;
    //权限名称
    private String juname;
    //权限url
    private String url;
    //权限类型
    private int type;
    //权限的父级
    private String parent;

    public JurisdictionModel() {
    }

    public JurisdictionModel(String uuid, String juname, String url, int type, String parent) {
        this.uuid = uuid;
        this.juname = juname;
        this.url = url;
        this.type = type;
        this.parent = parent;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getJuname() {
        return juname;
    }

    public void setJuname(String juname) {
        this.juname = juname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "JurisdictionModel{" +
                "uuid='" + uuid + '\'' +
                ", juname='" + juname + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", parent='" + parent + '\'' +
                '}';
    }
}
