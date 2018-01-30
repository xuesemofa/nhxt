package org.consume.com.rycbfx.controller;

import org.consume.com.hrzrl.model.HrzrlFxModel;
import org.consume.com.rycbfx.service.RycbfxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rycbfx")
public class RycbfxController {

    @Autowired
    private RycbfxService rycbfxService;

    @GetMapping("/choseRq")
    public ModelAndView choseRq() {
        return new ModelAndView("/rycbfx/chooseSj");
    }

    @RequestMapping("/confirmTime")
    public ModelAndView confirmTime(@RequestParam("rq") String rq) throws Exception {
        String[] ss = rq.split(",");
        Map<Integer, long[]> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < ss.length; i++) {
            long[] l = new long[2];
            String[] split = ss[i].split("]");
            if (split.length > 1) {
                l[0] = sdf.parse(ss[i].split("]")[0] + " 00:00:00").getTime();
                l[1] = sdf.parse(ss[i].split("]")[1] + " 00:00:00").getTime();
                map.put(i + 1, l);
            }
        }
        List<HrzrlFxModel> list = rycbfxService.findByRq(map);
        return new ModelAndView("/rycbfx/querySj")
                .addObject("list", list);
    }
}
