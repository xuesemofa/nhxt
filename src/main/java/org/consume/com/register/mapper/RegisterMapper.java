package org.consume.com.register.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.consume.com.register.model.RegisterModel;

/**
 * @name 注册记录
 */
public interface RegisterMapper {
    @Insert({
            "insert into register_table values (#{model.uuid},#{model.account},#{model.systimes})"
    })
    int add(@Param("model") RegisterModel model);
}
