package org.consume.com.hrzrl.service.impl;

import org.consume.com.crew.mapper.CrewMapper;
import org.consume.com.crew.model.CrewModel;
import org.consume.com.gwgrzq.service.GwgrzqService;
import org.consume.com.heat.mapper.HeatMapper;
import org.consume.com.heat.model.HeatModel;
import org.consume.com.hrzrl.mapper.HrzrlMapper;
import org.consume.com.hrzrl.model.HrzrlFxModel;
import org.consume.com.hrzrl.model.HrzrlModel;
import org.consume.com.hrzrl.service.HrzrlService;
import org.consume.com.qxxx.model.QxxxModel;
import org.consume.com.qxxx.service.QxxxService;
import org.consume.com.util.doubleUtils.Arith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HrzrlServiceImpl implements HrzrlService {

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

    @Override
    public List<HrzrlModel> getByJzid(String id, long strDate, long endDate) {
        return mapper.getByJzid(id, strDate, endDate);
    }

    @Override
    public List<HrzrlFxModel> getJzs(Map<Integer, String> map) {
        return mapper.getJzs(map);
    }

    @Override
    public List<HrzrlFxModel> getJzs2(Map<Integer, String> map) {
        return mapper.getJzs2(map);
    }

    @Override
    public List<HrzrlFxModel> getJzs3(Map<Integer, String> map) {
        return mapper.getJzs3(map);
    }

    @Override
    public List<HrzrlFxModel> getById(String id, String rqa, String rqb, long rqc) {
        List<HrzrlFxModel> list = mapper.getById(id, rqa, rqb, rqc);
        if (list == null || list.size() <= 0)
            return null;

        List<QxxxModel> qxxxModels = mapper.findPage(rqa);

//        BigDecimal b11 = new BigDecimal(Double.toString(qxxxModels.get(0).getZgwd()));
//        BigDecimal b21 = new BigDecimal(Double.toString(qxxxModels.get(0).getZdwd()));
//        list.get(0).setWd(b11.add(b21).doubleValue());

        for (int i = 0; i < list.size(); i++) {

            list.get(i).setSj(rqa);
            if (qxxxModels != null && qxxxModels.size() > 0) {
                BigDecimal b1 = new BigDecimal(Double.toString(qxxxModels.get(0).getZgwd()));
                BigDecimal b2 = new BigDecimal(Double.toString(qxxxModels.get(0).getZdwd()));
                BigDecimal decimal = b1.add(b2);
                double v = b1.divide(decimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                list.get(i).setWd(v);
            }
        }
        return list;
    }

    @Override
    public List<HrzrlFxModel> getById2(String id, long l) {
//        获取机组对象
        CrewModel model = crewMapper.getById(id);
//        获取机组每天的数据
        List<HrzrlFxModel> list = mapper.getById2(id);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setHrzmc(model.getNames());
            list.get(i).setHrzmj(model.getMj() + "");
//            获取热量基础数据
            String s = list.get(i).getHrzrdh() == null ? "0" : list.get(i).getHrzrdh();
//             获取上一天的热量基础数据，如果没有则为0
            String s1 = "0";
            if ((i + 1) < list.size())
                s1 = list.get(i + 1).getHrzrdh();
//            计算两天的热量基础数据差额
            BigDecimal b11 = new BigDecimal(s);
            BigDecimal b21 = new BigDecimal(s1);
            double v1 = b11.subtract(b21).doubleValue();
//            差额除以面积获取热单耗
            BigDecimal b12 = new BigDecimal(v1);
            BigDecimal b22 = new BigDecimal(model.getMj());
            list.get(i).setHrzrdh(v1 + "");
//            差额*1000000/mj/日期差秒数
            BigDecimal j = new BigDecimal(1000000000);
            double v2 = new BigDecimal((b12.multiply(j)).doubleValue()).divide(b22, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            double v3 = new BigDecimal(v2).divide(new BigDecimal(l), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            list.get(i).setHrzrzb(v3 + "");


            List<QxxxModel> qxxxModels = mapper.findPage(list.get(i).getSj());
            if (qxxxModels != null && qxxxModels.size() > 0) {
                BigDecimal b1 = new BigDecimal(Double.toString(qxxxModels.get(0).getZgwd()));
                BigDecimal b2 = new BigDecimal(Double.toString(qxxxModels.get(0).getZdwd()));
                BigDecimal decimal = b1.add(b2);
                double v = decimal.divide(new BigDecimal(2), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                list.get(i).setWd(v);
            }
        }
        return list;
    }

    @Override
    public List<HrzrlFxModel> getById3(String id, long l) {
//        获取机组对象
        HeatModel model = heatMapper.getById(id);
//        获取机组每天的数据
        List<HrzrlFxModel> list = mapper.getById3(id);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setHrzmc(model.getNames());
            list.get(i).setHrzmj(model.getMj() + "");
//            获取热量基础数据
            String s = list.get(i).getHrzrdh() == null ? "0" : list.get(i).getHrzrdh();
//             获取上一天的热量基础数据，如果没有则为0
            String s1 = "0";
            if ((i + 1) < list.size())
                s1 = list.get(i + 1).getHrzrdh();
//            计算两天的热量基础数据差额
            BigDecimal b11 = new BigDecimal(s);
            BigDecimal b21 = new BigDecimal(s1);
            double v1 = b11.subtract(b21).doubleValue();
//            差额除以面积获取热单耗
            BigDecimal b12 = new BigDecimal(v1);
            BigDecimal b22 = new BigDecimal(model.getMj());
            double rdh = b12.divide(b22, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            list.get(i).setHrzrdh(rdh + "");
//            差额*1000000/mj/日期差秒数
            BigDecimal j = new BigDecimal(1000000);
            double v2 = new BigDecimal((b12.multiply(j)).doubleValue()).divide(b22, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            double v3 = new BigDecimal(v2).divide(new BigDecimal(l), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            list.get(i).setHrzrzb(v3 + "");


            List<QxxxModel> qxxxModels = mapper.findPage(list.get(i).getSj());
            if (qxxxModels != null && qxxxModels.size() > 0) {
                BigDecimal b1 = new BigDecimal(Double.toString(qxxxModels.get(0).getZgwd()));
                BigDecimal b2 = new BigDecimal(Double.toString(qxxxModels.get(0).getZdwd()));
                BigDecimal decimal = b1.add(b2);
                double v = decimal.divide(new BigDecimal(2), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                list.get(i).setWd(v);
            }
        }
        return list;
    }

    @Override
    public List<QxxxModel> findPage(String rq) {
        return mapper.findPage(rq);
    }


    @Override
    public List<HrzrlFxModel> getByIdAndRq(String id, Map<Integer, long[]> map) {
        //        获取机组对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CrewModel model = crewMapper.getById(id);
        List<HrzrlFxModel> list1 = new ArrayList<>();
        List<QxxxModel> qxxxs = qxxxService.findPage(0, 0);
//        指定时间段的数据
        for (int i = 0; i < map.size(); i++) {
            List<HrzrlFxModel> list = mapper.getByIdAndRq(id, map.get(i + 1)[0], map.get(i + 1)[1]);
//            获取最大数据和最小数据
            if (list != null && list.size() > 0) {
                HrzrlFxModel h = new HrzrlFxModel();
                try {
                    Date parse = new Date(map.get(i + 1)[0]);
                    Date parse1 = new Date(map.get(i + 1)[1]);
                    h.setSj(sdf.format(parse) + "---" + sdf.format(parse1));
                } catch (Exception e) {
                    h.setSj("---");
                }
                h.setHrzmc(model.getNames());
                h.setHrzmj(model.getMj() + "");
//                最大
                String v1 = list.get(0).getHrzrdh();
//                最小
                String v2;
                if (list.size() > 1)
                    v2 = list.get(list.size() - 1).getHrzrdh();
                else
                    v2 = "0";
//                相减获取真实热量
                double v = Double.parseDouble(v1) - Double.parseDouble(v2);
                h.setHrzrdh(v + "");
//                热指标
                double v4 = v * 1000000000;
                double v5 = v4 / model.getMj();
                long l = new Date(map.get(i + 1)[1]).getTime() - new Date(map.get(i + 1)[0]).getTime();
                double v6 = v5 / Double.parseDouble((l/1000) + "");
                h.setHrzrzb(v6 + "");

//                气象
                double zgwd = 0.00;
                double zdwd = 0.00;
                if (qxxxs != null && qxxxs.size() > 0) {
                    zgwd = qxxxs.get(4) == null ? 0.00 : qxxxs.get(4).getZgwd();
                    zdwd = qxxxs.get(4) == null ? 0.00 : qxxxs.get(4).getZdwd();
                }
                Double div2 = Arith.div(Arith.add(zgwd, zdwd), Double.valueOf("2"));
                h.setWd(div2);
                list1.add(h);
            } else {
                HrzrlFxModel h = new HrzrlFxModel();
                try {
                    Date parse = new Date(map.get(i + 1)[0]);
                    Date parse1 = new Date(map.get(i + 1)[1]);
                    h.setSj(sdf.format(parse) + "---" + sdf.format(parse1));
                } catch (Exception e) {
                    h.setSj("---");
                }
                h.setHrzmc(model.getNames());
                h.setHrzmj(model.getMj() + "");
                h.setHrzrdh("0");
                h.setHrzrzb("0");
//                气象
                double zgwd = 0.00;
                double zdwd = 0.00;
                if (qxxxs != null && qxxxs.size() > 0) {
                    zgwd = qxxxs.get(4) == null ? 0.00 : qxxxs.get(4).getZgwd();
                    zdwd = qxxxs.get(4) == null ? 0.00 : qxxxs.get(4).getZdwd();
                }
                Double div2 = Arith.div(Arith.add(zgwd, zdwd), Double.valueOf("2"));
                h.setWd(div2);
                list1.add(h);
            }
        }
        return list1;
    }

    @Override
    public List<HrzrlFxModel> getByIdAndRq2(String id, Map<Integer, long[]> map) {
        //        获取机组对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HeatModel model = heatMapper.getById(id);
        List<QxxxModel> qxxxs = qxxxService.findPage(0, 0);
        List<CrewModel> crewModels = crewMapper.getByParents(id);
//        指定时间段的数据
        List<HrzrlFxModel> list2 = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            List<HrzrlFxModel> list1 = new ArrayList<>();
                    HrzrlFxModel h = new HrzrlFxModel();
            for (int j = 0; j < crewModels.size(); j++) {
                List<HrzrlFxModel> list = mapper.getByIdAndRq(crewModels.get(j).getUuid(), map.get(i + 1)[0], map.get(i + 1)[1]);
//            获取最大数据和最小数据
                if (list != null && list.size() > 0) {
                    try {
                        Date parse = new Date(map.get(i + 1)[0]);
                        Date parse1 = new Date(map.get(i + 1)[1]);
                        h.setSj(sdf.format(parse) + "---" + sdf.format(parse1));
                    } catch (Exception e) {
                        h.setSj("---");
                    }
                    h.setHrzmc(model.getNames());
                    h.setHrzmj(model.getMj() + "");
//                最大
                    String v1 = list.get(0).getHrzrdh();
//                最小
                    String v2;
                    if (list.size() > 1)
                        v2 = list.get(list.size() - 1).getHrzrdh();
                    else
                        v2 = "0";
                    double v = Double.parseDouble(v1) - Double.parseDouble(v2);

//                热单耗
                    h.setHrzrdh(v + "");
//                热指标
                    double v4 = v * 1000000000;
                    double v5 = v4 / model.getMj();
                    long l = new Date(map.get(i + 1)[1]).getTime() - new Date(map.get(i + 1)[0]).getTime();
                    double v6 = v5 / Double.parseDouble((l/1000) + "");
                    h.setHrzrzb(v6 + "");

//                气象
                    double zgwd = 0.00;
                    double zdwd = 0.00;
                    if (qxxxs != null && qxxxs.size() > 0) {
                        zgwd = qxxxs.get(4) == null ? 0.00 : qxxxs.get(4).getZgwd();
                        zdwd = qxxxs.get(4) == null ? 0.00 : qxxxs.get(4).getZdwd();
                    }
                    Double div2 = Arith.div(Arith.add(zgwd, zdwd), Double.valueOf("2"));
                    h.setWd(div2);
                    list1.add(h);
                } else {
                    h = new HrzrlFxModel();
                    try {
                        Date parse = new Date(map.get(i + 1)[0]);
                        Date parse1 = new Date(map.get(i + 1)[1]);
                        h.setSj(sdf.format(parse) + "---" + sdf.format(parse1));
                    } catch (Exception e) {
                        h.setSj("---");
                    }
                    h.setHrzmc(model.getNames());
                    h.setHrzmj(model.getMj() + "");
                    h.setHrzrdh("0");
                    h.setHrzrzb("0");
//                气象
                    double zgwd = 0.00;
                    double zdwd = 0.00;
                    if (qxxxs != null && qxxxs.size() > 0) {
                        zgwd = qxxxs.get(4) == null ? 0.00 : qxxxs.get(4).getZgwd();
                        zdwd = qxxxs.get(4) == null ? 0.00 : qxxxs.get(4).getZdwd();
                    }
                    Double div2 = Arith.div(Arith.add(zgwd, zdwd), Double.valueOf("2"));
                    h.setWd(div2);
                    list1.add(h);
                }
            }
            HrzrlFxModel h1 = new HrzrlFxModel();
            double v = 0;
            double v1 = 0;
            for (HrzrlFxModel f : list1) {
                v += Double.parseDouble(f.getHrzrdh());
                v1 += Double.parseDouble(f.getHrzrzb());
            }
            h1 = list1.get(0);
            h1.setHrzrdh(Double.toString(v));
            h1.setHrzrzb(Double.toString(v1));
            list2.add(h1);
        }
        return list2;
    }
}
