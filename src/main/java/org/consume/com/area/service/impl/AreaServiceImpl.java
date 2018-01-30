package org.consume.com.area.service.impl;

import java.util.List;

import org.consume.com.area.mapper.AreaMapper;
import org.consume.com.area.model.AreaModel;
import org.consume.com.area.service.AreaService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaMapper mapper;
	
	@Override
	public int add(AreaModel model) {
		model.setUuid(GetUuid.getUUID());
        return mapper.add(model);
	}

	@Override
	public int upDate(AreaModel model) {
		return mapper.upDate(model);
	}

	@Override
	public List<AreaModel> findAll() {
		return mapper.findAll();
	}

	@Override
	public AreaModel getByName(String name) {
		return mapper.getByName(name);
	}

	@Override
	public AreaModel getById(String id) {
		return mapper.getById(id);
	}

}
