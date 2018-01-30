package org.consume.com.sdrdj.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.sdrdj.model.SdrdjModel;

import java.util.List;

public interface SdrdjMapper {

    @Insert({
            "insert into sdrdj_table set uuid = #{model.uuid},v1=#{model.v1},v2=#{model.v2},v3=#{model.v3}"
    })
    int add(@Param("model") SdrdjModel model);

    @Delete({
            "delete from sdrdj_table"
    })
    int del();

    @Select({
            "select * from sdrdj_table"
    })
    List<SdrdjModel> finds();

}
