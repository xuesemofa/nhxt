package org.consume.com.rltj.service.serviceImpl;

import org.consume.com.opc.model.Datas3Model;

import java.util.List;

public interface RltjService {
    Datas3Model findYesterday();
    Datas3Model findBeforeYesterday();
    List<Datas3Model> findWeek();
}
