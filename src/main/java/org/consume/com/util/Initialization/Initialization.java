package org.consume.com.util.Initialization;

import org.consume.com.jurisdiction.model.JurisdictionModel;
import org.consume.com.jurisdiction.service.JurisdictionService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//权限初始化
@RestController
public class Initialization {

    @Autowired
    private JurisdictionService jurisdictionService;

    public void initialization() {
        JurisdictionModel ju = new JurisdictionModel();
        List<String> list = new ArrayList<String>();
        List<String> lis = new ArrayList<>();
        List<String> li = new ArrayList<>();
        List<String> l = new ArrayList<>();
        list.add("用户维护权限-");
        list.add("站点维护权限-");
        list.add("换热站权限-");
        list.add("机组维护权限-");
        list.add("属性维护权限-");
        list.add("机组对比-");
        lis.add("people:");
        lis.add("heat:");
        lis.add("exchange:");
        lis.add("crew:");
        lis.add("heatattribute:");
        lis.add("unitcomparison:");
        li.add("增加");
        li.add("删除");
        li.add("修改");
        li.add("查询");
        l.add("add");
        l.add("del");
        l.add("update");
        l.add("init");
        for (int i = 0; i < list.size() - 1; i++) {
            for (int ii = 0; ii < 4; ii++) {
                String s = list.get(i) + li.get(ii);
                String ss = lis.get(i) + l.get(ii);
                ju.setUuid(GetUuid.getUUID());
                ju.setJuname(s);
                ju.setUrl(ss);
                ju.setParent("");
                int add = jurisdictionService.add(ju);
            }
        }
    }
}
