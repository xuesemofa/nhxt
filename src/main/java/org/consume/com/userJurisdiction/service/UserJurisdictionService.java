package org.consume.com.userJurisdiction.service;

import org.apache.ibatis.annotations.Param;
import org.consume.com.jurisdiction.model.JurisdictionModel;
import org.consume.com.userJurisdiction.model.UserJurisdictionModel;

import java.util.List;

public interface UserJurisdictionService {
    //添加账户与权限间的对应关系
    int add(UserJurisdictionModel model);

    //删除账户与权限间的对应关系
    int del(String uuid);

    //根据用户主键删除对应关系
    int delByUserId(String userid);

    //通过用户主键查找
    List<UserJurisdictionModel> findByUserId(@Param("userId") String userId);

    //更改账户权限关联
    int UpdateByUserId(UserJurisdictionModel model);

    //根据账户ID返回权限Model list
    List<JurisdictionModel> findJurByUserId(String id);
}
