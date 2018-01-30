package org.consume.com.bx.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.consume.com.bx.model.BxModel;

public interface BxMapper {

    @Select({
            "select t.uuid,t.dates,t.lx,t.bx_txt,u.username user_id from bx_table t " +
                    "left JOIN user_table u on u.uuid = t.user_id ORDER BY t.dates desc"
    })
    Page<BxModel> findPage();

    @Insert({
            "insert into bx_table set uuid = #{model.uuid},user_id=#{model.user_id},bx_txt=#{model.bx_txt}," +
                    "dates=#{model.dates},lx=#{model.lx}"
    })
    int add(@Param("model") BxModel model);

    @Update({
            "update bx_table set lx=#{lx} where uuid = #{id}"
    })
    int update_lx(@Param("id") String id, @Param("lx") int lx);
}
