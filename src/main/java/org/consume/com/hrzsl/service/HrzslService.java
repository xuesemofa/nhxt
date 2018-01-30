package org.consume.com.hrzsl.service;

import com.github.pagehelper.Page;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.hrzdl.model.HrzdlModel;
import org.consume.com.hrzsl.model.HrzslModel;
import org.consume.com.sltj.model.SltjModel;

import java.util.List;

public interface HrzslService {
    List<HrzdlModel> getByJzid(String id, long strDate, long endDate);
    Page<ExchangeModel> findAllPage(int pageNow, int pageSize, String serach);
    List<HrzslModel> findById(String id);
}
