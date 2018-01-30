package org.consume.com.rymj.service.impl;

import org.consume.com.rymj.mapper.RymjMapper;
import org.consume.com.rymj.model.RymjModel;
import org.consume.com.rymj.service.RymjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RymjServiceImpl implements RymjService {

    @Autowired
    private RymjMapper mapper;

    @Override
    public int set(RymjModel model) {
        mapper.del();
        return mapper.set(model);
    }

    @Override
    public List<RymjModel> get() {
        return mapper.get();
    }
}
