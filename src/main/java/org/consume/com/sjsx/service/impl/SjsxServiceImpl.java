package org.consume.com.sjsx.service.impl;

import org.consume.com.sjsx.mapper.SjsxMapper;
import org.consume.com.sjsx.model.SjsxModel;
import org.consume.com.sjsx.service.SjsxService;
import org.springframework.beans.factory.annotation.Autowired;

public class SjsxServiceImpl implements SjsxService {

    @Autowired
    private SjsxMapper mapper;

    @Override
    public int add(SjsxModel model) {
        return mapper.add(model);
    }
}
