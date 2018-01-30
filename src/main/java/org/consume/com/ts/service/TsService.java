package org.consume.com.ts.service;

import com.github.pagehelper.Page;
import org.consume.com.ts.model.TsModel;

public interface TsService {
    Page<TsModel> findPage(int pageNow,int pageSize);

    int add(TsModel model);

    int update_lx(String id, int lx);
}
