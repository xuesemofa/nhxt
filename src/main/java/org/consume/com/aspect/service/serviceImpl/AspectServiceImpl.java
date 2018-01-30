package org.consume.com.aspect.service.serviceImpl;

import org.consume.com.aspect.mapper.AspectMapper;
import org.consume.com.aspect.service.AspectService;
import org.consume.com.crew.model.CrewModel;
import org.consume.com.opc.model.Datas2Model;
import org.consume.com.qxxx.model.QxxxModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AspectServiceImpl implements AspectService {
    @Autowired
    private AspectMapper mapper;
    @Override
    public double[] findById(String id, long a, long b) {
        List<Datas2Model> byId = mapper.findById(id, a, b);
        double t11 = 1;
        double t12 = 1;
        for (Datas2Model op:byId)
        {
            String t21 = op.getT21();
            String t22 = op.getT22();
            Double aDouble = Double.valueOf(t21);
            Double aDouble2 = Double.valueOf(t22);
            t11 += aDouble;
            t12 += aDouble2;
        }
        double v = t11 / byId.size();
        double v1 = t12 / byId.size();
        double[] d = new double[2];
        d[0] = v;
        d[1] = v1;
        return d;
    }

    @Override
    public double findTqwdByTime(String a, String b)
    {
        List<QxxxModel> tqwdByTime = mapper.findTqwdByTime(a, b);
        double[] t1 = new double[tqwdByTime.size()];
        for (int i = 0;i<tqwdByTime.size()-1;i++)
        {
            double v = (tqwdByTime.get(i).getZgwd() + tqwdByTime.get(i).getZdwd())/2;
            t1[i] = v;
        }
        double v = 1;
        for (double d:t1)
        {
            v += d;
        }
        v = v/t1.length;
        return v;
    }

    @Override
    public CrewModel findJzById(String id) {
        return mapper.findJzById(id);
    }
}
