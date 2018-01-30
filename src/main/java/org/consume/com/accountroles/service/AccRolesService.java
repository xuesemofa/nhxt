package org.consume.com.accountroles.service;

import org.consume.com.accountroles.modela.AccRolesModel;

public interface AccRolesService {
    //添加账户角色对应关系
    int add(AccRolesModel accRole);

    //删除账户角色对应关系
    int del(String uuid);

    //根据人员资料ID查找对应的关联记录
    AccRolesModel findByAccId(String uuid);

    //更改账户角色的关联关系
    int update(AccRolesModel accRoles);

    //根据uuid查找庄户角色对应关系
    AccRolesModel findByUuid(String uuid);
}
