package org.consume.com.hrzddhpm.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.sltj.model.SltjModel;

import java.util.List;

public interface HrzddhpmMapper {
    @Select({"SELECT * FROM sltj_table WHERE zdid IN (#{obj[0]},#{obj[1]},#{obj[2]},#{obj[3]},#{obj[4]},#{obj[5]},#{obj[6]}" +
            "#{obj[7]}) AND lx ='2' ORDER BY zdid,dates "})
    List<SltjModel> findHrzddhpm(@Param("obj")  List<String> object);
}
