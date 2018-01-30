package org.consume.com.accountroles.mapper;

import org.apache.ibatis.annotations.*;
import org.consume.com.accountroles.modela.AccRolesModel;

public interface AccRolesMapper {
    //添加账户角色对应关系
    @Insert({"INSERT INTO accrole_table VALUES(#{acc.uuid},#{acc.roleId},#{acc.accId})"})
    int add(@Param("acc") AccRolesModel accRole);

    //删除账户角色对应关系
    @Delete({"DELETE FROM accrole_table WHERE uuid = #{uuid}"})
    int del(@Param("uuid") String uuid);

    //根据人员资料ID查找对应的关联记录
    @Select({"SELECT * FROM accrole_table WHERE accId = #{uuid}"})
    AccRolesModel findByAccId(@Param("uuid") String uuid);

    //更改账户角色的关联关系
    @Update({"UPDATE accrole_table SET roleId = #{accRoles.roleId} WHERE uuid = #{accRoles.uuid}"})
    int update(@Param("accRoles") AccRolesModel accRoles);

    //根据uuid查找庄户角色对应关系
    @Select({"SELECT * FROM accrole_table WHERE uuid = #{uuid}"})
    AccRolesModel findByUuid(@Param("uuid") String uuid);
}
