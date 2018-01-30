package org.consume.com.exchange.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.consume.com.crew.service.CrewService;
import org.consume.com.exchange.service.ExchangeService;
import org.consume.com.heatattribute.service.AttributeService;
import org.consume.com.opc.model.Datas2Model;
import org.consume.com.opc.service.OPC2Service;
import org.consume.com.structure.model.StructureModel;
import org.consume.com.structure.service.StructureService;
import org.consume.com.util.ExportExcelSeedBack.ExportExcelSeedBack;
import org.consume.com.util.excel.CreateSimpleExcelToDisk;
import org.consume.com.util.files.FileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @name 换热站-all
 */
@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    private static String view = "/exchange/";
    @Value("${page.pageSize}")
    private Integer pageSize;

    @Autowired
    private ExchangeService service;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private CrewService crewService;
    @Autowired
    private StructureService structureService;
    @Autowired
    private OPC2Service opc2Service;

    /**
     * init
     *
     * @param pageNow Integer
     * @return ModelAndView Page<HeatModel> /heat/index
     */
    @RequiresPermissions(value = "hrz:gk:jzsjlb")
    @GetMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow) {
        List<StructureModel> page = structureService.findAlls("0", "1");
        return new ModelAndView("/exchange/index")
                .addObject("page", page);
    }

    /**
     * 查看单个机组
     *
     * @param jzname 机组名称
     * @return
     * @throws Exception
     */
//    @RequiresPermissions(value = "exchange:queryOne")
    @GetMapping("/queryOne")
    public ModelAndView queryOne(@RequestParam("jzname") String jzname) throws Exception {
        return new ModelAndView("/exchange/queryOne")
                .addObject("jzname", jzname);
    }

    @PostMapping("/getJson")
    public Map<String, Object> getJson(@RequestParam("jzname") String jzname) throws Exception {
        Map<String, Object> map = new HashMap<>();
//机组
        List<Datas2Model> datas = service.getByNames(jzname);

//        供温回温
        double[] yg_a_data = new double[datas.size()];
        double[] yh_a_data = new double[datas.size()];
        double[] eg_a_data = new double[datas.size()];
        double[] eh_a_data = new double[datas.size()];

//        公鸭回呀
        double[] yg_b_data = new double[datas.size()];
        double[] yh_b_data = new double[datas.size()];
        double[] eg_b_data = new double[datas.size()];
        double[] eh_b_data = new double[datas.size()];

//        时间
        String[] dates_a_data = new String[datas.size()];

        for (int i = 0; i < datas.size(); i++) {

            yg_a_data[i] = Double.valueOf(datas.get(datas.size() - (i + 1)).getT11());
            yh_a_data[i] = Double.valueOf(datas.get(datas.size() - (i + 1)).getT12());
            eg_a_data[i] = Double.valueOf(datas.get(datas.size() - (i + 1)).getT21());
            eh_a_data[i] = Double.valueOf(datas.get(datas.size() - (i + 1)).getT22());

            yg_b_data[i] = Double.valueOf(datas.get(datas.size() - (i + 1)).getP11());
            yh_b_data[i] = Double.valueOf(datas.get(datas.size() - (i + 1)).getP12());
            eg_b_data[i] = Double.valueOf(datas.get(datas.size() - (i + 1)).getP21());
            eh_b_data[i] = Double.valueOf(datas.get(datas.size() - (i + 1)).getP22());

            if (datas.get(datas.size() - (i + 1)).getDates() != null && datas.get(datas.size() - (i + 1)).getDates() != 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
                java.util.Date dt = new Date((datas.get(datas.size() - (i + 1)).getDates() / 1000) * 1000);
                String sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00

                dates_a_data[i] = sDateTime;
            } else {
                dates_a_data[i] = "####-##-## ##:##:##";
            }
        }

        map.put("yg_a_data", yg_a_data);
        map.put("yh_a_data", yh_a_data);
        map.put("eg_a_data", eg_a_data);
        map.put("eh_a_data", eh_a_data);

        map.put("yg_b_data", yg_b_data);
        map.put("yh_b_data", yh_b_data);
        map.put("eg_b_data", eg_b_data);
        map.put("eh_b_data", eh_b_data);

        map.put("dates_a_data", dates_a_data);

        map.put("title_data", new String[]{"一网供温", "一网回温", "二网供温", "二网回温"});
        map.put("title_data2", new String[]{"一网供压", "一网回压", "二网供压", "二网回压"});
        map.put("jzname", jzname);
        return map;
    }

    /**
     * 按分公司查看
     *
     * @param id 分公司id
     * @return
     * @throws Exception
     */
//    @RequiresPermissions(value = "exchange:queryByCompany")
    @GetMapping("/queryByCompany")
    public ModelAndView querys(@RequestParam("id") String id,
                               @RequestParam(value = "zd", required = false) String zd,
                               @RequestParam(value = "px", required = false) String px) {
        StructureModel model = structureService.getById(id);
        List<Datas2Model> models2 = opc2Service.findList(id, zd, px);
        return new ModelAndView("/exchange/query")
                .addObject("list", models2)
                .addObject("model", model);
    }

    @GetMapping("/queryCompanys")
    public ModelAndView queryCompanys(@RequestParam(value = "zd", required = false) String zd,
                                      @RequestParam(value = "px", required = false) String px) {
        List<Datas2Model> models2 = opc2Service.findList2(zd, px);
        return new ModelAndView("/exchange/querys")
                .addObject("list", models2)
                .addObject("zd", zd)
                .addObject("px", px);
    }

    @GetMapping("/queryCompanysToExcel")
    public ModelAndView queryCompanysToExcel(@RequestParam(value = "zd", required = false) String zd,
                                             @RequestParam(value = "px", required = false) String px) {
        List<Datas2Model> models2 = opc2Service.findList2(zd, px);
        String excels;
        try {
            excels = CreateSimpleExcelToDisk.excels(models2);
        } catch (Exception e) {
            return new ModelAndView("/exchange/dow");
        }
        return new ModelAndView("/exchange/dow").addObject("excels", excels);
    }

    @RequestMapping("/queryCompanysToExcel2")
    public String queryCompanysToExcel2(@RequestParam("table") String table,
                                              @RequestParam("na") String na) {
        String[] split = table.split("]");
        List<String[]> s = new ArrayList<>();
        for (String ss : split) {
            String[] split1 = ss.split(",");
            s.add(split1);
        }
        String excels;
        try {
            excels = CreateSimpleExcelToDisk.excels4(s,na);
        } catch (Exception e) {
           return  "";
        }
        return excels;
    }

    @GetMapping("/queryCompanysToExcel3")
    public ModelAndView queryCompanysToExcel3(@RequestParam(value = "fileName", required = false) String fileName) {

        return new ModelAndView("/exchange/dow2").addObject("fileName", fileName);
    }

    @RequestMapping(value = "/Download", method = RequestMethod.GET)
    public void testDownload(HttpServletResponse res, @RequestParam("fileName") String fileName) {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream;charset=UTF-8");
        res.setHeader("Content-Disposition", "attachment;filename=所有分公司数据.xls");
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

    @RequestMapping("/Download2")
    public void testDownload2(HttpServletResponse res, @RequestParam("fileName") String fileName) throws Exception {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream;charset=UTF-8");
        res.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"), "iso8859-1")
        );
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

//    @RequestMapping("/formTable")
//    public String fromTable(@RequestParam("table") String table,
//                            @RequestParam("na") String na) {
//        String[] split = table.split("]");
//        List<Object[]> s = new ArrayList<>();
//        for (String ss : split) {
//            String[] split1 = ss.split(",");
//            s.add(split1);
//        }
//        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
//        //导出文件的标题
//        String title = na + sim.format(new Date()) + "数据.xls";
//        //设置表格标题行，由于页面报表的表头固定不变，所以为了省事后端直接定死
//        String[] headers = new String[]{"序号","机组名称","所属分公司","建筑类型","通讯状态", "一网供水温度", "一网回水温度", "一网供水压力", "一网回水压力",
//                "二网供水温度", "二网回水温度", "二网供水压力", "二网回水压力", "1#循环泵频率反馈", "1#循环泵电流反馈",
//                "2#循环泵频率反馈", "2#循环泵电流反馈", "1#补水泵频率反馈", "采集时间"};
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//        try {
//            //防止中文乱码
//            String headStr = "attachment; filename=\"" + new String(title.getBytes("gb2312"), "ISO8859-1") + "\"";
//            response.setContentType("octets/stream");
//            response.setContentType("APPLICATION/OCTET-STREAM");
//            response.setHeader("Content-Disposition", headStr);
//            //ExportExcel ex = new ExportExcel(title, headers, dataList);//有标题
//            ExportExcelSeedBack ex = new ExportExcelSeedBack(title, headers, s);//没有标题
//            ex.export();
//            return "1";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "2";
//        }
//    }
}
