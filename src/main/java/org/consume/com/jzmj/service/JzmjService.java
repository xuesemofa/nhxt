package org.consume.com.jzmj.service;

import com.github.pagehelper.Page;
import org.consume.com.jzmj.model.JzmjModel;

public interface JzmjService {
    int add(JzmjModel model);
    int update(JzmjModel model);

    int del(String id);

    Page<JzmjModel> findPage(int pageNow, int pageSize);

    JzmjModel getByJzid(String id);
}
