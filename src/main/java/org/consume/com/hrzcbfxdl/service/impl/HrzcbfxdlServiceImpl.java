package org.consume.com.hrzcbfxdl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.hrzcbfxdl.service.HrzcbfxdlService;
import org.consume.com.hrzcbfxsl.service.HrzcbfxslService;
import org.consume.com.hrzdl.mapper.HrzdlMapper;
import org.consume.com.hrzdl.model.HrzdlModel;
import org.consume.com.hrzsl.mapper.HrzslMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HrzcbfxdlServiceImpl implements HrzcbfxdlService {

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
}
