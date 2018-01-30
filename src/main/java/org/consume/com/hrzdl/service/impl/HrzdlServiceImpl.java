package org.consume.com.hrzdl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.hrzdl.mapper.HrzdlMapper;
import org.consume.com.hrzdl.model.HrzdlModel;
import org.consume.com.hrzdl.service.HrzdlService;
import org.consume.com.sltj.model.SltjModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HrzdlServiceImpl implements HrzdlService {

    @Autowired
    private HrzdlMapper mapper;

    @Override
    public List<HrzdlModel> getByJzid(String id, long strDate, long endDate) {
        return mapper.getByJzid(id, strDate, endDate);
    }

    @Override
    public Page<ExchangeModel> findAllPage(int pageNow, int pageSize, String serach) {
        PageHelper.startPage(pageNow, pageSize);
        if (serach == null || serach.trim().equals(""))
            return mapper.findAllPage();
        return mapper.findAllPage2("%" + serach + "%");
    }

    @Override
    public List<HrzdlModel> findById(String id) {
        return mapper.findById(id);
    }
}
