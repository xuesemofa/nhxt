package org.consume.com.hrzmj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.consume.com.hrzmj.mapper.HrzmjMapper;
import org.consume.com.hrzmj.model.HrzmjModel;
import org.consume.com.hrzmj.service.HrzmjService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class HrzmjServiceImpl implements HrzmjService {

    @Resource
    private HrzmjMapper mapper;

    @Override
    public int add(HrzmjModel model) {
        HrzmjModel jzid = mapper.getByJzid(model.getJzid());
        if (jzid != null)
            mapper.del(jzid.getJzid());
        model.setUuid(GetUuid.getUUID());
        return mapper.add(model);
    }

    @Override
    public int update(HrzmjModel model) {
        return mapper.update(model);
    }

    @Override
    public int del(String id) {
        return mapper.del(id);
    }

    @Override
    public Page<HrzmjModel> findPage(int pageNow, int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        return mapper.findPage();
    }

    @Override
    public HrzmjModel getByJzid(String id) {
        return mapper.getByJzid(id);
    }
}
