package org.consume.com.hrzcbfxrl.mapper.sql;

import java.util.Map;

public class SQLS {

    public String sel1(Map<Integer, String> map) {
        String s = " CONVERT(hrzmc USING gbk)";
        String q = " ";
        if (map.containsKey(2) && map.containsKey(3)) {
            s = map.get(2).equals("") ? " CONVERT(hrzmc USING gbk)" : map.get(2);
            q += map.get(3);
        }
        String sql = "SELECT"
                + " 	h.`names` hrzmc,"
                + " 	h.mj hrzmj,"
                + " 	SUM(d.acc_hea) / h.mj hrzrdh,"
                + " 	SUM(d.acc_hea) * 1000000 / h.mj / " + map.get(6) + " hrzrzb"
                + " FROM"
                + " 	heat_table h"
                + " LEFT JOIN crew_table c ON c.parents = h.uuid"
                + " LEFT JOIN datas_2_1_table d ON d.jzid = c.uuid"
                + " AND from_unixtime(d.dates/1000,'%Y-%m-%d') = '" + map.get(4) + "'"
                + " WHERE"
                + " 	h.parents = '" + map.get(1) + "'"
                + " GROUP BY"
                + " 	hrzmc,"
                + " 	h.mj"
                + " ORDER BY ";
        return sql + s + q;
    }

    public String sel2(Map<Integer, String> map) {
        String s = " CONVERT(hrzmc USING gbk)";
        String q = " ";
        if (map.containsKey(2) && map.containsKey(3)) {
            s = map.get(2).equals("") ? " CONVERT(hrzmc USING gbk)" : map.get(2);
            q += map.get(3);
        }
        String sql = "SELECT"
                + " 	h.`names` hrzmc,"
                + " 	h.mj hrzmj,d.acc_hea ywljrl,"
                + " 	d.acc_hea / h.mj hrzrdh,"
                + " 	d.acc_hea * 1000000 / h.mj / " + map.get(6) + " hrzrzb"
                + " FROM"
                + " 	heat_table h"
                + " LEFT JOIN crew_table c ON c.parents = h.uuid"
                + " LEFT JOIN datas_2_1_table d ON d.jzid = c.uuid"
                + " AND from_unixtime(d.dates/1000,'%Y-%m-%d') = '" + map.get(4) + "'"
                + " WHERE"
                + " 	h.parents = '" + map.get(1) + "'"
                + " GROUP BY"
                + " 	hrzmc,"
                + " 	h.mj"
                + " ORDER BY ";
        return sql + s + q;
    }

    /**
     * 务必保留此sql语句
     * select distinct from_unixtime(dates/1000,'%Y-%m-%d') dates,c.`names` from datas_2_table
     * LEFT JOIN crew_table c on c.uuid = jzid GROUP BY dates order by dates asc
     *
     * @param map
     * @return
     */
    public String sel3(Map<Integer, String> map) {
        String s = " CONVERT(sj USING gbk)";
        String q = " desc";
        if (map.containsKey(2) && map.containsKey(3)) {
            s = map.get(2);
            q += map.get(3);
        }
        String sql = "SELECT"
                + " 	d.dates sj,"
                + " 	c.`names` jzmc,"
                + " 	c.mj jzmj,"
                + " 	d.acc_hea / c.mj rhrl,"
                + " 	d.acc_hea*1000000 / c.mj / " + map.get(4) + " rrzb"
                + " FROM"
                + " 	datas_2_table d"
                + " LEFT JOIN crew_table c ON c.uuid = d.jzid"
                + " LEFT JOIN jzmj_table j ON j.jzid = c.uuid"
                + " WHERE"
                + " 	d.jzid = '" + map.get(1) + "'"
                + " AND d.dates = ("
                + " 	SELECT"
                + " 		MAX(d1.dates)"
                + " 	FROM"
                + " 		datas_2_table d1 where d1.dates > " + map.get(5) + " and d1.dates < " + map.get(6)
                + " ) ORDER BY ";
        return sql + s + q;
    }

    public String sel4(Map<Integer, String> map) {
        String sql = "SELECT"
                + " 	*"
                + " FROM"
                + " 	qxxx_tq_table q1"
                + " WHERE"
                + " 	q1.dates = ("
                + " 		SELECT"
                + " 			MAX(q.dates)"
                + " 		FROM"
                + " 			qxxx_tq_table q where date(q.dates) = date('" + map.get(0) + "')"
                + " 	)";
        return sql;
    }

}
