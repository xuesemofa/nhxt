package org.consume.com.exchange.service;

import com.github.pagehelper.Page;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.exchange.model.OpcDatas2Model;
import org.consume.com.exchange.model.opcDatasModel;
import org.consume.com.opc.model.Datas2Model;

import java.util.List;

public interface ExchangeService {
    Page<ExchangeModel> findAllPage(int pageNow, int pageSize);

    //根据分公司查询数据
    List<opcDatasModel> getDatas(String id);

    List<OpcDatas2Model> getDatas2(String id);

    //根据机组名称查询数据
    List<Datas2Model> getByNames(String names);

}
