package org.consume.com.building.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class BuildingModel {
    private String uuid;
    @NotBlank(message = "建筑类型名称不能为空")
    @Size(min = 1, max = 30, message = "换热站名称长度位1-30")
    private String names;

    public BuildingModel(String uuid, String names) {
        this.uuid = uuid;
        this.names = names;
    }

    public BuildingModel() {
    }

    @Override
    public String toString() {
        return "BuildingModel{" +
                "uuid='" + uuid + '\'' +
                ", names='" + names + '\'' +
                '}';
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
}
