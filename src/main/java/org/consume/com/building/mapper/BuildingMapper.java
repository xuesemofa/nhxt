package org.consume.com.building.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.consume.com.building.model.BuildingModel;
import org.springframework.web.bind.annotation.PathVariable;


public interface BuildingMapper {

    @Select({"SELECT * FROM building_table"})
    Page<BuildingModel> findAll();

    @Select({"SELECT * FROM building_table WHERE `names` LIKE #{serch}"})
    Page<BuildingModel> findAll2(@Param("serch") String serch);

    @Select({"SELECT * FROM building_table WHERE `names` LIKE #{names}"})
    BuildingModel findByName(@Param("names")String names);

    @Select({"SELECT * FROM building_table WHERE uuid = #{id}"})
    BuildingModel findById(@Param("id") String id);

    @Insert({"INSERT INTO building_table VALUES (#{b.uuid},#{b.names})"})
    int add(@Param("b") BuildingModel b);

    @Delete({"DELETE FROM building_table WHERE uuid = #{id}"})
    int del(@Param("id")String id);

    @Update({"UPDATE building_table SET `names` = #{b.names} WHERE uuid = #{b.uuid}"})
    int update(@Param("b") BuildingModel b);

}
