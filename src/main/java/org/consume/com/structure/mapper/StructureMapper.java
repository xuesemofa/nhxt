package org.consume.com.structure.mapper;

import org.apache.ibatis.annotations.*;
import org.consume.com.structure.model.StructureModel;

import java.util.List;

/**
 * @table structure_table
 */
public interface StructureMapper {

    String tableName = " structure_table ";

    /**
     * add
     *
     * @param model StructureModel
     * @return int
     */
    @Insert({
            "insert into " + tableName + " values (#{model.uuid},#{model.names},#{model.parents},#{model.types},#{model.ordes})"
    })
    int add(@Param("model") StructureModel model);

    /**
     * del
     *
     * @param id String
     * @return int
     */
    @Delete({
            "delete from " + tableName + " where uuid = #{id}"
    })
    int del(@Param("id") String id);

    /**
     * update
     *
     * @param model StructureModel
     * @return int
     */
    @Update({
            "update from " + tableName + " set names = #{model.names},parents = #{model.parents},types = #{model.types},ordes=#{model.ordes}" +
                    " where uuid = #{model.uuid}"
    })
    int update(@Param("model") StructureModel model);

    /**
     * getByParent
     * 根据父级查询下级
     *
     * @param parentid String
     * @return List<StructureModel>
     */
    @Select({
            "select * from " + tableName + " where parents = #{parentid} order by ordes"
    })
    List<StructureModel> getByParent(@Param("parentid") String parentid);

    @Select({
            "select * from " + tableName + " order by ordes"
    })
    List<StructureModel> getByParents();

    @Select({
            "select * from " + tableName + " where uuid = #{id}"
    })
    StructureModel getById(@Param("id") String id);

    @Select({
            "select * from " + tableName + " where parents = #{types} order by ordes"
    })
    List<StructureModel> findAll(@Param("types") String types);

    @Select({
            "select * from " + tableName + " where parents = #{parents} and types = #{types} order by ordes"
    })
    List<StructureModel> findAlls(@Param("parents") String parents, @Param("types") String types);

    /**
     * 根据id查询等级的
     *
     * @param id
     * @return
     */
    @Select({
            "select * from " + tableName + " where parents in (select parents from "
                    + tableName + "where uuid = #{id})"
    })
    List<StructureModel> getEqual(@Param("id") String id);
}
