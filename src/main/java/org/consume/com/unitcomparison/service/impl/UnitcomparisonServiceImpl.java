package org.consume.com.unitcomparison.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.unitcomparison.mapper.UnitcomparisonMapper;
import org.consume.com.unitcomparison.model.UnitComparisonModel;
import org.consume.com.unitcomparison.service.UnitcomparisonService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UnitcomparisonServiceImpl implements UnitcomparisonService {

    @Resource
    private UnitcomparisonMapper mapper;

    @Override
    public Page<ExchangeModel> findAllPage(int pageNow, int pageSize, String serach) {
        PageHelper.startPage(pageNow, pageSize);
        if (serach == null || serach.trim().equals(""))
            return mapper.findAllPage();
        return mapper.findAllPage2("%" + serach + "%");
    }

    @Override
    public int add(UnitComparisonModel model) {
        model.setUuid(GetUuid.getUUID());
        return mapper.add(model);
    }

    @Override
    public Page<UnitComparisonModel> findAllPage2(int pageNow, int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        return mapper.findAllPage4();
    }

    @Override
    public Page<UnitComparisonModel> findAllPage3(int pageNow, int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        return mapper.findAllPage3();
    }

    @Override
    public UnitComparisonModel getById(String id) {
        return mapper.getById(id);
    }

    @Override
    public int del(String id) {
        return mapper.del(id);
    }
}
