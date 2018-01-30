package org.consume.com.structure.service.impl;

import org.consume.com.structure.mapper.StructureMapper;
import org.consume.com.structure.model.StructureModel;
import org.consume.com.structure.service.StructureService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class StructureServiceImpl implements StructureService {

    @Resource
    private StructureMapper mapper;

    @Override
    public int add(StructureModel model) {
        model.setUuid(GetUuid.getUUID());
        return mapper.add(model);
    }

    @Override
    public int del(String id) {
        return mapper.del(id);
    }

    @Override
    public int update(StructureModel model) {
        return mapper.update(model);
    }

    @Override
    public List<StructureModel> getByParent(String parentid) {
        return mapper.getByParent(parentid);
    }

    @Override
    public StructureModel getById(String id) {
        return mapper.getById(id);
    }

    @Override
    public List<StructureModel> findAll(String types) {
        return mapper.findAll(types);
    }

    @Override
    public List<StructureModel> findAlls(String parents, String types) {
        return mapper.findAlls(parents, types);
    }

    @Override
    public List<StructureModel> getByParents() {

        return mapper.getByParents();
    }

    @Override
    public List<StructureModel> getEqual(String id) {
        return mapper.getEqual(id);
    }
}
