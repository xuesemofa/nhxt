package org.consume.com.sltj.controller;

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
 * 水量统计分析
 */
@RestController
@RequestMapping("/sltj")
public class SltjController {

    @Value("${page.pageSize}")
    private Integer pageSize;

    @Autowired
    private HeatService service;

    @Autowired
    private StructureService structureService;

    @Autowired
    private SltjService sltjService;

//    @RequiresPermissions(value = "sltj:init")
    @GetMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow) {
        Page<HeatModel> page = service.findAllPage(pageNow, pageSize);
        List<StructureModel> list = structureService.getByParents();
        return new ModelAndView("/sltj/heat")
                .addObject("model", new HeatModel())
                .addObject("list", list)
                .addObject("page", page);
    }

    @GetMapping("/query")
    public ModelAndView init(@RequestParam("id") String id) {
        return new ModelAndView("/sltj/query")
                .addObject("id", id);
    }

    @PostMapping("/getJson")
    public Map<String, Object> getJson(@RequestParam("id") String id) throws Exception {
        Map<String, Object> map = new HashMap<>();
//        for (int j = 1; j < 13; j++) {
//            if (j == 1 || j == 3 || j == 5 || j == 7 || j == 8 || j == 10 || j == 12) {
//                long oneStrat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-01 00:00:00").getTime();
//                long oneEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-31 24:59:59").getTime();
//                List<Datas2Model> jza = opc2Service.getById(unitComparisonModel.getJzbid(), oneStrat, oneEnd);
//                if (jza != null && jza.size() > 0) {
//                    mapb.put(j, jza.get(0));
//                } else {
//                    Datas2Model d = new Datas2Model();
//                    mapb.put(j, d);
//                }
//            } else if (j == 4 || j == 6 || j == 9 || j == 11) {
//                long oneStrat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-01 00:00:00").getTime();
//                long oneEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-30 24:59:59").getTime();
//                List<Datas2Model> jza = opc2Service.getById(unitComparisonModel.getJzbid(), oneStrat, oneEnd);
//                if (jza != null && jza.size() > 0) {
//                    mapb.put(j, jza.get(0));
//                } else {
//                    Datas2Model d = new Datas2Model();
//                    mapb.put(j, d);
//                }
//            } else if (j == 2) {
//                long oneStrat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-01 00:00:00").getTime();
//                long oneEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(i + "-0" + j + "-28 24:59:59").getTime();
//                List<Datas2Model> jza = opc2Service.getById(unitComparisonModel.getJzbid(), oneStrat, oneEnd);
//                if (jza != null && jza.size() > 0) {
//                    mapb.put(j, jza.get(0));
//                } else {
//                    Datas2Model d = new Datas2Model();
//                    mapb.put(j, d);
//                }
//            }
//        }
        List<SltjModel> sltjModels = sltjService.get(id,"1");
        double[] v = new double[1];
        v[0] = Double.valueOf(sltjModels.get(0).getV());
        map.put("v", v);
        return map;
    }
}
