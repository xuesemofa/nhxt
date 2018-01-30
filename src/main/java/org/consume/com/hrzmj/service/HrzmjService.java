package org.consume.com.hrzmj.service;

import com.github.pagehelper.Page;
import org.consume.com.hrzmj.model.HrzmjModel;

import java.util.Map;

public interface HrzmjService {
    int add(HrzmjModel model);
    int update(HrzmjModel model);

    int del(String id);

    Page<HrzmjModel> findPage(int pageNow, int pageSize);

    HrzmjModel getByJzid(String id);
}
