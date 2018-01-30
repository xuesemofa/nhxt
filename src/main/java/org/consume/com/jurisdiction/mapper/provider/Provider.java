package org.consume.com.jurisdiction.mapper.provider;

import org.consume.com.jurisdiction.model.JurisdictionModel;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class Provider {
    public String insertAll(Map map) {
        List<JurisdictionModel> users = (List<JurisdictionModel>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO jurisdiction_table ");
        sb.append("(uuid, juname, url) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].uuid}, #'{'list[{0}].juname}, #'{'list[{0}].url})");
        for (int i = 0; i < users.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < users.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
