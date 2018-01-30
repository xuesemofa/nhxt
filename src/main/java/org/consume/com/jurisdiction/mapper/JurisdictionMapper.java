package org.consume.com.jurisdiction.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.consume.com.jurisdiction.mapper.provider.Provider;
import org.consume.com.jurisdiction.model.JurisdictionModel;

import java.util.List;

public interface JurisdictionMapper {
    //增加权限
    @Insert({"INSERT INTO jurisdiction_table VALUES (#{ju.uuid},#{ju.juname},#{ju.url},null,#{ju.parent})"})
    int add(@Param("ju") JurisdictionModel ju);

    //删除权限
    @Delete({"DELETE FROM jurisdiction_table WHERE uuid = #{uuid}"})
    int del(@Param("uuid") String uuid);

    //删除权限
    @Delete({"DELETE FROM jurisdiction_table"})
    int del2();

    //查询所有
    @Select({"SELECT * FROM jurisdiction_table ORDER BY juname"})
    List<JurisdictionModel> findAll();

    //查询所有并进行分页
    @Select({"SELECT * FROM jurisdiction_table ORDER BY juname"})
    Page<JurisdictionModel> findAllByPage();

    //    批量初始化权限
    @InsertProvider(type = Provider.class, method = "insertAll")
    int insertAll(@Param("list") List<JurisdictionModel> list);
}
