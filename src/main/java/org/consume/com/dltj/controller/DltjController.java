package org.consume.com.dltj.controller;

import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.consume.com.heat.model.HeatModel;
import org.consume.com.heat.service.HeatService;
import org.consume.com.sltj.model.SltjModel;
import org.consume.com.sltj.service.SltjService;
import org.consume.com.structure.model.StructureModel;
import org.consume.com.structure.service.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电量统计分析
 */
@RestController
@RequestMapping("/dltj")
public class DltjController {
//    @RequiresPermissions(value = "dltj:init")

    @Value("${page.pageSize}")
    private Integer pageSize;

    @Autowired
    private HeatService service;

    @Autowired
    private StructureService structureService;

    @Autowired
    private SltjService sltjService;

//    @RequiresPermissions(value = "dltj:init")
    @GetMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow) {
        Page<HeatModel> page = service.findAllPage(pageNow, pageSize);
        List<StructureModel> list = structureService.getByParents();
        return new ModelAndView("/dltj/heat")
                .addObject("model", new HeatModel())
                .addObject("list", list)
                .addObject("page", page);
    }

    @GetMapping("/query")
    public ModelAndView init(@RequestParam("id") String id) {
        return new ModelAndView("/dltj/query")
                .addObject("id", id);
    }

    @PostMapping("/getJson")
    public Map<String, Object> getJson(@RequestParam("id") String id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<SltjModel> sltjModels = sltjService.get(id, "1");
        double[] v = new double[1];
        v[0] = Double.valueOf(sltjModels.get(0).getV());
        map.put("v", v);
        return map;
    }
}
