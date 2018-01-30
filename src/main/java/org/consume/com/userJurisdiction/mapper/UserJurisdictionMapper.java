package org.consume.com.userJurisdiction.mapper;

import org.apache.ibatis.annotations.*;
import org.consume.com.jurisdiction.model.JurisdictionModel;
import org.consume.com.userJurisdiction.model.UserJurisdictionModel;

import java.util.List;

public interface UserJurisdictionMapper {
    //添加角色与权限间的对应关系
    @Insert({"INSERT INTO user_jurisdiction_table VALUES (#{roju.uuid},#{roju.userId},#{roju.jurisdictionId})"})
    int add(@Param("roju") UserJurisdictionModel roju);

    //删除角色与权限间的对应关系
    @Delete({"DELETE FROM user_jurisdiction_table WHERE uuid = #{uuid}"})
    int del(@Param("uuid") String uuid);

    //根据用户主键删除对应关系
    @Delete({"DELETE FROM user_jurisdiction_table WHERE userId = #{userid}"})
    int delByUserId(@Param("userid") String userid);

    //通过用户主键查找
    @Select({"SELECT * FROM user_jurisdiction_table WHERE userId = #{userId}"})
    List<UserJurisdictionModel> findByUserId(@Param("userId") String userId);

    //更改账户权限关联
    @Update({"UPDATE user_jurisdiction_table SET jurisdictionId = #{model.jurisdictionId} WHERE userId = #{model.userId}"})
    int UpdateByUserId(@Param("model") UserJurisdictionModel model);

    //根据账户ID返回权限Model list
    @Select({"SELECT j.* FROM jurisdiction_table j LEFT JOIN user_jurisdiction_table u ON u.jurisdictionId = j.uuid where u.userId = #{id}"})
    List<JurisdictionModel> findJurByUserId(@Param("id") String id);
}
