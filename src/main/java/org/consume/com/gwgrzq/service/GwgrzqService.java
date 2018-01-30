package org.consume.com.gwgrzq.service;

import org.consume.com.gwgrzq.model.GwgrzqModel;

import java.sql.Timestamp;
import java.util.List;

public interface GwgrzqService {
    void setZq(Timestamp k, Timestamp v);

    void del();

    List<GwgrzqModel> find();
//供暖开始日期至现在的秒
    long getmm();
}
