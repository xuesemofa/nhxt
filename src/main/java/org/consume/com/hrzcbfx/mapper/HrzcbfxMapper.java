package org.consume.com.hrzcbfx.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.hrzcbfx.model.HrzcbfxModel;

import java.util.List;

public interface HrzcbfxMapper {


    @Select({
            "SELECT"
                    + " 	h.`names` uuid,"
                    + " CASE WHEN SUM(d.acc_hea) is null THEN 0"
                    + " ELSE SUM(d.acc_hea) END v,"
                    + " CASE WHEN s.v is null THEN 0"
                    + " ELSE s.v END v1,"
                    + " CASE WHEN s2.v is null THEN 0"
                    + " ELSE s2.v END v2"
                    + " FROM"
                    + " 	heat_table h"
                    + " left JOIN crew_table c ON c.parents = h.uuid"
                    + " left JOIN datas_2_table d ON d.jzid = c.uuid"
                    + " AND d.dates = ("
                    + " 	SELECT"
                    + " 		MAX(d1.dates)"
                    + " 	FROM"
                    + " 		datas_2_table d1"
                    + " )"
                    + " left JOIN sltj_table s ON s.zdid = h.uuid"
                    + " AND s.dates = ("
                    + " 	SELECT"
                    + " 		MAX(s1.dates)"
                    + " 	FROM"
                    + " 		sltj_table s1"
                    + " 	WHERE"
                    + " 		s1.lx = '1'"
                    + " )"
                    + " AND s.lx = '1'"
                    + " left JOIN sltj_table s2 ON s2.zdid = h.uuid"
                    + " AND s2.dates = ("
                    + " 	SELECT"
                    + " 		MAX(s3.dates)"
                    + " 	FROM"
                    + " 		sltj_table s3"
                    + " 	WHERE"
                    + " 		s3.lx = '2'"
                    + " )"
                    + " AND s2.lx = '2'"
                    + " WHERE"
                    + " 	h.parents = #{id}"
                    + " GROUP BY"
                    + " 	h.`names`"
    })
    List<HrzcbfxModel> queryByCompany(@Param("id") String id);
}
