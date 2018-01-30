package org.consume.com.sjsx.model;

public class SjsxModel {
    private String uuid;
    private String bs;
    //    1:水2：电
    private int lx;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public int getLx() {
        return lx;
    }

    public void setLx(int lx) {
        this.lx = lx;
    }

    public SjsxModel() {
        super();
    }

    public SjsxModel(String uuid, String bs, int lx) {
        this.uuid = uuid;
        this.bs = bs;
        this.lx = lx;
    }
}
