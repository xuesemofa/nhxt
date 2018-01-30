package org.consume.com.qxxx.service;

import com.github.pagehelper.Page;
import org.consume.com.qxxx.model.QxxxModel;

import java.util.List;
import java.util.Map;

public interface QxxxService {

    List<QxxxModel> findPage(int pageNow, int pageSize);

    int add(Map<Integer, QxxxModel> map);
}
