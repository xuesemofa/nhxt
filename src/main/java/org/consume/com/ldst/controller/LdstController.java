package org.consume.com.ldst.controller;

import org.consume.com.gwgrzq.model.GwgrzqModel;
import org.consume.com.gwgrzq.service.impl.GwgrzqServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ldst")
public class LdstController {

    @Autowired
    private GwgrzqServiceImpl gwgrzqService;

    @GetMapping("/init")
    public ModelAndView init() {
        List<GwgrzqModel> gwgrzqModels = gwgrzqService.find();
        GwgrzqModel gwgrzqModel = (gwgrzqModels != null && gwgrzqModels.size() > 0) ? gwgrzqModels.get(0) : new GwgrzqModel();
        long gwzq = 0L;
        long gwts = 0L;
        double wcl = 0;
        if (gwgrzqModel.getEnd_day() != null && gwgrzqModel.getStrat_day() != null) {
            Timestamp end_day = gwgrzqModel.getEnd_day();
            Timestamp strat_day = gwgrzqModel.getStrat_day();
//            供温天数
            gwzq = (end_day.getTime() - strat_day.getTime()) / (1000 * 60 * 60 * 24);
//            已经供温天数
            gwts = (new Date().getTime() - strat_day.getTime()) / (1000 * 60 * 60 * 24);
            if (gwts > 0) {
                wcl = gwts / (double) gwzq;
            }

        }
        return new ModelAndView("/ldst/index")
                .addObject("gwzq", gwzq)
                .addObject("gwts", gwts)
                .addObject("wcl", wcl)
                .addObject("model", gwgrzqModel);
    }
}
