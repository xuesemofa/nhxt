package org.consume.com.structure.service;

import org.consume.com.structure.model.StructureModel;

import java.util.List;

/**
 * @table structure_table
 * @name 组织结构接口
 */
public interface StructureService {
    /**
     * add
     *
     * @param model StructureModel
     * @return int
     */
    int add(StructureModel model);

    /**
     * del
     *
     * @param id String
     * @return int
     */
    int del(String id);

    /**
     * update
     *
     * @param model StructureModel
     * @return int
     */
    int update(StructureModel model);

    /**
     * getByParent
     * 根据父级查询下级
     *
     * @param parentid String
     * @return List<StructureModel>
     */
    List<StructureModel> getByParent(String parentid);

    List<StructureModel> getByParents();

    StructureModel getById(String id);

    List<StructureModel> findAll(String types);

    List<StructureModel> findAlls(String parents, String types);

    /**
     * 根据id查询等级的
     *
     * @param id String
     * @return List<StructureModel>
     */
    List<StructureModel> getEqual(String id);
}
