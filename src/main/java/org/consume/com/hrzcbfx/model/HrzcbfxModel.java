package org.consume.com.hrzcbfx.model;

public class HrzcbfxModel {
    private String uuid;
    private double v;
    private double v1;
    private double v2;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double getV1() {
        return v1;
    }

    public void setV1(double v1) {
        this.v1 = v1;
    }

    public double getV2() {
        return v2;
    }

    public void setV2(double v2) {
        this.v2 = v2;
    }

    public HrzcbfxModel() {
        super();
    }

    public HrzcbfxModel(String uuid, double v, double v1, double v2) {
        this.uuid = uuid;
        this.v = v;
        this.v1 = v1;
        this.v2 = v2;
    }
}
