package org.consume.com.bx.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.consume.com.bx.mapper.BxMapper;
import org.consume.com.bx.model.BxModel;
import org.consume.com.bx.service.BxService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BxServiceImpl implements BxService {

    @Autowired
    private BxMapper mapper;

    @Override
    public Page<BxModel> findPage(int pageNow, int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        return mapper.findPage();
    }

    @Override
    public int add(BxModel model) {
        model.setUuid(GetUuid.getUUID());
        return mapper.add(model);
    }

    @Override
    public int update_lx(String id, int lx) {
        return mapper.update_lx(id, lx);
    }
}
