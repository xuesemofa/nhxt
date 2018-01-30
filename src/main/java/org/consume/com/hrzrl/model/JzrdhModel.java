package org.consume.com.hrzrl.model;

import java.sql.Timestamp;

public class JzrdhModel {
    private Timestamp sj;
    private Double rl;

    public Timestamp getSj() {
        return sj;
    }

    public void setSj(Timestamp sj) {
        this.sj = sj;
    }

    public Double getRl() {
        return rl;
    }

    public void setRl(Double rl) {
        this.rl = rl;
    }

    public JzrdhModel() {
        super();
    }

    public JzrdhModel(Timestamp sj, Double rl) {
        this.sj = sj;
        this.rl = rl;
    }
}
