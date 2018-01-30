package org.consume.com.roles.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.roles.model.RolesModel;

import java.util.List;

public interface RoleMapper {
    //添加
    @Insert({"INSERT INTO role_table VALUES (#{role.uuid},#{role.rolesName})"})
    int add(@Param("role") RolesModel role);

    //根据角色的主键进行删除
    @Delete({"DELETE FROM role_table WHERE uuid = #{uuid}"})
    int del(@Param("uuid") String uuid);

    //查询所欲的角色列表
    @Select({"SELECT * FROM role_table"})
    Page<RolesModel> findAllPage();

    //根据人员id查找对应的角色列表
    @Select({"SELECT * FROM role_table WHERE uuid in (SELECT roleId FROM accrole_table WHERE accId = #{userId})"})
    List<RolesModel> findByUserId(@Param("userId") String UserId);

    //查询所有角色列表
    @Select({"SELECT * FROM role_table"})
    List<RolesModel> findAll();
}
