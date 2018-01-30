package org.consume.com.area.service;

import java.util.List;

import org.consume.com.area.model.AreaModel;

public interface AreaService {
	int add(AreaModel model);
	//改
    int upDate(AreaModel model);
	//查
	List<AreaModel> findAll();
	
	AreaModel getByName(String names);
	
	AreaModel getById(String id);
}
