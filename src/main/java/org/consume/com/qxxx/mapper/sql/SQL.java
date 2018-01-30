package org.consume.com.qxxx.mapper.sql;

import org.consume.com.qxxx.model.QxxxModel;

import java.util.Map;
import java.util.StringJoiner;

public class SQL {
    public String inserts(Map<Integer, QxxxModel> map) {
        StringJoiner sj = new StringJoiner("");
        map.forEach((k, v) -> {
            sj.add("insert into qxxx_tq_table set " +
                    "uuid = '" + v.getUuid() + "'," +
                    "sf='" + v.getSf() + "'," +
                    "cs='" + v.getCs() + "'," +
                    "qy='" + v.getQy() + "'," +
                    "dates = '" + v.getDates() + "'," +
                    "zgwd=" + v.getZgwd() + "," +
                    "zdwd=" + v.getZdwd() + "," +
                    "zgsd=" + v.getZgsd() + "," +
                    "zdsd=" + v.getZdsd() + "," +
                    "zgyl=" + v.getZgyl() + "," +
                    "zdyl=" + v.getZdyl() + "," +
                    "fx='" + v.getFx() + "'," +
                    "fj='" + v.getFj() + "'," +
                    "tq='" + v.getTq() + "',"+
                    "bs='" + v.getBs() + "'"
            );
            if (map.size() > 1)
                sj.add(";");
        });
        return sj.toString();
    }
}
