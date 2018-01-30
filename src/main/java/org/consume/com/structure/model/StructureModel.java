package org.consume.com.structure.model;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @name 组织机构
 * @table structure_table
 */
public class StructureModel implements Serializable {
    //    id
    private String uuid;
    //    名称
    @NotBlank(message = "名称不能位空")
    private String names;
    //    父级
    @NotBlank(message = "父级不能为空")
    private String parents;
    //    类别 1:分公司 2：部门 3：职位
    @NotBlank(message = "类型不能为空")
    private String types;

    private int ordes;

    public StructureModel() {
        super();
    }

    public StructureModel(String uuid, String names, String parents, String types) {
        this.uuid = uuid;
        this.names = names;
        this.parents = parents;
        this.types = types;
    }

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

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public int getOrdes() {
        return ordes;
    }

    public void setOrdes(int ordes) {
        this.ordes = ordes;
    }

    public StructureModel(String uuid, String names, String parents, String types, int ordes) {
        this.uuid = uuid;
        this.names = names;
        this.parents = parents;
        this.types = types;
        this.ordes = ordes;
    }

    @Override
    public String toString() {
        return "StructureModel{" +
                "uuid='" + uuid + '\'' +
                ", names='" + names + '\'' +
                ", parents='" + parents + '\'' +
                ", types='" + types + '\'' +
                '}';
    }
}
