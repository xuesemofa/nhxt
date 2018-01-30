package org.consume.com.heat.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @name 换热站维护
 * @table heat_table
 */
public class HeatModel implements Serializable {
    //    id
    private String uuid;
    //    换热站名称
    @NotBlank(message = "换热站名称不能为空")
    @Size(min = 1, max = 30, message = "换热站名称长度位1-30")
    private String names;

    private String parents;
    @Min(value = 1, message = "面积不能小于0")
    private double mj = 1.00;

    private double wx = 0.00;
    //片区
    private String pq;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public double getMj() {
        return mj;
    }

    public void setMj(double mj) {
        this.mj = mj;
    }

    public double getWx() {
        return wx;
    }

    public void setWx(double wx) {
        this.wx = wx;
    }

    public HeatModel() {
        super();
    }

    public String getPq() {
		return pq;
	}

	public void setPq(String pq) {
		this.pq = pq;
	}

	public HeatModel(String uuid, String names, String parents, double mj, double wx) {
        this.uuid = uuid;
        this.names = names;
        this.parents = parents;
        this.mj = mj;
        this.wx = wx;
    }
}
