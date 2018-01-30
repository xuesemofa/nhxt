package org.consume.com.jzmj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.consume.com.jzmj.mapper.JzmjMapper;
import org.consume.com.jzmj.model.JzmjModel;
import org.consume.com.jzmj.service.JzmjService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class JzmjServiceImpl implements JzmjService {

    @Resource
    private JzmjMapper mapper;

    @Override
    public int add(JzmjModel model) {
        model.setUuid(GetUuid.getUUID());
        return mapper.add(model);
    }

    @Override
    public int update(JzmjModel model) {
        return mapper.update(model);
    }

    @Override
    public int del(String id) {
        return mapper.del(id);
    }

    @Override
    public Page<JzmjModel> findPage(int pageNow, int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        return mapper.findPage();
    }

    @Override
    public JzmjModel getByJzid(String id) {
        return mapper.getByJzid(id);
    }
}
