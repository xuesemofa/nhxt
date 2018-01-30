package org.consume.com.opc.service;

import org.consume.com.opc.model.Datas2Model;

import java.util.List;
import java.util.Map;

public interface OPC2Service {

    int add(Datas2Model model);

    int save(Map<String, List<Datas2Model>> map);

    List<Datas2Model> findList(String id, String zd, String px);

    List<Datas2Model> findList2(String zd, String px);

    List<Datas2Model> getById(String id, long a, long b);

    /**
     * 根据日期获取最新数据，并插入新表
     * @param rq
     * @return
     */
    void getByDate(String rq);
}
