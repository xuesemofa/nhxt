package org.consume.com.opc.service.impl;

import org.consume.com.opc.mapper.OPC2Mapper;
import org.consume.com.opc.model.Datas2Model;
import org.consume.com.opc.service.OPC2Service;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OPC2ServiceImpl implements OPC2Service {

    @Resource
    private OPC2Mapper mapper;

    @Override
    public int add(Datas2Model model) {
        model.setUuid(GetUuid.getUUID());
        return mapper.add(model);
    }

    @Override
    public int save(Map<String, List<Datas2Model>> map) {
        return mapper.save(map);
    }

    @Override
    public List<Datas2Model> findList(String id, String zd, String px) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, id);
        if ((zd != null && !(zd.equals(""))) && (px != null && !(px.equals("")))) {
            map.put(2, zd);
            map.put(3, px);
        }
        return mapper.findList2(map);
    }

    @Override
    public List<Datas2Model> findList2(String zd, String px) {
        Map<Integer, String> map = new HashMap<>();
        if ((zd != null && !(zd.equals(""))) && (px != null && !(px.equals("")))) {
            map.put(2, zd);
            map.put(3, px);
        }
        return mapper.findList3(map);
    }

    @Override
    public List<Datas2Model> getById(String id, long a, long b) {
        return mapper.getById(id, a, b);
    }

    @Override
    public void getByDate(String rq) {
        List<Datas2Model> list = mapper.getByDate(rq);
        if (list != null && list.size() > 0) {
            mapper.delByDate(rq);
            for (int i = 0; i < list.size(); i++) {
                Datas2Model datas2Model = list.get(i);
                Datas2Model model = new Datas2Model(
                        GetUuid.getUUID(), datas2Model.getJzid(), datas2Model.getDates(), datas2Model.getT11(), datas2Model.getT12()
                        , datas2Model.getP11(), datas2Model.getP12(), datas2Model.getIns_hea(), datas2Model.getIns_flo(),
                        datas2Model.getAcc_hea(), datas2Model.getT21(), datas2Model.getT22(), datas2Model.getP21(), datas2Model.getP22(),
                        datas2Model.getLev(), datas2Model.getMot_bac(), datas2Model.getBf_bac1(), datas2Model.getXf_bac1(),
                        datas2Model.getxI_bac1(), datas2Model.getXf_bac2(), datas2Model.getxI_bac2(), datas2Model.getBl1(),
                        datas2Model.getBl2(), datas2Model.getBl3(), datas2Model.getBl4(), datas2Model.getBl5(), datas2Model.getBl6(),
                        datas2Model.getBl7(), datas2Model.getBl8(), datas2Model.getBl9(), datas2Model.getBl10()
                );
                mapper.add2(model);
            }
        }
    }
}
