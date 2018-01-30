package org.consume.com.register.service.impl;

import org.consume.com.register.mapper.RegisterMapper;
import org.consume.com.register.model.RegisterModel;
import org.consume.com.register.service.RegisterService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private RegisterMapper mapper;

    @Override
    public int add(RegisterModel model) {
        model.setUuid(GetUuid.getUUID());
        return mapper.add(model);
    }
}
