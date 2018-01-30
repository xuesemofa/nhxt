package org.consume.com.xq.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.consume.com.xq.model.XqModel;

import com.github.pagehelper.Page;

public interface XqMapper {
	@Select({"SELECT * FROM xq_table WHERE dates = (select MAX(dates) from  xq_table)"})
	Page<XqModel> findAllPage();
	
	@Select({"SELECT * FROM xq_table WHERE name LIKE #{name} and dates = (select MAX(dates) from  xq_table)"})
	Page<XqModel> findByName(@Param("name")String name);
	
	@Select({"SELECT * FROM xq_table WHERE name = #{name} and dates = (select MAX(dates) from  xq_table)"})
	XqModel getByName(@Param("name")String name);
}
