package org.consume.com.gwgrzq.service.impl;

import org.consume.com.gwgrzq.mapper.GwgrzqMapper;
import org.consume.com.gwgrzq.model.GwgrzqModel;
import org.consume.com.gwgrzq.service.GwgrzqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GwgrzqServiceImpl implements GwgrzqService {

    @Autowired
    private GwgrzqMapper mapper;

    @Override
    public void setZq(Timestamp k, Timestamp v) {
        mapper.del();
        mapper.setZq(k, v);
    }

    @Override
    public void del() {
        mapper.del();
    }

    @Override
    public List<GwgrzqModel> find() {
        return mapper.find();
    }

    @Override
    public long getmm() {
        List<GwgrzqModel> gwgrzqModels = mapper.find();
        Date date = new Date();
        if (gwgrzqModels == null || gwgrzqModels.size() <= 0)
            date = new Date();
        else
            date = gwgrzqModels.get(0).getStrat_day();
        long l = (new Date().getTime() - date.getTime()) / 1000;
        return l;
    }
}
