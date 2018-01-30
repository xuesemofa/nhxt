package org.consume.com.crew.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @name 机组维护
 * @table crew_table
 */
public class CrewModel {
    //    id
    private String uuid;
    //    机组名称
    @NotBlank(message = "机组名称不能为空")
    private String names;
    //    顺序
    private Integer orders;

    //    面积
    @Min(value = 0,message = "面积不能小于0")
    private Double mj = 0.0;

    //    父id
    private String parents;

    //建筑类型
    private String jzlx;

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

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public double getMj() {
        return mj < 0 ? 0 : mj;
    }

    public void setMj(Double mj) {
        this.mj = mj;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getJzlx() {
        return jzlx;
    }

    public void setJzlx(String jzlx) {
        this.jzlx = jzlx;
    }

    public CrewModel() {
        super();
    }

    public CrewModel(String uuid, String names, Integer orders, Double mj, String parents, String jzlx) {
        this.uuid = uuid;
        this.names = names;
        this.orders = orders;
        this.mj = mj;
        this.parents = parents;
        this.jzlx = jzlx;
    }

    @Override
    public String toString() {
        return "CrewModel{" +
                "uuid='" + uuid + '\'' +
                ", names='" + names + '\'' +
                ", orders=" + orders +
                ", mj=" + mj +
                ", parents='" + parents + '\'' +
                ", jzlx='" + jzlx + '\'' +
                '}';
    }
}
