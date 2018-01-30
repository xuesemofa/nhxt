package org.consume.com.hrzrl.service;

import org.consume.com.hrzrl.model.HrzrlFxModel;
import org.consume.com.hrzrl.model.HrzrlModel;
import org.consume.com.qxxx.model.QxxxModel;

import java.util.List;
import java.util.Map;

public interface HrzrlService {
    List<HrzrlModel> getByJzid(String id, long strDate, long endDate);

    List<HrzrlFxModel> getJzs(Map<Integer, String> map);

    List<HrzrlFxModel> getJzs2(Map<Integer, String> map);

    List<HrzrlFxModel> getJzs3(Map<Integer, String> map);

    List<HrzrlFxModel> getById(String id, String rqa, String rqb, long rqc);

    List<HrzrlFxModel> getById2(String id, long l);

    List<HrzrlFxModel> getById3(String id, long l);

    List<QxxxModel> findPage(String rq);

    List<HrzrlFxModel> getByIdAndRq(String id,Map<Integer,long[]> map);
    List<HrzrlFxModel> getByIdAndRq2(String id,Map<Integer,long[]> map);
}
