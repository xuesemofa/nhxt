package org.consume.com.hrzcbfxwx.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface HrzcbfxwxMapper {
    @Update({
            "update heat_table h set wx = #{wx} where uuid = #{id}"
    })
    void setWx(@Param("id") String id, @Param("wx") double wx);
}
