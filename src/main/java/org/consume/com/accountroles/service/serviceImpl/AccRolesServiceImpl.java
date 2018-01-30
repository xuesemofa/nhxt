package org.consume.com.accountroles.service.serviceImpl;

import org.consume.com.accountroles.mapper.AccRolesMapper;
import org.consume.com.accountroles.modela.AccRolesModel;
import org.consume.com.accountroles.service.AccRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccRolesServiceImpl implements AccRolesService {
    @Autowired
    private AccRolesMapper mapper;

    @Override
    public int add(AccRolesModel accRole) {
        return mapper.add(accRole);
    }

    @Override
    public int del(String uuid) {
        return mapper.del(uuid);
    }

    @Override
    public AccRolesModel findByAccId(String uuid) {
        return mapper.findByAccId(uuid);
    }

    @Override
    public int update(AccRolesModel accRoles) {
        return mapper.update(accRoles);
    }

    @Override
    public AccRolesModel findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }
}
