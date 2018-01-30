package org.consume.com.rwgwbj.mapper;

import org.apache.ibatis.annotations.Select;
import org.consume.com.rwgwbj.model.RwgwbjModel;

import java.util.List;

public interface RwgwbjMapper {
    @Select({"SELECT c.`names` AS jzid,d.T21 AS rwgw FROM datas_2_table d LEFT JOIN crew_table c " +
            "ON d.jzid = c.uuid GROUP BY jzid ORDER BY dates DESC"})
    List<RwgwbjModel> findNowByRwgw();
}
