package org.consume.com.aspect.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.crew.model.CrewModel;
import org.consume.com.opc.model.Datas2Model;
import org.consume.com.qxxx.model.QxxxModel;

import java.util.List;

public interface AspectMapper {
    @Select({"SELECT * FROM datas_2_table WHERE #{a} < dates < #{b} AND jzid = #{id}"})
    List<Datas2Model> findById(@Param("id") String id,@Param("a") long a ,@Param("b") long b );
    @Select({"SELECT * FROM qxxx_tq_table WHERE dates > #{a} AND dates < #{b}"})
    List<QxxxModel> findTqwdByTime(@Param("a") String a ,@Param("b") String b );
    @Select({"SELECT * FROM crew_table WHERE uuid = #{id}"})
    CrewModel findJzById(@Param("id") String id);
}
