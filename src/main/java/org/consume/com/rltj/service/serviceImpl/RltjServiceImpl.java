package org.consume.com.rltj.service.serviceImpl;

import org.consume.com.opc.model.Datas3Model;
import org.consume.com.rltj.mapper.RltjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RltjServiceImpl implements RltjService {
    @Autowired
    private RltjMapper mapper;
    @Override
    public Datas3Model findYesterday() {
        return mapper.findYesterday();
    }

    @Override
    public Datas3Model findBeforeYesterday() {
        return mapper.findBeforeYesterday();
    }

    @Override
    public List<Datas3Model> findWeek() {
        return mapper.findWeek();
    }
}
