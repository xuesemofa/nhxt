package org.consume.com.hrzmj.model;

import javax.validation.constraints.DecimalMin;

/**
 * @table jzmj_table
 */
public class HrzmjModel {
    private String uuid;
    private String jzid;
    @DecimalMin(value = "0", message = "面积不能小于0")
    private double hrzmj;

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

    public double getHrzmj() {
        return hrzmj;
    }

    public void setHrzmj(double hrzmj) {
        this.hrzmj = hrzmj;
    }

    public HrzmjModel() {
        super();
    }

    public HrzmjModel(String uuid, String jzid, double hrzmj) {
        this.uuid = uuid;
        this.jzid = jzid;
        this.hrzmj = hrzmj;
    }
}
