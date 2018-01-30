package org.consume.com.ts.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.consume.com.ts.model.TsModel;

public interface TsMapper {

    @Select({
            "select t.uuid,t.dates,t.lx,t.ts_txt,u.username user_id from ts_table t " +
                    "left JOIN user_table u on u.uuid = t.user_id ORDER BY t.dates desc"
    })
    Page<TsModel> findPage();

    @Insert({
            "insert into ts_table set uuid = #{model.uuid},user_id=#{model.user_id},ts_txt=#{model.ts_txt}," +
                    "dates=#{model.dates},lx=#{model.lx}"
    })
    int add(@Param("model") TsModel model);

    @Update({
            "update ts_table set lx=#{lx} where uuid = #{id}"
    })
    int update_lx(@Param("id") String id, @Param("lx") int lx);
}
