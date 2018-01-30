package org.consume.com.aspect.service;

import org.consume.com.crew.model.CrewModel;
import org.consume.com.opc.model.Datas2Model;

public interface AspectService {
    double[] findById(String id, long a , long b );
    double findTqwdByTime(String a , String b );
    CrewModel findJzById(String id);
}
