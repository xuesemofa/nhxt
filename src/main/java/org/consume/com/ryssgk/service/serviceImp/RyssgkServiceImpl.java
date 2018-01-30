package org.consume.com.ryssgk.service.serviceImp;

import org.consume.com.gwgrzq.service.GwgrzqService;
import org.consume.com.hrzrl.model.HrzrlFxModel;
import org.consume.com.opc.model.Datas3Model;
import org.consume.com.qxxx.model.QxxxModel;
import org.consume.com.qxxx.service.QxxxService;
import org.consume.com.rymj.model.RymjModel;
import org.consume.com.rymj.service.RymjService;
import org.consume.com.ryssgk.mapper.RyssgkMapper;
import org.consume.com.ryssgk.service.RyssgkService;
import org.consume.com.util.doubleUtils.Arith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RyssgkServiceImpl implements RyssgkService {
    @Autowired
    private RyssgkMapper mapper;
    @Autowired
    private QxxxService qxxxService;
    @Autowired
    private GwgrzqService gwgrzqService;
    @Autowired
    private RymjService rymjService;

    @Override
    public List<Datas3Model> findAll() {
        return mapper.findAll();
    }

    @Override
    public List<Datas3Model> findByFour(String t) {
        String tt = "";
        if ("1".equals(t)) {
            tt = "4";
        }
        if ("2".equals(t)) {
            tt = "12";
        }
        if ("3".equals(t)) {
            tt = "24";
        }
        if ("4".equals(t)) {
            tt = "168";
        }
        if ("5".equals(t)) {
            tt = "720";
        }
        if ("6".equals(t)) {
            tt = "2160";
        }
        return mapper.findByFour(tt);
    }

    @Override
    public Datas3Model findNewest() {
        return mapper.findNewest();
    }

    @Override
    public List<HrzrlFxModel> findByRq(Map<Integer, long[]> map,String[] split2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        List<RymjModel> rymjModels = rymjService.get();
//        String mj = "1";
//        if (rymjModels != null && rymjModels.size() > 0)
//            mj = rymjModels.get(0).getMj() + "";
        List<HrzrlFxModel> list1 = new ArrayList<>();
        List<QxxxModel> qxxxs = qxxxService.findPage(0, 0);
//        指定时间段的数据
        for (int i = 0; i < map.size(); i++) {
            List<Datas3Model> list = mapper.findByRq(map.get(i + 1)[0], map.get(i + 1)[1]);
//            获取最大数据和最小数据
            if (list != null && list.size() > 0) {
                HrzrlFxModel h = new HrzrlFxModel();
//                sdf.parse(map.get(i + 1)[0]+"")
//                h.setSj(map.get(i + 1)[0]+"---"+map.get(i + 1)[1]);
                try {
                    h.setSj(sdf.format(new Date(map.get(i + 1)[0])) + "---" + sdf.format(new Date(map.get(i + 1)[1])));
                } catch (Exception e) {
                    h.setSj("---");
                }
                h.setHrzmj(split2[i]);
//                最大
                String v1 = list.get(0).getAcc_hea();
//                最小
                String v2;
                if (list.size() > 1)
                    v2 = list.get(list.size() - 1).getAcc_hea();
                else
                    v2 = "0";
//                相减获取真实热量
                Double sub = Arith.sub(Double.valueOf(v1), Double.valueOf(v2));
//                热单耗
                Double div = Arith.div(sub, Double.valueOf(split2[i]));
                h.setHrzrdh(div + "");
//                热指标
                Double mul = Arith.mul(sub, Double.valueOf("1000000"));
                long getmm = gwgrzqService.getmm();
                Double div1 = Arith.div(mul, Double.valueOf(getmm + ""));
                h.setHrzrzb(div1 + "");

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
    public List<HrzrlFxModel> findByRq2(Map<Integer, long[]> map,String[] split1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        List<RymjModel> rymjModels = rymjService.get();
//        String mj = "1";
//        if (rymjModels != null && rymjModels.size() > 0)
//            mj = rymjModels.get(0).getMj() + "";
        List<HrzrlFxModel> list1 = new ArrayList<>();
        List<QxxxModel> qxxxs = qxxxService.findPage(0, 0);
//        指定时间段的数据
        for (int i = 0; i < map.size(); i++) {
            List<Datas3Model> list = mapper.findByRq(map.get(i + 1)[0], map.get(i + 1)[1]);
//            获取最大数据和最小数据
            if (list != null && list.size() > 0) {
                HrzrlFxModel h = new HrzrlFxModel();
//                h.setSj(map.get(i + 1)[0]+"---"+map.get(i + 1)[1]);
                try {
                    h.setSj(sdf.format(new Date(map.get(i + 1)[0])) + "---" + sdf.format(new Date(map.get(i + 1)[1])));
                } catch (Exception e) {
                    h.setSj("---");
                }
                h.setHrzmj(split1[i]);
//                最大
                String v1 = list.get(0).getAcc_hea();
//                最小
                String v2;
                if (list.size() > 1)
                    v2 = list.get(list.size() - 1).getAcc_hea();
                else
                    v2 = "0";
//                相减获取真实热量
                Double sub = Arith.sub(Double.valueOf(v1), Double.valueOf(v2));
//                热单耗
                Double div = Arith.div(sub, Double.valueOf(split1[i]));
                h.setHrzrdh(div + "");
//                热指标
                Double mul = Arith.mul(sub, Double.valueOf("1000000"));
                long getmm = gwgrzqService.getmm();
                Double div1 = Arith.div(mul, Double.valueOf(getmm + ""));
                h.setHrzrzb(div1 + "");

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
}
