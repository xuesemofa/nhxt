package org.consume.com.sltj.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.sltj.mapper.sql.SQLS;
import org.consume.com.sltj.model.SltjModel;

import java.util.List;
import java.util.Map;

public interface SltjMapper {
    @InsertProvider(type = SQLS.class, method = "inserts")
    int add(Map<String, List<SltjModel>> map);

    @Select({
            "select * from sltj_table s1 where s1.dates in (" +
                    "select MAX(s.dates) from sltj_table s where s.zdid = #{id} and s.lx=#{lx}) and s1.zdid = #{id} and s1.lx=#{lx}"
    })
    List<SltjModel> get(@Param("id") String id, @Param("lx") String lx);

    @Select({"SELECT " +
            "s.uuid, " +
            "s.v, " +
            "s.dates, " +
            "h.`names` zdid, " +
            "h.mj lx,st.`names` fgs,b.`names` jzwlx  " +
            "FROM " +
            "sltj_table s " +
            "RIGHT JOIN heat_table h ON h.uuid = s.zdid " +
            "RIGHT JOIN structure_table st ON st.uuid = h.parents " +
            "AND st.uuid = #{id} LEFT JOIN building_table b ON b.uuid = h.jzlx " +
            "WHERE " +
            "s.lx = '2' " +
            "AND " +
            "dates = (SELECT MAX(dates) FROM sltj_table WHERE sltj_table.lx = '2')"})
    List<SltjModel> findById(@Param("id") String id);

    @Select({"SELECT s.uuid,s.v,s.dates,h.`names` zdid,h.mj lx,b.`names` jzwlx  FROM sltj_table s LEFT JOIN heat_table h ON " +
            "s.zdid = h.uuid LEFT JOIN building_table b ON b.uuid = h.jzlx  where s.lx = '2' AND s.zdid = #{id}  AND #{l} < dates < #{ll} ORDER BY zdid,dates"})
    List<SltjModel> findByTimeAndId(@Param("id") String id, @Param("l") long l, @Param("ll") long ll);

    @Select({"SELECT " +
            "s.uuid, " +
            "s.v, " +
            "s.dates, " +
            "h.`names` zdid, " +
            "h.mj lx,st.`names` fgs,b.`names` jzwlx " +
            "FROM " +
            "sltj_table s " +
            "RIGHT JOIN heat_table h ON h.uuid = s.zdid " +
            "RIGHT JOIN structure_table st ON st.uuid = h.parents " +
            "AND st.uuid = #{id} LEFT JOIN building_table b ON b.uuid = h.jzlx " +
            "WHERE " +
            "s.lx = '1' " +
            "AND " +
            "dates = (SELECT MAX(dates) FROM sltj_table WHERE sltj_table.lx = '1')"})
    List<SltjModel> findById2(@Param("id") String id);

    @Select({"SELECT"
            + " 	s.uuid,"
            + " 	s.v,"
            + " 	FROM_UNIXTIME(s.dates / 1000),"
            + " 	h.`names` zdid,"
            + " 	h.mj lx"
            + " FROM"
            + " 	sltj_table s"
            + " LEFT JOIN heat_table h ON h.uuid = s.zdid"
            + " WHERE"
            + " 	s.lx = '1'"
            + " AND s.dates >= #{l}"
            + " AND s.dates <= #{ll}"
            + " AND s.zdid = #{id}"
            + " ORDER BY dates desc"})
    List<SltjModel> findByTimeAndId2(@Param("id") String id, @Param("l") long l, @Param("ll") long ll);

    @Select({"SELECT"
            + " 	s.uuid,"
            + " 	s.v,"
            + " 	FROM_UNIXTIME(s.dates / 1000),"
            + " 	h.`names` zdid,"
            + " 	h.mj lx"
            + " FROM"
            + " 	sltj_table s"
            + " LEFT JOIN heat_table h ON h.uuid = s.zdid"
            + " WHERE"
            + " 	s.lx = '2'"
            + " AND s.dates >= #{l}"
            + " AND s.dates <= #{ll}"
            + " AND s.zdid = #{id}"
            + " ORDER BY dates desc"})
    List<SltjModel> findByTimeAndId3(@Param("id") String id, @Param("l") long l, @Param("ll") long ll);

}
