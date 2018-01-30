package org.consume.com.hrzrl.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.consume.com.hrzrl.mapper.sql.SQLS;
import org.consume.com.hrzrl.model.HrzrlFxModel;
import org.consume.com.hrzrl.model.HrzrlModel;
import org.consume.com.qxxx.model.QxxxModel;

import java.util.List;
import java.util.Map;

public interface HrzrlMapper {

    @Select({
            "SELECT"
                    + " 	c.`names`,"
                    + " d1.acc_hea," +
                    "d1.dates"
                    + " FROM"
                    + " 	datas_2_1_table d1"
                    + " left join crew_table c on c.uuid = d1.jzid"
                    + " WHERE"
                    + " 	d1.dates = ("
                    + " 		SELECT"
                    + " 			MAX(dates)"
                    + " 		FROM"
                    + " 			datas_2_1_table d"
                    + " 		WHERE"
                    + " 			 #{strDate} < d.dates < #{endDate}"
                    + " 		AND d.jzid = #{id}"
                    + " 	) and d1.jzid=#{id} ORDER BY d1.dates"
    })
    List<HrzrlModel> getByJzid(@Param("id") String id, @Param("strDate") long strDate, @Param("endDate") long endDate);

    //    @Select({
//            "SELECT"
//                    + " 	h.`names` hrzmc,"
//                    + " 	hj.hrzmj hrzmj,"
//                    + " 	SUM(d.acc_hea) / hrzmj hrzrdh," +
//                    " SUM(d.acc_hea) / hrzmj / 1514494722232 hrzrzb"
//                    + " FROM"
//                    + " 	heat_table h"
//                    + " LEFT JOIN hrzmj_table hj ON hj.jzid = h.uuid"
//                    + " LEFT JOIN crew_table c ON c.parents = h.uuid"
//                    + " LEFT JOIN datas_2_table d ON d.jzid = c.uuid"
//                    + " AND d.dates = ("
//                    + " 	SELECT"
//                    + " 		MAX(d1.dates)"
//                    + " 	FROM"
//                    + " 		datas_2_table d1"
//                    + " )"
//                    + " WHERE"
//                    + " 	h.parents = #{id}"
//                    + " GROUP BY"
//                    + " 	hrzmc,"
//                    + " 	hj.hrzmj"
//    })
    @SelectProvider(type = SQLS.class, method = "sel1")
    List<HrzrlFxModel> getJzs(Map<Integer, String> map);

    @SelectProvider(type = SQLS.class, method = "sel2")
    List<HrzrlFxModel> getJzs2(Map<Integer, String> map);

    @SelectProvider(type = SQLS.class, method = "sel5")
    List<HrzrlFxModel> getJzs3(Map<Integer, String> map);

    //    机组日单耗
//    @SelectProvider(type = SQLS.class, method = "sel3")
    @Select({
            "SELECT"
                    + " 	c.mj hrzmj,"
                    + " 	(d.acc_hea - d1.acc_hea) / c.mj hrzrdh,"
                    + " 	(d.acc_hea - d1.acc_hea) * 1000000 / c.mj / - #{rqc} hrzrzb"
                    + " FROM crew_table c"
                    + " LEFT JOIN datas_2_1_table d ON d.jzid = c.uuid"
                    + " AND from_unixtime(d.dates / 1000, '%Y-%m-%d') = #{rqa}"
                    + " LEFT JOIN datas_2_1_table d1 ON d1.jzid = c.uuid"
                    + " AND from_unixtime(d1.dates / 1000, '%Y-%m-%d') = #{rqb}"
                    + " WHERE"
                    + " 	c.uuid = #{id}"
                    + " GROUP BY"
                    + " 	c.mj"
    })
    List<HrzrlFxModel> getById(@Param("id") String id, @Param("rqa") String rqa, @Param("rqb") String rqb, @Param("rqc") long rqc);

    @Select({
            "SELECT"
                    + " 	from_unixtime(d.dates / 1000, '%Y-%m-%d') sj,"
                    + " 	d.acc_hea hrzrdh"
                    + " FROM"
                    + " 	datas_2_1_table d"
                    + " WHERE"
                    + " 	d.jzid = #{id} ORDER BY d.dates desc"
    })
    List<HrzrlFxModel> getById2(@Param("id") String id);

    @Select({
            "SELECT"
                    + "	from_unixtime(d.dates / 1000, '%Y-%m-%d') sj,"
                    + "	SUM(d.acc_hea) hrzrdh"
                    + " FROM"
                    + "	datas_2_1_table d LEFT JOIN crew_table c on c.uuid = d.jzid and c.parents=#{id}"
                    + " GROUP BY sj"
                    + " ORDER BY"
                    + "	d.dates DESC"
    })
    List<HrzrlFxModel> getById3(@Param("id") String id);

    //    @SelectProvider(type = SQLS.class, method = "sel4")
    @Select({
            "select * from qxxx_tq_table q where q.dates = (" +
                    "select MAX(q.dates) from qxxx_tq_table q where DATE_FORMAT(q.dates, '%Y-%m-%d') = #{rq})"
    })
    List<QxxxModel> findPage(@Param("rq") String rq);

    @Select({
            "SELECT"
                    + " d.acc_hea hrzrdh"
                    + " FROM"
                    + " 	datas_2_table d"
                    + " WHERE"
                    + " 	d.jzid = #{id}"
                    + " AND #{rqa} <= d.dates AND d.dates <= #{rqb}"
                    + " ORDER BY"
                    + " 	d.dates DESC"
    })
    List<HrzrlFxModel> getByIdAndRq(@Param("id") String id, @Param("rqa") long rqa, @Param("rqb") long rqb);

    @Select({
            "SELECT"
                    + " 	SUM(d.acc_hea) hrzrdh,"
                    + " 	d.jzid"
                    + " FROM"
                    + " 	datas_2_table d"
                    + " LEFT JOIN crew_table c ON c.uuid = d.jzid"
                    + " WHERE"
                    + " 	c.parents = #{id}"
                    + " AND #{rqa} <= d.dates AND d.dates <= #{rqb}"
                    + " GROUP BY"
                    + " 	d.jzid"
                    + " ORDER BY"
                    + " 	d.dates DESC"
    })
    List<HrzrlFxModel> getByIdAndRq2(@Param("id") String id, @Param("rqa") long rqa, @Param("rqb") long rqb);

    @Select({
            "select * from qxxx_tq_table q where UNIX_TIMESTAMP(q.dates) >=  #{rqa} and UNIX_TIMESTAMP(q.dates) <= #{rqb}"
    })
    List<QxxxModel> getByQxRq(@Param("rqa") long rqa, @Param("rqb") long rqb);
}
