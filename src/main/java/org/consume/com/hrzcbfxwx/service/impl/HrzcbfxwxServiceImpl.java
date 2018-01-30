package org.consume.com.hrzcbfxwx.service.impl;

import org.consume.com.hrzcbfxwx.mapper.HrzcbfxwxMapper;
import org.consume.com.hrzcbfxwx.service.HrzcbfxwxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HrzcbfxwxServiceImpl implements HrzcbfxwxService {

    @Autowired
    private HrzcbfxwxMapper mapper;

    @Override
    public void setWx(String id, double wx) {
        mapper.setWx(id, wx);
    }
}
