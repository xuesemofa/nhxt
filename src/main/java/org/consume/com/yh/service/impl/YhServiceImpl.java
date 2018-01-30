package org.consume.com.yh.service.impl;

import org.consume.com.yh.mapper.YhMapper;
import org.consume.com.yh.model.YhModel;
import org.consume.com.yh.service.YhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
@Transactional
public class YhServiceImpl implements YhService{
	
	@Autowired
	private YhMapper mapper;

	@Override
	public Page<YhModel> findAllPage(int pageNow, int pageSize) {
		PageHelper.startPage(pageNow, pageSize);
        return mapper.findAllPage();
	}

	@Override
	public Page<YhModel> findByUserName(int pageNow, int pageSize, String username) {
		username = "%"+username+"%";
		return mapper.findByUserName(username);
	}

	@Override
	public YhModel getByName(String name) {
		return mapper.getByName(name);
	}

}
