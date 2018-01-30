package org.consume.com.roles.service;

import com.github.pagehelper.Page;
import org.consume.com.roles.model.RolesModel;

import java.util.List;

public interface RoleService {
    //添加
    int add(RolesModel role);

    //根据角色的主键进行删除
    int del(String uuid);

    //分页查询所欲的角色列表
    Page<RolesModel> findAllPage(int pageNow, int pageSize);

    //根据人员id查找对应的角色列表
    List<RolesModel> findByUserId(String UserId);

    //查询所有角色列表
    List<RolesModel> findAll();
}
