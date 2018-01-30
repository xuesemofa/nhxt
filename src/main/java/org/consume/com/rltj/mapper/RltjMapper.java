package org.consume.com.rltj.mapper;

import org.apache.ibatis.annotations.Select;
import org.consume.com.opc.model.Datas3Model;

import java.util.List;

public interface RltjMapper {
    //查询昨天的最后一条记录
    @Select({"SELECT * FROM datas_3_table d WHERE d.jzid = '1' AND 2 <= TO_DAYS(NOW()) - TO_DAYS(dates) GROUP BY dates Desc LIMIT 1"})
    Datas3Model findYesterday();
    //查询前天的最后一条记录
    @Select({"SELECT * FROM datas_3_table d WHERE d.jzid = '1' AND 3 <= TO_DAYS(NOW()) - TO_DAYS(dates) GROUP BY dates Desc LIMIT 1"})
    Datas3Model findBeforeYesterday();
    //查询前一周
    @Select({"SELECT * FROM datas_3_table where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(dates)"})
    List<Datas3Model> findWeek();
}
