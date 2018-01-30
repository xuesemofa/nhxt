package org.consume.com.rwgwbj.model;

public class RwgwbjModel {
    private String jzid;
    private String rwgw;

    public String getJzid() {
        return jzid;
    }

    public void setJzid(String jzid) {
        this.jzid = jzid;
    }

    public String getRwgw() {
        return rwgw;
    }

    public void setRwgw(String rwgw) {
        this.rwgw = rwgw;
    }

    public RwgwbjModel() {
    }

    public RwgwbjModel(String jzid, String rwgw) {
        this.jzid = jzid;
        this.rwgw = rwgw;
    }

    @Override
    public String toString() {
        return "RwgwbjModel{" +
                "jzid='" + jzid + '\'' +
                ", rwgw='" + rwgw + '\'' +
                '}';
    }
}
