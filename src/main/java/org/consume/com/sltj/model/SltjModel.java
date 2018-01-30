package org.consume.com.sltj.model;

import java.text.DecimalFormat;

/**
 * @table sltj_table
 */
public class SltjModel {
    private String uuid;
//    累计量
    private String v;
//    补水瞬时流量
    private String v1;
    private long dates;
    private String zdid;
//    1:水 2：电
    private String lx;
    //电单耗
    private double ddh;
    //分公司名称
    private String fgs;
    //建筑物类型
    private String jzwlx;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public long getDates() {
        return dates;
    }

    public void setDates(long dates) {
        this.dates = dates;
    }

    public String getZdid() {
        return zdid;
    }

    public void setZdid(String zdid) {
        this.zdid = zdid;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public double getDdh() {
        return ddh;
    }

    public void setDdh(double ddh) {
        DecimalFormat df   = new DecimalFormat("######0.00");
        String format = df.format(ddh);
        this.ddh = Double.parseDouble(format);
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getFgs() {
        return fgs;
    }

    public void setFgs(String fgs) {
        this.fgs = fgs;
    }

    public String getJzwlx() {
        return jzwlx;
    }

    public void setJzwlx(String jzwlx) {
        this.jzwlx = jzwlx;
    }

    public SltjModel() {
        super();
    }

    public SltjModel(String uuid, String v, long dates, String zdid, String lx, double ddh) {
        this.uuid = uuid;
        this.v = v;
        this.dates = dates;
        this.zdid = zdid;
        this.lx = lx;
        this.ddh = ddh;
    }

    public SltjModel(String uuid, String v, String v1, long dates, String zdid, String lx, double ddh) {
        this.uuid = uuid;
        this.v = v;
        this.v1 = v1;
        this.dates = dates;
        this.zdid = zdid;
        this.lx = lx;
        this.ddh = ddh;
    }

    public SltjModel(String uuid, String v, String v1, long dates, String zdid, String lx, double ddh, String fgs, String jzwlx) {
        this.uuid = uuid;
        this.v = v;
        this.v1 = v1;
        this.dates = dates;
        this.zdid = zdid;
        this.lx = lx;
        this.ddh = ddh;
        this.fgs = fgs;
        this.jzwlx = jzwlx;
    }
}
