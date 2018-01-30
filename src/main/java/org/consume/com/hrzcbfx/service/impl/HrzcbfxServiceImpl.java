package org.consume.com.hrzcbfx.service.impl;

import org.consume.com.hrzcbfx.mapper.HrzcbfxMapper;
import org.consume.com.hrzcbfx.model.HrzcbfxModel;
import org.consume.com.hrzcbfx.service.HrzcbfxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HrzcbfxServiceImpl implements HrzcbfxService {

    @Autowired
    private HrzcbfxMapper mapper;

    @Override
    public List<HrzcbfxModel> queryByCompany(String id) {
        return mapper.queryByCompany(id);
    }
}
