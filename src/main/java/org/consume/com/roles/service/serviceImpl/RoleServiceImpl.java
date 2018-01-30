package org.consume.com.roles.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.consume.com.roles.mapper.RoleMapper;
import org.consume.com.roles.model.RolesModel;
import org.consume.com.roles.service.RoleService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper mapper;

    @Override
    public int add(RolesModel role) {
        role.setUuid(GetUuid.getUUID());
        return mapper.add(role);
    }

    @Override
    public int del(String uuid) {
        return mapper.del(uuid);
    }

    @Override
    public Page<RolesModel> findAllPage(int pageNow, int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        return mapper.findAllPage();
    }

    @Override
    public List<RolesModel> findByUserId(String UserId) {
        return mapper.findByUserId(UserId);
    }

    @Override
    public List<RolesModel> findAll() {
        return mapper.findAll();
    }
}
