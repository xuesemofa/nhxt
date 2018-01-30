package org.consume.com.area.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.consume.com.area.model.AreaModel;

public interface AreaMapper {
	/**
     * add
     *
     * @param model AreaModel
     * @return int
     */
    @Insert({
            "insert into area_table set uuid = #{model.uuid},name = #{model.name},falg=#{model.falg}"
    })
    int add(@Param("model") AreaModel model);
	//改
    /**
     * update
     *
     * @param model AreaModel
     * @return int
     */
    @Update({
            "update area_table set name = #{model.name},falg=#{model.falg} where uuid = #{model.uuid}"
    })
    int upDate(@Param("model") AreaModel model);
	//查
    @Select({
        "select * from area_table order by CONVERT(name USING gbk)"
	})
	List<AreaModel> findAll();
	
	@Select({
	        "select * from area_table where name = #{name}"
	})
	AreaModel getByName(@Param("name") String name);
	
	@Select({
	        "select * from area_table where uuid = #{id}"
	})
	AreaModel getById(@Param("id") String id);
}
