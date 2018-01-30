package org.consume.com.ryssgk.service;

import org.consume.com.hrzrl.model.HrzrlFxModel;
import org.consume.com.opc.model.Datas3Model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface RyssgkService {
    List<Datas3Model> findAll();

    List<Datas3Model> findByFour(String t);

    Datas3Model findNewest();

    List<HrzrlFxModel> findByRq(Map<Integer, long[]> map,String[] split1);
    List<HrzrlFxModel> findByRq2(Map<Integer, long[]> map,String[]split1);
}
