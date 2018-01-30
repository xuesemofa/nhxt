package org.consume.com.rycbfx.service.impl;

import org.consume.com.gwgrzq.service.GwgrzqService;
import org.consume.com.hrzrl.model.HrzrlFxModel;
import org.consume.com.opc.model.Datas3Model;
import org.consume.com.qxxx.model.QxxxModel;
import org.consume.com.qxxx.service.QxxxService;
import org.consume.com.rycbfx.mapper.RycbfxMapper;
import org.consume.com.rycbfx.service.RycbfxService;
import org.consume.com.rymj.model.RymjModel;
import org.consume.com.rymj.service.RymjService;
import org.consume.com.sdrdj.model.SdrdjModel;
import org.consume.com.sdrdj.service.SdrdjService;
import org.consume.com.util.doubleUtils.Arith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RycbfxServiceImpl implements RycbfxService {
    @Autowired
    private RycbfxMapper mapper;
    @Autowired
    private QxxxService qxxxService;
    @Autowired
    private GwgrzqService gwgrzqService;
    @Autowired
    private RymjService rymjService;
    @Autowired
    private SdrdjService sdrdjService;

    @Override
    public List<HrzrlFxModel> findByRq(Map<Integer, long[]> map) {
        List<RymjModel> rymjModels = rymjService.get();
        List<SdrdjModel> list2 = sdrdjService.finds();
        String dj = "0";
        if (list2 != null && list2.size() > 0)
            dj = list2.get(0).getV3() + "";
        String mj = "1";
        if (rymjModels != null && rymjModels.size() > 0)
            mj = rymjModels.get(0).getMj() + "";
        List<HrzrlFxModel> list1 = new ArrayList<>();
        List<QxxxModel> qxxxs = qxxxService.findPage(0, 0);
//        指定时间段的数据
        for (int i = 0; i < map.size(); i++) {
            List<Datas3Model> list = mapper.findByRq(map.get(i + 1)[0], map.get(i + 1)[1]);
//            获取最大数据和最小数据
            if (list != null && list.size() > 0) {
                HrzrlFxModel h = new HrzrlFxModel();
                h.setSj(map.get(i + 1)[0]+"---"+map.get(i + 1)[1]);
                h.setHrzmj(mj);
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
//                Double div = Arith.div(sub, Double.valueOf(mj));
//                h.setHrzrdh(div + "");
                h.setHrzrdh(sub + "");
//                成本
                Double mul = Arith.mul(sub, Double.valueOf(dj));
                h.setHrzrzb(mul + "");

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
