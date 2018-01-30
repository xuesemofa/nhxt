package org.consume.com.userJurisdiction.service.serviceImpl;

import org.consume.com.jurisdiction.model.JurisdictionModel;
import org.consume.com.userJurisdiction.mapper.UserJurisdictionMapper;
import org.consume.com.userJurisdiction.model.UserJurisdictionModel;
import org.consume.com.userJurisdiction.service.UserJurisdictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserJurisdictionServiceImpl implements UserJurisdictionService {
    @Autowired
    private UserJurisdictionMapper mapper;

    @Override
    public int add(UserJurisdictionModel roju) {
        return mapper.add(roju);
    }

    @Override
    public int del(String uuid) {
        return mapper.del(uuid);
    }

    @Override
    public int delByUserId(String userid) {
        return mapper.delByUserId(userid);
    }

    @Override
    public List<UserJurisdictionModel> findByUserId(String userId) {
        return mapper.findByUserId(userId);
    }

    @Override
    public int UpdateByUserId(UserJurisdictionModel model) {
        return mapper.UpdateByUserId(model);
    }

    @Override
    public List<JurisdictionModel> findJurByUserId(String id) {
        return mapper.findJurByUserId(id);
    }
}
