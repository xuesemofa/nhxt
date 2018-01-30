package org.consume.com.unitcomparison.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.unitcomparison.model.UnitComparisonModel;

public interface UnitcomparisonMapper {
    @Select({
            " SELECT"
                    + " c.uuid jzid,"
                    + " c.`names` jz,"
                    + " h.`names` zd,"
                    + " CASE h.parents"
                    + " WHEN '0' THEN"
                    + " '总部'"
                    + " ELSE"
                    + " s.`names`"
                    + " END dw"
                    + " FROM"
                    + " crew_table c"
                    + " LEFT JOIN heat_table h ON h.uuid = c.parents"
                    + " LEFT JOIN structure_table s ON s.uuid = h.parents ORDER BY s.ordes,zd,jz"
    })
    Page<ExchangeModel> findAllPage();

    @Select({
            " SELECT"
                    + " c.uuid jzid,"
                    + " c.`names` jz,"
                    + " h.`names` zd,"
                    + " CASE h.parents"
                    + " WHEN '0' THEN"
                    + " '总部'"
                    + " ELSE"
                    + " s.`names`"
                    + " END dw"
                    + " FROM"
                    + " crew_table c"
                    + " LEFT JOIN heat_table h ON h.uuid = c.parents"
                    + " LEFT JOIN structure_table s ON s.uuid = h.parents where c.`names` like #{serach} ORDER BY s.ordes,zd,jz"
    })
    Page<ExchangeModel> findAllPage2(@Param("serach") String serach);

    @Select({
            "SELECT",
            "u.uuid,",
            "CONCAT(",
            "        s1.`names`,",
            "        '-',",
            "        h1.`names`,",
            "        '-',",
            "        c1.`names`",
            ") jzaid,",
            "CONCAT(",
            "       s2.`names`,",
            "        '-',",
            "        h2.`names`,",
            "        '-',",
            "        c2.`names`",
            ") jzbid,",
            "u.accid,",
            "u.scsj",
            "FROM",
            "unit_comparison_table u",
            "LEFT JOIN crew_table c1 ON c1.uuid = u.jzaid",
            "LEFT JOIN heat_table h1 ON h1.uuid = c1.parents",
            "LEFT JOIN structure_table s1 ON s1.uuid = h1.parents",
            "LEFT JOIN crew_table c2 ON c2.uuid = u.jzbid",
            "LEFT JOIN heat_table h2 ON h2.uuid = c2.parents",
            "LEFT JOIN structure_table s2 ON s2.uuid = h2.parents",
            "ORDER BY u.scsj"
    })
    Page<UnitComparisonModel> findAllPage3();

    @Insert({
            "insert into unit_comparison_table " +
                    " set uuid = #{model.uuid},jzaid = #{model.jzaid}," +
                    " jzbid = #{model.jzbid},scsj = #{model.scsj},accid = #{model.accid},zhlx = #{model.zhlx}"
    })
    int add(@Param("model") UnitComparisonModel model);

    @Select({
            "select * from unit_comparison_table u where u.uuid = #{id}"
    })
    UnitComparisonModel getById(@Param("id") String id);

    @Delete({
            "delete from unit_comparison_table where uuid = #{id}"
    })
    int del(@Param("id") String id);

    @Select({"select * from unit_comparison_table where zhlx = 1 or zhlx = 2 ORDER BY scsj DESC"})
    Page<UnitComparisonModel> findAllPage4();
}
