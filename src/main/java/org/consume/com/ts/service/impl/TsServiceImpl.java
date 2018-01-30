package org.consume.com.ts.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.consume.com.ts.mapper.TsMapper;
import org.consume.com.ts.model.TsModel;
import org.consume.com.ts.service.TsService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TsServiceImpl implements TsService {

    @Autowired
    private TsMapper mapper;

    @Override
    public Page<TsModel> findPage(int pageNow, int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        return mapper.findPage();
    }

    @Override
    public int add(TsModel model) {
        model.setUuid(GetUuid.getUUID());
        return mapper.add(model);
    }

    @Override
    public int update_lx(String id, int lx) {
        return mapper.update_lx(id, lx);
    }
}
