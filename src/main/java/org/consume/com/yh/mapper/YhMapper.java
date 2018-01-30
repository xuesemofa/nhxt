package org.consume.com.yh.mapper;

import org.consume.com.yh.model.YhModel;
import org.apache.ibatis.annotations.*;
import com.github.pagehelper.Page;

public interface YhMapper {
	@Select({"SELECT * FROM yh_table WHERE dates = (select MAX(dates) from  yh_table)"})
	Page<YhModel> findAllPage();
	
	@Select({"SELECT * FROM yh_table WHERE username LIKE #{username} AND dates = (select MAX(dates) from  yh_table) "})
	Page<YhModel> findByUserName(@Param("username")String username);
	
	@Select({"SELECT * FROM yh_table WHERE name = #{name} and dates = (select MAX(dates) from  yh_table)"})
	YhModel getByName(@Param("name")String name);
}
