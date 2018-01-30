package org.consume.com.sjsx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.consume.com.sjsx.model.SjsxModel;

public interface SjsxMapper {

    @Insert({
            "insert into sjsx_table set uuid = #{model.uuid},v=#{model.v},dates = #{model.dates},zdid = #{model.zdid}"
    })
    int add(@Param("model") SjsxModel model);
}
