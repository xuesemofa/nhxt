package org.consume.com.crew.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.consume.com.crew.model.CrewModel;

import java.util.List;

/**
 * @name 机组维护
 */
public interface CrewMapper {
    /**
     * add
     *
     * @param model HeatModel
     * @return int
     */
    @Insert({
            "insert into crew_table set uuid = #{model.uuid},names = #{model.names},parents = #{model.parents}" +
                    ",mj=#{model.mj},jzlx=#{model.jzlx}"
    })
    int add(@Param("model") CrewModel model);

    /**
     * del
     *
     * @param id String
     * @return int
     */
    @Delete({
            "delete from crew_table where uuid = #{id}"
    })
    int del(@Param("id") String id);

    @Delete({
            "delete from crew_table where parents = #{id}"
    })
    int delByParent(@Param("id") String id);

    /**
     * update
     *
     * @param model HeatModel
     * @return int
     */
    @Update({
            "update crew_table set names = #{model.names},parents = #{model.parents},mj=#{model.mj},jzlx=#{model.jzlx} where uuid = #{model.uuid}"
    })
    int update(@Param("model") CrewModel model);

    /**
     * findAllPage
     *
     * @return Page<HeatModel>
     */
    @Select({
            "select c.uuid,c.`names`,c.orders,h.`names` as parents,c.mj,b.names jzlx from crew_table c " +
                    " LEFT JOIN heat_table h on h.uuid = c.parents LEFT JOIN building_table b ON b.uuid = c.jzlx order by CONVERT(c.`names` USING gbk)"
    })
    Page<CrewModel> findAllPage();

    @Select({
            "select c.uuid,c.`names`,c.orders,h.`names` as parents,c.mj from crew_table c " +
                    " LEFT JOIN heat_table h on h.uuid = c.parents where c.`names` like #{serach} order by CONVERT(c.`names` USING gbk)"
    })
    Page<CrewModel> findAllPage1(@Param("serach") String serach);

    @Select({
            "select * from crew_table order by orders"
    })
    List<CrewModel> findAll();

    @Select({
            "select * from crew_table where names = #{names}"
    })
    CrewModel getByNames(@Param("names") String names);

    @Select({
            "select * from crew_table where uuid = #{id}"
    })
    CrewModel getById(@Param("id") String id);

    @Select({
            "select * from crew_table where parents = #{id}"
    })
    List<CrewModel> getByParents(@Param("id") String id);
}
