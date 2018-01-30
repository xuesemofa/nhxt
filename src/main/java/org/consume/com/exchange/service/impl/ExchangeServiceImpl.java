package org.consume.com.exchange.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.consume.com.exchange.mapper.ExchangeMapper;
import org.consume.com.exchange.model.ExchangeModel;
import org.consume.com.exchange.model.OpcDatas2Model;
import org.consume.com.exchange.model.opcDatasModel;
import org.consume.com.exchange.service.ExchangeService;
import org.consume.com.opc.model.Datas2Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ExchangeServiceImpl implements ExchangeService {

    @Resource
    private ExchangeMapper mapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //    @Transactional
//    @Override
    public Page<ExchangeModel> findAllPage(int pageNow, int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        return mapper.findAllPage();
    }

    @Override
    public List<opcDatasModel> getDatas(String id) {
        return mapper.getDatas(id);
    }

    @Override
    public List<OpcDatas2Model> getDatas2(String id) {
        return mapper.getDatas2(id);
    }

    @Override
    public List<Datas2Model> getByNames(String names) {
        return mapper.getByNames(names);
    }

    //    @TargetDataSource(name = "ds1")
//    @Override
//    public List<ExchangeModel> test() {
//        String sql = "select uuid,email,password from account_table";
//        return (List<ExchangeModel>) jdbcTemplate.query(sql, new RowMapper<ExchangeModel>() {
//            @Override
//            public ExchangeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
//                ExchangeModel s = new ExchangeModel();
//                s.setDw(rs.getString("email"));
//                s.setJz(rs.getString("password"));
//                return s;
//            }
//        });
//    }
//
//    @TargetDataSource(name = "ds2")
//    @Override
//    public List<UserTestModel> test2() {
//        String sql = "select email,password from user_table";
//        return (List<UserTestModel>) jdbcTemplate.query(sql, new RowMapper<UserTestModel>() {
//            @Override
//            public UserTestModel mapRow(ResultSet rs, int rowNum) throws SQLException {
//                UserTestModel s = new UserTestModel();
//                s.setEmail(rs.getString("email"));
//                s.setPassword(rs.getString("password"));
//                return s;
//            }
//        });
//    }
}
