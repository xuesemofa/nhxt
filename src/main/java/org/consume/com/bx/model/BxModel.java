package org.consume.com.bx.model;

import java.sql.Timestamp;

/**
 * @table bx_table
 */
public class BxModel {
    private String uuid;
    private String user_id;
    private String bx_txt;
    private Timestamp dates;
    //    0:未处理 1:已处理
    private int lx;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBx_txt() {
        return bx_txt;
    }

    public void setBx_txt(String bx_txt) {
        this.bx_txt = bx_txt;
    }

    public Timestamp getDates() {
        return dates;
    }

    public void setDates(Timestamp dates) {
        this.dates = dates;
    }

    public int getLx() {
        return lx;
    }

    public void setLx(int lx) {
        this.lx = lx;
    }

    public BxModel() {
        super();
    }

    public BxModel(String uuid, String user_id, String bx_txt, Timestamp dates, int lx) {
        this.uuid = uuid;
        this.user_id = user_id;
        this.bx_txt = bx_txt;
        this.dates = dates;
        this.lx = lx;
    }
}
