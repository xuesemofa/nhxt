package org.consume.com.xq.service;

import org.consume.com.xq.model.XqModel;

import com.github.pagehelper.Page;

public interface XqService {
	Page<XqModel> findAllPage(int pageNow, int pageSize);
	XqModel getByName(String name);
	Page<XqModel> findByName(int pageNow, int pageSize, String name);
}
