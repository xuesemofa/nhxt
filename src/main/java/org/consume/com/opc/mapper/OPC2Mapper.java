package org.consume.com.opc.mapper;

import org.apache.ibatis.annotations.*;
import org.consume.com.opc.mapper.sql.SQLS;
import org.consume.com.opc.model.Datas2Model;

import java.util.List;
import java.util.Map;

public interface OPC2Mapper {

    @Insert({
            "insert into datas_2_table set "
                    + " uuid = #{model.uuid},"
                    + " T11=#{model.T11},"
                    + " T12=#{model.T12},"
                    + " P11=#{model.P11},"
                    + " P12=#{model.P12},"
                    + " ins_hea=#{model.ins_hea},"
                    + " ins_flo=#{model.ins_flo},"
                    + " acc_hea=#{model.acc_hea},"
                    + " T21=#{model.T21},"
                    + " T22=#{model.T22},"
                    + " P21=#{model.P21},"
                    + " P22=#{model.P22},"
                    + " lev=#{model.lev},"
                    + " mot_bac=#{model.mot_bac},"
                    + " bf_bac1=#{model.bf_bac1},"
                    + " xf_bac1=#{model.xf_bac1},"
                    + " xI_bac1=#{model.xI_bac1},"
                    + " xf_bac2=#{model.xf_bac2},"
                    + " xI_bac2=#{model.xI_bac2},"
                    + " bl1=#{model.bl1},"
                    + " bl2=#{model.bl2},"
                    + " bl3=#{model.bl3},"
                    + " bl4=#{model.bl4},"
                    + " bl5=#{model.bl5},"
                    + " bl6=#{model.bl6},"
                    + " bl7=#{model.bl7},"
                    + " bl8=#{model.bl8},"
                    + " bl9=#{model.bl9},"
                    + " bl10=#{model.bl10}"
    })
    int add(@Param("model") Datas2Model model);

    //    此注解多个参数必须map类型
    @InsertProvider(type = SQLS.class, method = "inserts2")
    int save(Map<String, List<Datas2Model>> map);

    @Select({
            "SELECT"
                    + "      c.`names` jzid,"
                    + "      d.T22,"
                    + "      d.T21,"
                    + "      d.T12,"
                    + "      d.T11,"
                    + "      d.P22,"
                    + "      d.P21,"
                    + "      d.P12,"
                    + "      d.P11,"
                    + "      d.mot_bac,"
                    + "      d.lev,"
                    + "      d.ins_hea,"
                    + "      d.ins_flo,"
                    + "      d.acc_hea,"
                    + "      d.xI_bac2,"
                    + "      d.xf_bac2,"
                    + "      d.xI_bac1,"
                    + "      d.xf_bac1,"
                    + "      d.bf_bac1,"
                    + "      d.dates"
                    + " FROM"
                    + "      datas_2_table d"
                    + " LEFT JOIN crew_table c ON c.uuid = d.jzid"
                    + " WHERE"
                    + "      c.uuid IN ("
                    + "           SELECT"
                    + "                c.uuid"
                    + "           FROM"
                    + "                crew_table c"
                    + "           WHERE"
                    + "                c.parents IN ("
                    + "                     SELECT"
                    + "                          uuid"
                    + "                     FROM"
                    + "                          heat_table"
                    + "                     WHERE"
                    + "                          parents = #{id}"
                    + "                )"
                    + "      )"
                    + " AND d.dates IN ("
                    + "      SELECT"
                    + "           MAX(d2.dates) dates"
                    + "      FROM"
                    + "           datas_2_table d2"
                    + " )"
                    + " ORDER BY"
                    + "      c.`names`"
//            " SELECT"
//                    + " 	CONCAT(h3.`names`, ',', c3.`names`) jzid,"
//                    + " 	d3.T22,"
//                    + " 	d3.T21,"
//                    + " 	d3.T12,"
//                    + " 	d3.T11,"
//                    + " 	d3.P22,"
//                    + " 	d3.P21,"
//                    + " 	d3.P12,"
//                    + " 	d3.P11,"
//                    + " 	d3.mot_bac,"
//                    + " 	d3.lev,"
//                    + " 	d3.ins_hea,"
//                    + " 	d3.ins_flo,"
//                    + " 	d3.acc_hea,"
//                    + " 	d3.xI_bac2,"
//                    + " 	d3.xf_bac2,"
//                    + " 	d3.xI_bac1,"
//                    + " 	d3.xf_bac1,"
//                    + " 	d3.bf_bac1,"
//                    + " 	d3.dates"
//                    + " FROM"
//                    + " 	datas_2_table d3"
//                    + " LEFT JOIN crew_table c3 ON c3.uuid = d3.jzid"
//                    + " LEFT JOIN heat_table h3 ON h3.uuid = c3.parents"
//                    + " WHERE"
//                    + " 	d3.uuid IN ("
//                    + " 		SELECT"
//                    + " 			d.uuid"
//                    + " 		FROM"
//                    + " 			datas_2_table d"
//                    + " 		LEFT JOIN crew_table c ON c.uuid = d.jzid"
//                    + " 		WHERE"
//                    + " 			c.uuid IN ("
//                    + " 				SELECT"
//                    + " 					c.uuid"
//                    + " 				FROM"
//                    + " 					crew_table c"
//                    + " 				WHERE"
//                    + " 					c.parents IN ("
//                    + " 						SELECT"
//                    + " 							uuid"
//                    + " 						FROM"
//                    + " 							heat_table"
//                    + " 						WHERE"
//                    + " 							parents = '8dc9315a8e4b43a58367ffe23c05458f'"
//                    + " 					)"
//                    + " 			)"
//                    + " 		AND d.dates IN ("
//                    + " 			SELECT"
//                    + " 				MAX(d2.dates) dates"
//                    + " 			FROM"
//                    + " 				datas_2_table d2"
//                    + " 		)"
//                    + " 		ORDER BY"
//                    + " 			c.`names`"
//                    + " 	)  ORDER BY h3.`names`"
    })
    List<Datas2Model> findList(@Param("id") String id);

    @SelectProvider(type = SQLS.class, method = "sel1")
    List<Datas2Model> findList2(Map<Integer, String> map);

    @SelectProvider(type = SQLS.class, method = "sel2")
    List<Datas2Model> findList3(Map<Integer, String> map);

    @Select({
            "SELECT" +
                    " * " +
                    " FROM " +
                    " datas_2_table d2 " +
                    " WHERE " +
                    " d2.dates = (select MAX(d.dates) dates from datas_2_table d where d.dates >= #{a} "
                    + " and d.dates <= #{b} and d.jzid = #{id}) AND d2.jzid =#{id}"
    })
    List<Datas2Model> getById(@Param("id") String id, @Param("a") long a, @Param("b") long b);

    //    获取指定日期的最新数据
    @Select({
            "select * from datas_2_table d1 where d1.dates = (" +
                    "select MAX(d.dates) from datas_2_table d where from_unixtime(d.dates/1000,'%Y-%m-%d') " +
                    "= #{rq} ORDER BY d.dates DESC)"
    })
    List<Datas2Model> getByDate(@Param("rq") String rq);

    //插入每天的最大值
    @Insert({
            "insert into datas_2_1_table set "
                    + " uuid = #{model.uuid},jzid=#{model.jzid},dates=#{model.dates},"
                    + " T11=#{model.T11},"
                    + " T12=#{model.T12},"
                    + " P11=#{model.P11},"
                    + " P12=#{model.P12},"
                    + " ins_hea=#{model.ins_hea},"
                    + " ins_flo=#{model.ins_flo},"
                    + " acc_hea=#{model.acc_hea},"
                    + " T21=#{model.T21},"
                    + " T22=#{model.T22},"
                    + " P21=#{model.P21},"
                    + " P22=#{model.P22},"
                    + " lev=#{model.lev},"
                    + " mot_bac=#{model.mot_bac},"
                    + " bf_bac1=#{model.bf_bac1},"
                    + " xf_bac1=#{model.xf_bac1},"
                    + " xI_bac1=#{model.xI_bac1},"
                    + " xf_bac2=#{model.xf_bac2},"
                    + " xI_bac2=#{model.xI_bac2},"
                    + " bl1=#{model.bl1},"
                    + " bl2=#{model.bl2},"
                    + " bl3=#{model.bl3},"
                    + " bl4=#{model.bl4},"
                    + " bl5=#{model.bl5},"
                    + " bl6=#{model.bl6},"
                    + " bl7=#{model.bl7},"
                    + " bl8=#{model.bl8},"
                    + " bl9=#{model.bl9},"
                    + " bl10=#{model.bl10}"
    })
    int add2(@Param("model") Datas2Model model);

    //    根据日期查询新表数据
    @Delete({
            "delete from datas_2_1_table where from_unixtime(dates/1000,'%Y-%m-%d') = #{rq}"
    })
    int delByDate(@Param("rq") String rq);

}
