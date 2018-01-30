package org.consume.com.rwhwbj.mapper;

import org.apache.ibatis.annotations.Select;
import org.consume.com.rwgwbj.model.RwgwbjModel;

import java.util.List;

public interface RwhwbjMapper {
    @Select({"SELECT c.`names` AS jzid,d.T22 AS rwgw FROM datas_2_table d LEFT JOIN crew_table c " +
            " ON d.jzid = c.uuid GROUP BY jzid ORDER BY dates DESC"})
    List<RwgwbjModel> findNowByRwgw();
}
