package org.consume.com.bx.service;

import com.github.pagehelper.Page;
import org.consume.com.bx.model.BxModel;

public interface BxService {
    Page<BxModel> findPage(int pageNow, int pageSize);

    int add(BxModel model);

    int update_lx(String id, int lx);
}
