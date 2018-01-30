package org.consume.com.hrzcbfxdl.service;

import com.github.pagehelper.Page;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.hrzdl.model.HrzdlModel;

import java.util.List;

public interface HrzcbfxdlService {
    List<HrzdlModel> getByJzid(String id, long strDate, long endDate);
    Page<ExchangeModel> findAllPage(int pageNow, int pageSize, String serach);
}
