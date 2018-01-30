package org.consume.com.exchange.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.exchange.model.OpcDatas2Model;
import org.consume.com.exchange.model.opcDatasModel;
import org.consume.com.opc.model.Datas2Model;

import java.util.List;

public interface ExchangeMapper {
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
                    + " LEFT JOIN structure_table s ON s.uuid = h.parents ORDER BY dw,zd,jz"
    })
    Page<ExchangeModel> findAllPage();


    @Select({
            "SELECT"
                    + " 	h. NAMES,"
                    + " 	h.choice,"
                    + " 	o.v,"
                    + " 	o.dates"
                    + " FROM"
                    + " 	heat_attribute_table h"
                    + " LEFT JOIN opc_datas_table o ON o.k = h.bs"
                    + " WHERE"
                    + " 	o.dates IN ("
                    + " 		SELECT"
                    + " 			MAX(o.dates) m"
                    + " 		FROM"
                    + " 			heat_attribute_table h"
                    + " 		LEFT JOIN opc_datas_table o ON o.k = h.bs"
                    + " 		WHERE"
                    + " 			h.jzid = #{id}"
                    + " 	)"
                    + " AND h.jzid = #{id} ORDER BY h.`names`"
    })
    List<opcDatasModel> getDatas(@Param("id") String id);

    @Select({
            "SELECT"
                    + "	d.*"
                    + " FROM"
                    + "	datas_2_table d"
                    + " LEFT JOIN crew_table c ON c.uuid = d.jzid"
                    + " WHERE"
                    + "	c.`names` = #{names}"
                    + " ORDER BY"
                    + "	d.dates DESC LIMIT 10"
    })
    List<Datas2Model> getByNames(@Param("names") String names);

    /**
     * 根据分公司id查询所有的机组信息
     *
     * @param id
     * @return
     */
    @Select({
            "SELECT"
                    + "      c.`names` k,GROUP_CONCAT("
                    + "           h.`names`,"
                    + "           ']',"
                    + "           h.orders,"
                    + "           ']',"
                    + "           h.orders,"
                    + "           ']',"
                    + "           o.dates,"
                    + "           ']',"
                    + "           o.k,"
                    + "           ']',"
                    + "           o.v"
                    + "      ) v"
                    + " FROM"
                    + "      heat_attribute_table h"
                    + " LEFT JOIN opc_datas_table o ON o.k = h.bs"
                    + " LEFT JOIN crew_table c ON c.uuid = h.jzid"
                    + " WHERE"
                    + "      o.dates IN ("
                    + "           SELECT"
                    + "                MAX(o.dates) m"
                    + "           FROM"
                    + "                heat_attribute_table h"
                    + "           LEFT JOIN opc_datas_table o ON o.k = h.bs"
                    + "           WHERE"
                    + "                h.jzid IN ("
                    + "                     SELECT"
                    + "                          c.uuid"
                    + "                     FROM"
                    + "                          heat_table h"
                    + "                     RIGHT JOIN crew_table c ON c.parents = h.uuid"
                    + "                     WHERE"
                    + "                          h.parents = #{id}"
                    + "                )"
                    + "      )"
                    + " AND h.jzid IN ("
                    + "      SELECT"
                    + "           c.uuid"
                    + "      FROM"
                    + "           heat_table h"
                    + "      RIGHT JOIN crew_table c ON c.parents = h.uuid"
                    + "      WHERE"
                    + "           h.parents = #{id}"
                    + " )"
                    + " GROUP BY"
                    + "      c.`names` order by c.`names`"
    })
    List<OpcDatas2Model> getDatas2(@Param("id") String id);
}
