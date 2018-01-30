package org.consume.com.hrzddhpm.model;

public class HrzddhpmModel {
    private String dates;
    private String zdname;
    private Double hdl;

    public HrzddhpmModel() {
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getZdname() {
        return zdname;
    }

    public void setZdname(String zdname) {
        this.zdname = zdname;
    }

    public Double getHdl() {
        return hdl;
    }

    public void setHdl(Double hdl) {
        this.hdl = hdl;
    }

    public HrzddhpmModel(String dates, String zdname, Double hdl) {
        this.dates = dates;
        this.zdname = zdname;
        this.hdl = hdl;
    }

    @Override
    public String toString() {
        return "HrzddhpmModel{" +
                "dates='" + dates + '\'' +
                ", zdname='" + zdname + '\'' +
                ", hdl=" + hdl +
                '}';
    }
}
