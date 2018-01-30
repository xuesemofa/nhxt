package org.consume.com.hrzddhpm.service.serviceImpl;

import org.consume.com.hrzddhpm.mapper.HrzddhpmMapper;
import org.consume.com.hrzddhpm.service.HrzddhpmService;
import org.consume.com.sltj.model.SltjModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HrzddhpmServiceImpl implements HrzddhpmService {
    @Autowired
    private HrzddhpmMapper mapper;

    @Override
    public List<SltjModel> findHrzddhpm( List<String> object) {
        return mapper.findHrzddhpm(object);
    }
}
