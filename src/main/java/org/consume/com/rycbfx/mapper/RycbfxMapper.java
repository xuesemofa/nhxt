package org.consume.com.rycbfx.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.opc.model.Datas3Model;
import org.consume.com.qxxx.model.QxxxModel;

import java.util.List;

public interface RycbfxMapper {


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

    @Select({
            "select * from qxxx_tq_table q where UNIX_TIMESTAMP(q.dates) >=  #{rqa} and UNIX_TIMESTAMP(q.dates) <= #{rqb}"
    })
    List<QxxxModel> getByQxRq(@Param("rqa") long rqa, @Param("rqb") long rqb);
}
