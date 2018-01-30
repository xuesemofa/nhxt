package org.consume.com.gwgrzq.controller;

import org.consume.com.gwgrzq.model.GwgrzqModel;
import org.consume.com.gwgrzq.service.GwgrzqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/gwgrzq")
public class GwgrzqController {

    @Autowired
    private GwgrzqService gwgrzqService;

    @GetMapping("/init")
    public ModelAndView init() {
        List<GwgrzqModel> gwgrzqModels = gwgrzqService.find();
        return new ModelAndView("/gwgrzq/index")
                .addObject("model", gwgrzqModels == null ? new GwgrzqModel() : gwgrzqModels.get(0));
    }

    @PostMapping("/add")
    public ModelAndView add(@RequestParam("strat_day") String strat_day, @RequestParam("end_day") String end_day) throws Exception {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = sdf.parse(strat_day);
        Date parse1 = sdf.parse(end_day);
        gwgrzqService.setZq(new Timestamp(parse.getTime()), new Timestamp(parse1.getTime()));
        List<GwgrzqModel> gwgrzqModels = gwgrzqService.find();
        return new ModelAndView("/gwgrzq/index")
                .addObject("model", gwgrzqModels == null ? new GwgrzqModel() : gwgrzqModels.get(0));
    }
}
