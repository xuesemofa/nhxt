package org.consume.com.exchange.model;

public class OpcDatas2Model {
    private String k;
    private String v;

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public OpcDatas2Model() {
        super();
    }

    public OpcDatas2Model(String k, String v) {
        this.k = k;
        this.v = v;
    }
}
