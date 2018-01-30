package org.consume.com.hrzcbfxdl.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.hrzdl.model.HrzdlModel;

import java.util.List;

public interface HrzcbfxdlMapper {

    @Select({
            "SELECT"
                    + " 	s.dates,"
                    + " s.zdid,"
                    + " s.v acc_hea"
                    + " FROM"
                    + " 	sltj_table s"
                    + " WHERE"
                    + " 	s.dates = ("
                    + " 		SELECT"
                    + " 			MAX(dates)"
                    + " 		FROM"
                    + " 			sltj_table d"
                    + " 		WHERE"
                    + " 			d.dates > #{strDate}"
                    + " 		AND d.dates < #{endDate}"
                    + " 		AND d.zdid = #{id}"
                    + " 		AND d.lx = '2'"
                    + " 	) AND s.zdid = #{id} " +
                    " AND s.lx = '2'"
    })
    List<HrzdlModel> getByJzid(@Param("id") String id, @Param("strDate") long strDate, @Param("endDate") long endDate);

    @Select({
            " SELECT"
                    + " h.uuid jzid,"
                    + " h.`names` zd,"
                    + " CASE h.parents"
                    + " WHEN '0' THEN"
                    + " '总部'"
                    + " ELSE"
                    + " s.`names`"
                    + " END dw"
                    + " FROM"
                    + " heat_table h "
                    + " LEFT JOIN structure_table s ON s.uuid = h.parents where h.`names` like #{serach} ORDER BY dw,zd"
    })
    Page<ExchangeModel> findAllPage2(@Param("serach") String serach);

    @Select({
            " SELECT"
                    + " h.uuid jzid,"
                    + " h.`names` zd,"
                    + " CASE h.parents"
                    + " WHEN '0' THEN"
                    + " '总部'"
                    + " ELSE"
                    + " s.`names`"
                    + " END dw"
                    + " FROM"
                    + " heat_table h "
                    + " LEFT JOIN structure_table s ON s.uuid = h.parents ORDER BY dw,zd"
    })
    Page<ExchangeModel> findAllPage();
}
