package org.consume.com.xq.service.impl;

import org.consume.com.xq.mapper.XqMapper;
import org.consume.com.xq.model.XqModel;
import org.consume.com.xq.service.XqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
@Transactional
public class XqServiceImpl implements XqService{
	
	@Autowired
	private XqMapper mapper;

	@Override
	public Page<XqModel> findAllPage(int pageNow, int pageSize) {
		PageHelper.startPage(pageNow, pageSize);
        return mapper.findAllPage();
	}

	@Override
	public Page<XqModel> findByName(int pageNow, int pageSize,String name) {
		PageHelper.startPage(pageNow, pageSize);
		name = "%"+name+"%";
		return mapper.findByName(name);
	}

	@Override
	public XqModel getByName(String name) {
		return mapper.getByName(name);
	}

}
