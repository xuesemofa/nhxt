package org.consume.com.xq.model;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 小区
 *
 * @table xq_table
 */
public class XqModel implements Serializable {
    //    uuid
    private String uuid;
    //    小区名称
    private String name;
    //    终端用户户数
    private Integer hs;
    //    热计量表编号
    private Integer bh;
    //    sim号
    private Integer sim;
    //    供热面积
    private Double mj;
    //    采集时间
    private Timestamp dates;
    //    供水温度
    private Double gw;
    //    回水温度
    private Double hw;
    //    总热量
    private Double zrl;
    //    热功率
    private Double rgv;
    //    瞬时流量
    private Double ssll;
    //    总流量
    private Double zll;
    //    单耗热量
    private Double dhrl;
    //    备注
    private String bz;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHs() {
        return hs;
    }

    public void setHs(Integer hs) {
        this.hs = hs;
    }

    public Integer getBh() {
        return bh;
    }

    public void setBh(Integer bh) {
        this.bh = bh;
    }

    public Integer getSim() {
        return sim;
    }

    public void setSim(Integer sim) {
        this.sim = sim;
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

    public Double getGw() {
        return gw;
    }

    public void setGw(Double gw) {
        this.gw = gw;
    }

    public Double getHw() {
        return hw;
    }

    public void setHw(Double hw) {
        this.hw = hw;
    }

    public Double getZrl() {
        return zrl;
    }

    public void setZrl(Double zrl) {
        this.zrl = zrl;
    }

    public Double getRgv() {
        return rgv;
    }

    public void setRgv(Double rgv) {
        this.rgv = rgv;
    }

    public Double getSsll() {
        return ssll;
    }

    public void setSsll(Double ssll) {
        this.ssll = ssll;
    }

    public Double getZll() {
        return zll;
    }

    public void setZll(Double zll) {
        this.zll = zll;
    }

    public Double getDhrl() {
        return dhrl;
    }

    public void setDhrl(Double dhrl) {
        this.dhrl = dhrl;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public XqModel() {
        super();
    }

    public XqModel(String uuid, String name, Integer hs, Integer bh, Integer sim, Double mj, Timestamp dates, Double gw, Double hw, Double zrl, Double rgv, Double ssll, Double zll, Double dhrl, String bz) {
        this.uuid = uuid;
        this.name = name;
        this.hs = hs;
        this.bh = bh;
        this.sim = sim;
        this.mj = mj;
        this.dates = dates;
        this.gw = gw;
        this.hw = hw;
        this.zrl = zrl;
        this.rgv = rgv;
        this.ssll = ssll;
        this.zll = zll;
        this.dhrl = dhrl;
        this.bz = bz;
    }
}