package org.consume.com.jurisdiction.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.consume.com.jurisdiction.mapper.JurisdictionMapper;
import org.consume.com.jurisdiction.model.JurisdictionModel;
import org.consume.com.jurisdiction.service.JurisdictionService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JurisdictionServiceImpl implements JurisdictionService {
    @Autowired
    private JurisdictionMapper mapper;

    @Override
    public int add(JurisdictionModel ju) {
        return mapper.add(ju);
    }

    @Override
    public int del(String uuid) {
        return mapper.del(uuid);
    }

    @Override
    public int del2() {
        return mapper.del2();
    }

    @Override
    public List<JurisdictionModel> findAll() {
        return mapper.findAll();
    }

    @Override
    public Page<JurisdictionModel> findAllByPage(int pageNow, int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        return mapper.findAllByPage();
    }

    @Override
    public int insertAll() {
        //            用户维护权限
        List<JurisdictionModel> list = new ArrayList<>();
        String s1[] = {"列表", "新增", "修改", "删除", "查询", "选择"};
        String s2[] = {"init", "add", "update", "del", "query", "choice"};
        String s3[] = {"用户维护", "站点维护权限", "换热站权限", "机组维护权限", "属性维护权限", "机组对比",
                "数据查询", "气象信息", "热量统计分析", "水量统计分析", "电量统计分析", "水电热综合统计分析",
                "水电热站与站统计分析", "热源实时工况信息", "换热监控实时工况", "投诉信息", "报修信息", "用户采暖负荷信息",
                "收费信息", "换热站能耗成本分析", "换热站营销分析", "GIS系统",
                "换热站能耗分析", "换热站能耗KPI指标管理", "超标管理"};
        String s4[] = {"people", "heat", "exchange", "crew", "heatattribute", "unitcomparison", "sjcx", "qxxx", "rltj", "sltj",
                "dltj", "sdrzh", "sdrzz", "ryssgk", "hrjk", "ts", "bx", "yhcnfh", "sf", "hrznhcbfx", "yxcbfx", "gis",
                "hrznhfx", "kpi", "cbgl"};

        for (int i = 0; i < s3.length; i++
                ) {
            for (int k = 0; k < s1.length; k++
                    ) {
                JurisdictionModel j = new JurisdictionModel();
                j.setUuid(GetUuid.getUUID());
                j.setJuname(s3[i] + s1[k]);
                j.setUrl(s4[i] + ":" + s2[k]);
                list.add(j);
            }
        }
        return mapper.insertAll(list);
    }
}
