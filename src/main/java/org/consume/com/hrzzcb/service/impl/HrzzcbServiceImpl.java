package org.consume.com.hrzzcb.service.impl;

import org.consume.com.aspect.service.AspectService;
import org.consume.com.crew.mapper.CrewMapper;
import org.consume.com.crew.model.CrewModel;
import org.consume.com.gwgrzq.service.GwgrzqService;
import org.consume.com.heat.mapper.HeatMapper;
import org.consume.com.heat.model.HeatModel;
import org.consume.com.hrzrl.mapper.HrzrlMapper;
import org.consume.com.hrzrl.model.HrzrlFxModel;
import org.consume.com.hrzzcb.service.HrzzcbService;
import org.consume.com.qxxx.model.QxxxModel;
import org.consume.com.qxxx.service.QxxxService;
import org.consume.com.sdrdj.model.SdrdjModel;
import org.consume.com.sdrdj.service.SdrdjService;
import org.consume.com.sltj.model.SltjModel;
import org.consume.com.sltj.service.SltjService;
import org.consume.com.util.doubleUtils.Arith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HrzzcbServiceImpl implements HrzzcbService {

    @Autowired
    private HrzrlMapper mapper;
    @Autowired
    private CrewMapper crewMapper;
    @Autowired
    private HeatMapper heatMapper;
    @Autowired
    private GwgrzqService gwgrzqService;
    @Autowired
    private QxxxService qxxxService;
    @Autowired
    private SltjService sltjService;
    @Autowired
    private AspectService aspectService;
    @Autowired
    private SdrdjService sdrdjService;


    @Override
    public ArrayList<Object> getByIdAndRq(String id, Map<Integer, long[]> map) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HeatModel model = heatMapper.getById(id);
        List<QxxxModel> qxxxs = qxxxService.findPage(0, 0);
        List<CrewModel> crewModels = crewMapper.getByParents(id);
        ArrayList<Object> array = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("######0.00");

//        单价
        List<SdrdjModel> list2 = sdrdjService.finds();
        String dj = "0";
        if (list2 != null && list2.size() > 0)
            dj = list2.get(0).getV1() + "";
        String dj2 = "0";
        if (list2 != null && list2.size() > 0)
            dj2 = list2.get(0).getV2() + "";
        String dj3 = "0";
        if (list2 != null && list2.size() > 0)
            dj3 = list2.get(0).getV3() + "";

//        指定时间段的数据
        for (int i = 0; i < map.size(); i++) {
            Object[] ob5 = new Object[12];
            Date date = new Date(map.get(i + 1)[0]);
            Date date1 = new Date(map.get(i + 1)[1]);
            ob5[0] = sdf.format(date) + "---" + sdf.format(date1);
            ob5[1] = model.getNames();
            ob5[2] = model.getMj();
            ob5[3] = 0;
            ob5[4] = 0;
            ob5[5] = 0;
            ob5[6] = 0;
            ob5[7] = 0;
            ob5[8] = 0;
            ob5[9] = 0;
            ob5[10] = model.getWx();
            for (int j = 0; j < crewModels.size(); j++) {
                List<HrzrlFxModel> list = mapper.getByIdAndRq(crewModels.get(j).getUuid(), map.get(i + 1)[0], map.get(i + 1)[1]);
//            获取最大数据和最小数据
                if (list != null && list.size() > 0) {
//                最大
                    String v1 = list.get(0).getHrzrdh();
//                最小
                    String v2;
                    if (list.size() > 1)
                        v2 = list.get(list.size() - 1).getHrzrdh();
                    else
                        v2 = "0";
//                相减获取真实热量
                    Double sub = Arith.sub(Double.valueOf(v1), Double.valueOf(v2));
                    Double add = Arith.add(Double.valueOf(ob5[3] + ""), sub);
                    ob5[3] = df.format(add);
//                成本
                    Double div = Arith.mul(sub, Double.valueOf(dj3));
                    ob5[4] = df.format(Arith.add(Double.valueOf(ob5[4] + ""), div));
                }else {
                    ob5[3] = 0;
                    ob5[4] = 0;
                }
            }
            //        气象
            double zgwd = 0.00;
            double zdwd = 0.00;
            if (qxxxs != null && qxxxs.size() > 0) {
                zgwd = qxxxs.get(4) == null ? 0.00 : qxxxs.get(4).getZgwd();
                zdwd = qxxxs.get(4) == null ? 0.00 : qxxxs.get(4).getZdwd();
            }
            Double div2 = Arith.div(Arith.add(zgwd, zdwd), Double.valueOf("2"));
            ob5[9] = df.format(div2);
            array.add(ob5);
        }

//        水
        for (int i = 0; i < map.size(); i++) {
            List<SltjModel> byId = sltjService.findByTimeAndId2(model.getUuid(), map.get(i + 1)[0], map.get(i + 1)[1]);
            double d = 0;
            Object[] o = (Object[]) array.get(i);
            if (byId != null && byId.size() > 0) {
                d = Double.parseDouble(byId.get(0).getV()) - Double.parseDouble(byId.get(byId.size()-1).getV());
                Double mul = Arith.mul(Double.valueOf(dj), d);
                o[5] = df.format(d);
                o[6] = df.format(mul);
            }else {
                o[5] = 0;
                o[6] = 0;
            }
        }

        //        电
        for (int i = 0; i < map.size(); i++) {
            List<SltjModel> byId = sltjService.findByTimeAndId2(model.getUuid(), map.get(i + 1)[0], map.get(i + 1)[1]);
            double d = 0;
            Object[] o = (Object[]) array.get(i);
            if (byId != null && byId.size() > 0) {
                d = Double.parseDouble(byId.get(0).getV()) - Double.parseDouble(byId.get(byId.size()-1).getV());
                Double mul = Arith.mul(Double.valueOf(dj2), d);
                o[7] = df.format(d);
                o[8] = df.format(mul);
            }else {
                o[7] = 0;
                o[8] = 0;
            }
        }

        return array;
    }
}
