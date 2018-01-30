package org.consume.com.qxxx.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.consume.com.qxxx.mapper.QxxxMapper;
import org.consume.com.qxxx.model.QxxxModel;
import org.consume.com.qxxx.service.QxxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional

public class QxxxServiceImpl implements QxxxService {

    @Autowired
    private QxxxMapper mapper;

    @Override
    public List<QxxxModel> findPage(int pageNow, int pageSize) {
        List<QxxxModel> list = mapper.findPage();
        List<QxxxModel> list1 = mapper.findPage2(list.get(0).getBs());
        return list1;
    }

    @Override
    public int add(Map<Integer, QxxxModel> map) {
        return mapper.add(map);
    }
}
