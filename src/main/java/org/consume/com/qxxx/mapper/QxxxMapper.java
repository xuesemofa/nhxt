package org.consume.com.qxxx.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.qxxx.mapper.sql.SQL;
import org.consume.com.qxxx.model.QxxxModel;

import java.util.List;
import java.util.Map;

public interface QxxxMapper {
    @Select({
            "select * from qxxx_tq_table q order by q.dates desc LIMIT 1"
    })
    List<QxxxModel> findPage();

    @Select({
            "select * from qxxx_tq_table q where q.bs = #{bs} order by q.dates desc"
    })
    List<QxxxModel> findPage2(@Param("bs") String bs);

    @InsertProvider(type = SQL.class, method = "inserts")
    int add(Map<Integer, QxxxModel> map);
}
