package org.consume.com.qxxx.model;

import java.sql.Timestamp;

/**
 * @table qxxx_tq_table
 */
public class QxxxModel {
    private String uuid;
    //    省份
    private String sf;
    //    城市
    private String cs;
    //    区域
    private String qy;
    //    日期
    private Timestamp dates;
    //    最高温度
    private double zgwd;
    //    最低温度
    private double zdwd;
    //    最高湿度
    private double zgsd;
    //    最低湿度
    private double zdsd;
    //    最高压力
    private double zgyl;
    //    最低压力
    private double zdyl;
    //    风向
    private String fx;
    //    风级
    private String fj;
    //    天气
    private String tq;
    //    唯一标识
    private String bs;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getQy() {
        return qy;
    }

    public void setQy(String qy) {
        this.qy = qy;
    }

    public Timestamp getDates() {
        return dates;
    }

    public void setDates(Timestamp dates) {
        this.dates = dates;
    }

    public double getZgwd() {
        return zgwd;
    }

    public void setZgwd(double zgwd) {
        this.zgwd = zgwd;
    }

    public double getZdwd() {
        return zdwd;
    }

    public void setZdwd(double zdwd) {
        this.zdwd = zdwd;
    }

    public double getZgsd() {
        return zgsd;
    }

    public void setZgsd(double zgsd) {
        this.zgsd = zgsd;
    }

    public double getZdsd() {
        return zdsd;
    }

    public void setZdsd(double zdsd) {
        this.zdsd = zdsd;
    }

    public double getZgyl() {
        return zgyl;
    }

    public void setZgyl(double zgyl) {
        this.zgyl = zgyl;
    }

    public double getZdyl() {
        return zdyl;
    }

    public void setZdyl(double zdyl) {
        this.zdyl = zdyl;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public String getTq() {
        return tq;
    }

    public void setTq(String tq) {
        this.tq = tq;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public QxxxModel() {
        super();
    }

    public QxxxModel(String uuid, String sf, String cs, String qy, Timestamp dates, double zgwd, double zdwd, double zgsd, double zdsd, double zgyl, double zdyl, String fx, String fj, String tq, String bs) {
        this.uuid = uuid;
        this.sf = sf;
        this.cs = cs;
        this.qy = qy;
        this.dates = dates;
        this.zgwd = zgwd;
        this.zdwd = zdwd;
        this.zgsd = zgsd;
        this.zdsd = zdsd;
        this.zgyl = zgyl;
        this.zdyl = zdyl;
        this.fx = fx;
        this.fj = fj;
        this.tq = tq;
        this.bs = bs;
    }
}
