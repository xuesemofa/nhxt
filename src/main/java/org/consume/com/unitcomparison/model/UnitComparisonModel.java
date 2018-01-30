package org.consume.com.unitcomparison.model;

import java.sql.Timestamp;

/**
 * @name 机组对比记录
 * @table unit_comparison_table
 */
public class UnitComparisonModel {
    //    id
    private String uuid;
    //机组名称
    private String jzname;
    //    机组Aid
    private String jzaid;
    //    机组Bid  ******* 在机组纵横对比中将这个字段用来保存对比时间节点 *******
    private String jzbid;
    //    生成时间
    private Timestamp scsj;
    //    生成人id
    private String accid;
    //    纵横类型  啥也不是：0  纵向：1   横向：2
    private Integer zhlx;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getJzaid() {
        return jzaid;
    }

    public void setJzaid(String jzaid) {
        this.jzaid = jzaid;
    }

    public String getJzbid() {
        return jzbid;
    }

    public void setJzbid(String jzbid) {
        this.jzbid = jzbid;
    }

    public Timestamp getScsj() {
        return scsj;
    }

    public void setScsj(Timestamp scsj) {
        this.scsj = scsj;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public Integer getZhlx() {
        return zhlx;
    }

    public void setZhlx(Integer zhlx) {
        this.zhlx = zhlx;
    }

    public String getJzname() {
        return jzname;
    }

    public void setJzname(String jzname) {
        this.jzname = jzname;
    }

    public UnitComparisonModel() {
        super();
    }

    public UnitComparisonModel(String uuid, String jzname, String jzaid, String jzbid, Timestamp scsj, String accid, Integer zhlx) {
        this.uuid = uuid;
        this.jzname = jzname;
        this.jzaid = jzaid;
        this.jzbid = jzbid;
        this.scsj = scsj;
        this.accid = accid;
        this.zhlx = zhlx;
    }
}
