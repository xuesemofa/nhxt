package org.consume.com.rymj.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.rymj.model.RymjModel;

import java.util.List;

public interface RymjMapper {
    @Insert({
            "INSERT into rymj_table(mj) value(#{model.mj})"
    })
    int set(@Param("model") RymjModel model);

    @Delete({
            "delete from rymj_table"
    })
    void del();

    @Select({
            "select * from rymj_table"
    })
    List<RymjModel> get();
}
