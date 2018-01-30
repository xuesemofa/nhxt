package org.consume.com.ryssgk.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.opc.model.Datas3Model;

import java.util.List;

public interface RyssgkMapper {
    @Select({"SELECT * FROM datas_3_table d WHERE d.jzid = '1' GROUP BY dates Desc"})
    List<Datas3Model> findAll();

    @Select({"SELECT * FROM datas_3_table WHERE jzid = '1' AND dates > NOW() - INTERVAL #{tt} HOUR"})
    List<Datas3Model> findByFour(@Param("tt") String tt);

    @Select({"SELECT * FROM datas_3_table d WHERE d.jzid = '1' GROUP BY dates Desc LIMIT 1"})
    Datas3Model findNewest();

    @Select({
            "SELECT"
                    + " 	d.acc_hea"
                    + " FROM"
                    + " 	datas_3_table d"
                    + " WHERE unix_timestamp(d.dates)*1000 >= #{rqa}"
                    + " AND unix_timestamp(d.dates)*1000 <= #{rqb}"
                    + " ORDER BY"
                    + " 	d.dates DESC"
    })
    List<Datas3Model> findByRq(@Param("rqa") long rqa, @Param("rqb") long rqb);
}
