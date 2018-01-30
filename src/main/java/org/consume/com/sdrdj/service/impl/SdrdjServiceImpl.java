package org.consume.com.sdrdj.service.impl;

import org.consume.com.sdrdj.mapper.SdrdjMapper;
import org.consume.com.sdrdj.model.SdrdjModel;
import org.consume.com.sdrdj.service.SdrdjService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SdrdjServiceImpl implements SdrdjService {

    @Autowired
    private SdrdjMapper mapper;

    @Override
    public int add(SdrdjModel model) {
        model.setUuid(GetUuid.getUUID());
        return mapper.add(model);
    }

    @Override
    public int del() {
        return mapper.del();
    }

    @Override
    public List<SdrdjModel> finds() {
        return mapper.finds();
    }
}
