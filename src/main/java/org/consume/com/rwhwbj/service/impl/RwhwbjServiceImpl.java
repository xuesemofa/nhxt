package org.consume.com.rwhwbj.service.impl;

import org.consume.com.rwgwbj.model.RwgwbjModel;
import org.consume.com.rwhwbj.mapper.RwhwbjMapper;
import org.consume.com.rwhwbj.service.RwhwbjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RwhwbjServiceImpl implements RwhwbjService {
    @Autowired
    private RwhwbjMapper mapper;

    @Override
    public List<RwgwbjModel> findNowByRwgw() {
        return mapper.findNowByRwgw();
    }
}
