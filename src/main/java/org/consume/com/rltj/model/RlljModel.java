package org.consume.com.rltj.model;

import java.sql.Timestamp;

public class RlljModel {
    private String uuid;
    private String yesdata;
    private String befData;
    private String chae;
    private Timestamp dates;

    public RlljModel() {
    }

    public RlljModel(String uuid, String yesdata, String befData, String chae, Timestamp dates) {
        this.uuid = uuid;
        this.yesdata = yesdata;
        this.befData = befData;
        this.chae = chae;
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "RlljModel{" +
                "uuid='" + uuid + '\'' +
                ", yesdata='" + yesdata + '\'' +
                ", befData='" + befData + '\'' +
                ", chae='" + chae + '\'' +
                ", dates=" + dates +
                '}';
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getYesdata() {
        return yesdata;
    }

    public void setYesdata(String yesdata) {
        this.yesdata = yesdata;
    }

    public String getBefData() {
        return befData;
    }

    public void setBefData(String befData) {
        this.befData = befData;
    }

    public String getChae() {
        return chae;
    }

    public void setChae(String chae) {
        this.chae = chae;
    }

    public Timestamp getDates() {
        return dates;
    }

    public void setDates(Timestamp dates) {
        this.dates = dates;
    }
}
