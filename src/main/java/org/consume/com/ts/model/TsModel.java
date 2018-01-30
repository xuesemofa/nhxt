package org.consume.com.ts.model;

import java.sql.Timestamp;

/**
 * @table ts_table
 */
public class TsModel {
    private String uuid;
    private String user_id;
    private String ts_txt;
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

    public String getTs_txt() {
        return ts_txt;
    }

    public void setTs_txt(String ts_txt) {
        this.ts_txt = ts_txt;
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

    public TsModel() {
        super();
    }

    public TsModel(String uuid, String user_id, String ts_txt, Timestamp dates, int lx) {
        this.uuid = uuid;
        this.user_id = user_id;
        this.ts_txt = ts_txt;
        this.dates = dates;
        this.lx = lx;
    }
}
