package org.consume.com.rwgwbj.service.serviceImpl;

import org.consume.com.rwgwbj.mapper.RwgwbjMapper;
import org.consume.com.rwgwbj.model.RwgwbjModel;
import org.consume.com.rwgwbj.service.RwgwbjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RwgwbjServiceImpl implements RwgwbjService {
    @Autowired
    private RwgwbjMapper mapper;
    @Override
    public List<RwgwbjModel> findNowByRwgw() {
        return mapper.findNowByRwgw();
    }
}
