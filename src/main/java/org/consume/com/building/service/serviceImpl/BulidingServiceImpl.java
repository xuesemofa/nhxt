package org.consume.com.building.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.consume.com.building.mapper.BuildingMapper;
import org.consume.com.building.model.BuildingModel;
import org.consume.com.building.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BulidingServiceImpl implements BuildingService{

    @Autowired
    private BuildingMapper mapper;


    @Override
    public Page<BuildingModel> findAll2(int pageNow, int pageSize, String serch) {
        PageHelper.startPage(pageNow, pageSize);
        if(serch == null||"".equals(serch)){
            return mapper.findAll();
        }
        return mapper.findAll2("%"+serch+"%");
    }

    @Override
    public BuildingModel findByName(String names) {
        return mapper.findByName(names);
    }

    @Override
    public BuildingModel findById(String id) {
        return mapper.findById(id);
    }

    @Override
    public List<BuildingModel> findAll() {
        return mapper.findAll();
    }

    @Override
    public int add(BuildingModel b) {
        return mapper.add(b);
    }

    @Override
    public int del(String id) {
        return mapper.del(id);
    }

    @Override
    public int update(BuildingModel b) {
        return mapper.update(b);
    }
}
