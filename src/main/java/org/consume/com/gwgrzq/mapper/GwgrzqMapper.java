package org.consume.com.gwgrzq.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.gwgrzq.model.GwgrzqModel;

import java.sql.Timestamp;
import java.util.List;

public interface GwgrzqMapper {

    @Insert({
            "insert into gwgrzq_table set strat_day = #{k},end_day=#{v}"
    })
    void setZq(@Param("k") Timestamp k, @Param("v") Timestamp v);

    @Delete({
            "delete from gwgrzq_table"
    })
    void del();

    @Select({
            "select strat_day,end_day from gwgrzq_table g"
    })
    List<GwgrzqModel> find();
}
