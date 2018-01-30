package org.consume.com.yh.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @table yh_table
 */
public class YhModel implements Serializable {
    //    uuid
    private String uuid;
    //    小区
    private String xq;
    //    楼号
    private String lh;
    //    用户名
    private String username;
    //    终端号
    private Integer zdbh;
    //    供热面积
    private Double mj;
    //    采集时间
    private Timestamp dates;
    //    户内温度值
    private Double hnwd;
    //    户内装置运行状况
    private String yxzk;
    //    分摊总热量
    private Double zrl;
    //    备注
    private String bz;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getLh() {
        return lh;
    }

    public void setLh(String lh) {
        this.lh = lh;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getZdbh() {
        return zdbh;
    }

    public void setZdbh(Integer zdbh) {
        this.zdbh = zdbh;
    }

    public Double getMj() {
        return mj;
    }

    public void setMj(Double mj) {
        this.mj = mj;
    }

    public Timestamp getDates() {
        return dates;
    }

    public void setDates(Timestamp dates) {
        this.dates = dates;
    }

    public Double getHnwd() {
        return hnwd;
    }

    public void setHnwd(Double hnwd) {
        this.hnwd = hnwd;
    }

    public String getYxzk() {
        return yxzk;
    }

    public void setYxzk(String yxzk) {
        this.yxzk = yxzk;
    }

    public Double getZrl() {
        return zrl;
    }

    public void setZrl(Double zrl) {
        this.zrl = zrl;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public YhModel() {
        super();
    }

    public YhModel(String uuid, String xq, String lh, String username, Integer zdbh, Double mj, Timestamp dates, Double hnwd, String yxzk, Double zrl, String bz) {
        this.uuid = uuid;
        this.xq = xq;
        this.lh = lh;
        this.username = username;
        this.zdbh = zdbh;
        this.mj = mj;
        this.dates = dates;
        this.hnwd = hnwd;
        this.yxzk = yxzk;
        this.zrl = zrl;
        this.bz = bz;
    }
}
