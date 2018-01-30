package org.consume.com.yh.service;

import org.consume.com.yh.model.YhModel;

import com.github.pagehelper.Page;

public interface YhService {
	Page<YhModel> findAllPage(int pageNow, int pageSize);
	Page<YhModel> findByUserName(int pageNow, int pageSize,String username);
	YhModel getByName(String name);
}
