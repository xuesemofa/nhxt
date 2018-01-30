package org.consume.com.exchange.model;

import java.io.Serializable;

/**
 * 换热站集合工具类
 * 本类为查询所用，无表
 */
public class ExchangeModel implements Serializable {
    //    所属单位
    private String dw;
    //    所属站点
    private String zd;
    //    机组id
    private String jzid;
    //    机组
    private String jz;

    public ExchangeModel() {
        super();
    }

    public ExchangeModel(String dw, String zd, String jzid, String jz) {
        this.dw = dw;
        this.zd = zd;
        this.jzid = jzid;
        this.jz = jz;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getZd() {
        return zd;
    }

    public void setZd(String zd) {
        this.zd = zd;
    }

    public String getJzid() {
        return jzid;
    }

    public void setJzid(String jzid) {
        this.jzid = jzid;
    }

    public String getJz() {
        return jz;
    }

    public void setJz(String jz) {
        this.jz = jz;
    }

    @Override
    public String toString() {
        return "ExchangeModel{" +
                "dw='" + dw + '\'' +
                ", zd='" + zd + '\'' +
                ", jzid='" + jzid + '\'' +
                ", jz='" + jz + '\'' +
                '}';
    }
}
