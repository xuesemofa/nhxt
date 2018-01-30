package org.consume.com.building.service;

import com.github.pagehelper.Page;
import org.consume.com.building.model.BuildingModel;

import java.util.List;


public interface BuildingService {
    Page<BuildingModel> findAll2(int pageNow, int pageSize, String serch);
    BuildingModel findByName(String names);
    BuildingModel findById(String id);
    List<BuildingModel> findAll();
    int add(BuildingModel b);
    int del(String id);
    int update(BuildingModel b);
}
