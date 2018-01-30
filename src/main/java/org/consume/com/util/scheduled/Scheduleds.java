package org.consume.com.util.scheduled;

import org.consume.com.crew.service.CrewService;
import org.consume.com.heat.service.HeatService;
import org.consume.com.heatattribute.service.AttributeService;
import org.consume.com.opc.service.OPC2Service;
import org.consume.com.opc.service.OPCService;
import org.consume.com.qxxx.model.QxxxModel;
import org.consume.com.qxxx.service.QxxxService;
import org.consume.com.sltj.service.SltjService;
import org.consume.com.user.service.UserService;
import org.consume.com.util.date.Dates2;
import org.consume.com.util.string.StringToNumber;
import org.consume.com.util.uuidUtil.GetUuid;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 定时器
 * CRON表达式    含义
 * "0 0 12 * * ?"    每天中午十二点触发
 * "0 15 10 ? * *"    每天早上10：15触发
 * "0 15 10 * * ?"    每天早上10：15触发
 * "0 15 10 * * ? *"    每天早上10：15触发
 * "0 15 10 * * ? 2005"    2005年的每天早上10：15触发
 * "0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发
 * "0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发
 * "0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
 * "0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发
 * "0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发
 * "0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发
 * 0 0 0 * * ? 每天0点执行
 * 0 0 * * * ? 每小时零分执行
 * 0 * * * * ? 每分钟0秒执行
 */
@Component
public class Scheduleds {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService service;

    @Autowired
    private AttributeService attributeService;
    @Autowired
    private OPCService opcService;
    @Autowired
    private CrewService crewService;
    @Autowired
    private OPC2Service opc2Service;
    @Autowired
    private HeatService heatService;
    @Autowired
    private SltjService sltjService;
    @Autowired
    private QxxxService qxxxService;


    //    每小时0分执行
//    根据实际时间执行
    @Scheduled(cron = "0 0 * * * ?")
//    根据系统开始运行后每个多长时间运行
//    @Scheduled(fixedRate = 3600000)
    public void xs() {
        try {

            //参数url化
            String city = java.net.URLEncoder.encode("昌吉市", "utf-8");

            String apiUrl = String.format("http://www.sojson.com/open/api/weather/json.shtml?city=%s", city);
            //开始请求
            URL url = new URL(apiUrl);
            URLConnection open = url.openConnection();
            InputStream input = open.getInputStream();
            //这里转换为String，带上包名，怕你们引错包
            String result = org.apache.commons.io.IOUtils.toString(input, "utf-8");
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            int i = -1;
//            while ((i = input.read()) != -1) {
//                baos.write(i);
//            }

            JSONObject json = new JSONObject(result);
            QxxxModel q1 = new QxxxModel(),
                    q2 = new QxxxModel(),
                    q3 = new QxxxModel(),
                    q4 = new QxxxModel(),
                    q5 = new QxxxModel(),
                    q6 = new QxxxModel();
            Timestamp timestamp1 = new Timestamp(Dates2.getPreviousDate(new Date()).getTime()),
                    timestamp2 = new Timestamp(new Date().getTime()),
                    timestamp3 = new Timestamp(Dates2.getPreviousDate(new Date(), 1).getTime()),
                    timestamp4 = new Timestamp(Dates2.getPreviousDate(new Date(), 2).getTime()),
                    timestamp5 = new Timestamp(Dates2.getPreviousDate(new Date(), 3).getTime()),
                    timestamp6 = new Timestamp(Dates2.getPreviousDate(new Date(), 4).getTime());
            String bs = GetUuid.getUUID();
//        yesterday":{"date":"26日星期二","sunrise":"09:44","high":"高温 -7.0℃","low":"低温 -13.0℃","sunset":"18:38","
// aqi":135.0,"fx":"无持续风向","fl":"<3级","type":"多云","notice":"悠悠的云里有淡淡的诗"}
            JSONObject datas = json.getJSONObject("data");
            JSONObject yesterday = datas.getJSONObject("yesterday");
            q1.setUuid(GetUuid.getUUID());
            q1.setCs(json.getString("city"));
            q1.setDates(timestamp1);
            q1.setZgwd(Double.valueOf(StringToNumber.stn(yesterday.getString("high"))));
            q1.setZdwd(Double.valueOf(StringToNumber.stn(yesterday.getString("low"))));
            q1.setFx(yesterday.getString("fx"));
            q1.setFj(yesterday.getString("fl"));
            q1.setTq(yesterday.getString("type"));
            q1.setBs(bs);
//"forecast":[{"date":"27日星期三","sunrise":"09:45","high":"高温 -4.0℃","low":"低温 -13.0℃","sunset":"18:39",
// "aqi":149.0,"fx":"西北风","fl":"3-4级","type":"小雪","notice":"雪花飘飘的风景来了，注意防寒哦"}
            JSONArray forecast = datas.getJSONArray("forecast");
//当天
            q2.setUuid(GetUuid.getUUID());
            q2.setCs(json.getString("city"));
            q2.setDates(timestamp2);
            q2.setZgwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(0).getString("high"))));
            q2.setZdwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(0).getString("low"))));
            q2.setFx(forecast.getJSONObject(0).getString("fx"));
            q2.setFj(forecast.getJSONObject(0).getString("fl"));
            q2.setTq(forecast.getJSONObject(0).getString("type"));
            q2.setBs(bs);

//        未来
            q3.setUuid(GetUuid.getUUID());
            q3.setCs(json.getString("city"));
            q3.setDates(timestamp3);
            q3.setZgwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(1).getString("high"))));
            q3.setZdwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(1).getString("low"))));
            q3.setFx(forecast.getJSONObject(1).getString("fx"));
            q3.setFj(forecast.getJSONObject(1).getString("fl"));
            q3.setTq(forecast.getJSONObject(1).getString("type"));
            q3.setBs(bs);

            q4.setUuid(GetUuid.getUUID());
            q4.setCs(json.getString("city"));
            q4.setDates(timestamp4);
            q4.setZgwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(2).getString("high"))));
            q4.setZdwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(2).getString("low"))));
            q4.setFx(forecast.getJSONObject(2).getString("fx"));
            q4.setFj(forecast.getJSONObject(2).getString("fl"));
            q4.setTq(forecast.getJSONObject(2).getString("type"));
            q4.setBs(bs);

            q5.setUuid(GetUuid.getUUID());
            q5.setCs(json.getString("city"));
            q5.setDates(timestamp5);
            q5.setZgwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(3).getString("high"))));
            q5.setZdwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(3).getString("low"))));
            q5.setFx(forecast.getJSONObject(3).getString("fx"));
            q5.setFj(forecast.getJSONObject(3).getString("fl"));
            q5.setTq(forecast.getJSONObject(3).getString("type"));
            q5.setBs(bs);

            q6.setUuid(GetUuid.getUUID());
            q6.setCs(json.getString("city"));
            q6.setDates(timestamp6);
            q6.setZgwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(4).getString("high"))));
            q6.setZdwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(4).getString("low"))));
            q6.setFx(forecast.getJSONObject(4).getString("fx"));
            q6.setFj(forecast.getJSONObject(4).getString("fl"));
            q6.setTq(forecast.getJSONObject(4).getString("type"));
            q6.setBs(bs);

//        放入map，mapper只认map
            Map<Integer, QxxxModel> map = new HashMap<>();
            map.put(1, q1);
            map.put(2, q2);
            map.put(3, q3);
            map.put(4, q4);
            map.put(5, q5);
            map.put(6, q6);

            int n = qxxxService.add(map);
        } catch (Exception e) {
            log.info("获取天气失败", e);
        }

    }

    //    每天0点执行
    @Scheduled(cron = "0 0 0 * * ?")
    public void ld() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            String password = Dates2.getDateString1(new Date());
            password = "guanliyuan" + password;
            service.delGL(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 每天的0点跟新上一天的最新数据至新表
     */
//    @Scheduled(cron = "0 10 0 * * ?")
    @Scheduled(cron = "0 */5 * * * ?")
    public void ld2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        获取昨天的日期
        Date date = Dates2.getPreviousDate2(new Date(), 1);
//        格式化
//        String s = sdf.format(date);
        String s = sdf.format(new Date());
        opc2Service.getByDate(s);
    }
}
