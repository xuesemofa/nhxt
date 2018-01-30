package org.consume.com.jzmj.model;

import javax.validation.constraints.DecimalMin;

/**
 * @table jzmj_table
 */
public class JzmjModel {
    private String uuid;
    private String jzid;
    @DecimalMin(value = "0", message = "面积不能小于0")
    private double jzmj;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getJzid() {
        return jzid;
    }

    public void setJzid(String jzid) {
        this.jzid = jzid;
    }

    public double getJzmj() {
        return jzmj;
    }

    public void setJzmj(double jzmj) {
        this.jzmj = jzmj;
    }

    public JzmjModel() {
        super();
    }

    public JzmjModel(String uuid, String jzid, double jzmj) {
        this.uuid = uuid;
        this.jzid = jzid;
        this.jzmj = jzmj;
    }
}
