package org.consume.com.jurisdiction.service;

import com.github.pagehelper.Page;
import org.consume.com.jurisdiction.model.JurisdictionModel;

import java.util.List;

public interface JurisdictionService {
    //增加权限
    int add(JurisdictionModel ju);

//    批量初始化权限
    int insertAll();

    //删除权限
    int del(String uuid);

//    删除所有权限
    int del2();

    //查询所有
    List<JurisdictionModel> findAll();

    //查询所有并进行分页
    Page<JurisdictionModel> findAllByPage(int pageNow,int pageSize);
}
