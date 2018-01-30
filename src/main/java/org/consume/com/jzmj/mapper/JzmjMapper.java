package org.consume.com.jzmj.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.consume.com.jzmj.model.JzmjModel;

public interface JzmjMapper {
    @Insert({
            "insert into jzmj_table set uuid = #{model.uuid},jzid=#{model.jzid},jzmj=#{model.jzmj}"
    })
    int add(@Param("model") JzmjModel model);

    @Update({
            "update jzmj_table set jzmj = #{model.jzmj} where uuid= #{model.uuid}"
    })
    int update(@Param("model") JzmjModel model);

    @Delete({
            "delete from jzmj_table where uuid = #{id}"
    })
    int del(@Param("id") String id);

    @Select({
            "select j.uuid,j.jzmj,c.`names` jzid from jzmj_table j left join crew_table c on c.uuid = j.jzid order by CONVERT(c.`names` USING gbk)"
    })
    Page<JzmjModel> findPage();

    @Select({
            "select * from jzmj_table j where j.jzid=#{id}"
    })
    JzmjModel getByJzid(@Param("id") String id);
}
