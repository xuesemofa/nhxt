package org.consume.com.sdrdj.model;

public class SdrdjModel {
    private String uuid;
    //    水
    private double v1 = 0;
    //    电
    private double v2 = 0;
    //    热
    private double v3 = 0;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public double getV3() {
        return v3;
    }

    public void setV3(double v3) {
        this.v3 = v3;
    }

    public SdrdjModel() {
        super();
    }

    public SdrdjModel(String uuid, double v1, double v2, double v3) {
        this.uuid = uuid;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }
}
