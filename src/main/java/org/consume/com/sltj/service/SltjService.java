package org.consume.com.sltj.service;

import org.consume.com.sltj.model.SltjModel;

import java.util.List;
import java.util.Map;

public interface SltjService {
    int add(Map<String, List<SltjModel>> map);

    List<SltjModel> get(String id, String lx);

    List<SltjModel> findById(String id);

    List<SltjModel> findByTimeAndId(String id, long l, long ll);

    List<SltjModel> findById2(String id);

    //水
    List<SltjModel> findByTimeAndId2(String id, long l, long ll);

    //    电
    List<SltjModel> findByTimeAndId3(String id, long l, long ll);
}
