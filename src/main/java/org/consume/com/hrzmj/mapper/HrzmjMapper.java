package org.consume.com.hrzmj.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.consume.com.hrzmj.model.HrzmjModel;

public interface HrzmjMapper {
    @Insert({
            "insert into hrzmj_table set uuid = #{model.uuid},jzid=#{model.jzid},hrzmj=#{model.hrzmj}"
    })
    int add(@Param("model") HrzmjModel model);

    @Update({
            "update hrzmj_table set hrzmj = #{model.hrzmj} where uuid = #{model.uuid}"
    })
    int update(@Param("model")HrzmjModel model);

    @Delete({
            "delete from hrzmj_table where uuid = #{id}"
    })
    int del(@Param("id") String id);

    @Select({
            "select j.uuid,j.hrzmj,c.`names` jzid from hrzmj_table j left join heat_table c on c.uuid = j.jzid order by CONVERT(c.`names` USING gbk)"
    })
    Page<HrzmjModel> findPage();

    @Select({
            "select * from hrzmj_table j where j.jzid=#{id}"
    })
    HrzmjModel getByJzid(@Param("id") String id);
}
