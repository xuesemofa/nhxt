package org.consume.com.unitcomparison.service;

import com.github.pagehelper.Page;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.unitcomparison.model.UnitComparisonModel;

public interface UnitcomparisonService {
    Page<ExchangeModel> findAllPage(int pageNow, int pageSize, String serach);

    int add(UnitComparisonModel model);

    Page<UnitComparisonModel> findAllPage2(int pageNow, int pageSize);

    Page<UnitComparisonModel> findAllPage3(int pageNow, int pageSize);

    UnitComparisonModel getById(String id);

    int del(String id);
}
