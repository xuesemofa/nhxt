package org.consume.com.util.file;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.consume.com.crew.model.CrewModel;
import org.consume.com.crew.service.CrewService;
import org.consume.com.heat.model.HeatModel;
import org.consume.com.heat.service.HeatService;
import org.consume.com.hrzmj.model.HrzmjModel;
import org.consume.com.hrzmj.service.HrzmjService;
import org.consume.com.jurisdiction.model.JurisdictionModel;
import org.consume.com.jurisdiction.service.JurisdictionService;
import org.consume.com.jzmj.model.JzmjModel;
import org.consume.com.jzmj.service.JzmjService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private HrzmjService hrzmjService;
    @Autowired
    private HeatService heatService;
    @Autowired
    private CrewService crewService;
    @Autowired
    private JzmjService jzmjService;
    @Autowired
    private JurisdictionService jurisdictionService;

    @PostMapping("/file")
    public String files(@RequestParam("file") MultipartFile file, @RequestParam("lx") Integer lx,
                        HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        try {
//            上传
            FileOutputStream out = new FileOutputStream(fileName);
            out.write(file.getBytes());
            out.flush();
            out.close();
//解析数据
            List<String> strings = JxExcelUtil.exportListFromExcel(lx, fileName);

            if (strings == null || strings.size() <= 0)
                return "没有解析到数据，请检查excel是否存在数据";
//            HrzmjModel model = new HrzmjModel();
            List<Integer> i = new ArrayList<>();
            switch (lx) {
//                换热站面积
                case 1:
                    strings.forEach(k -> {
                        HeatModel heatModel = heatService.getByNames(k.split("#")[0]);
                        if (heatModel != null && heatModel.getUuid() != null && !heatModel.getUuid().equals("")) {
                            HrzmjModel model = hrzmjService.getByJzid(heatModel.getUuid());
                            if (model != null) {
                                model.setHrzmj(Double.valueOf(k.split("#")[1]));
                                hrzmjService.update(model);
                            } else {
                                model = new HrzmjModel();
                                model.setHrzmj(Double.valueOf(k.split("#")[1]));
                                model.setJzid(heatModel.getUuid());
                                hrzmjService.add(model);
                            }
                            i.add(1);
                        }
                    });
                    return "数据导入完成" + i.size();
//                   机组面积
                case 2:
                    strings.forEach(k -> {
                        CrewModel crewModel = crewService.getByNames(k.split("#")[0]);
                        if (crewModel != null && crewModel.getUuid() != null && !crewModel.getUuid().equals("")) {
                            JzmjModel model = jzmjService.getByJzid(crewModel.getUuid());
                            if (model != null) {
                                model.setJzmj(Double.valueOf(k.split("#")[1]));
                                jzmjService.update(model);
                            } else {
                                model = new JzmjModel();
                                model.setJzmj(Double.valueOf(k.split("#")[1]));
                                model.setJzid(crewModel.getUuid());
                                jzmjService.add(model);
                            }
                            i.add(1);
                        }
                    });
                    return "数据导入完成" + i.size();
                //                   机组
                case 3:
                    strings.forEach(k -> {
                        HeatModel heatModel = heatService.getByNames(k.split("#")[0]);
                        if (heatModel != null && heatModel.getUuid() != null && !heatModel.getUuid().equals("")) {
                            CrewModel model = crewService.getByNames(k.split("#")[1]);
                            if (model != null) {
                                model.setParents(heatModel.getUuid());
                                try {
                                    Double aDouble = Double.valueOf(k.split("#")[2]);
                                    model.setMj(aDouble);
                                } catch (Exception e) {
                                    model.setMj(Double.valueOf(0.0));
                                }
                                crewService.update(model);
                            } else {
                                model = new CrewModel();
                                model.setParents(heatModel.getUuid());
                                model.setNames(k.split("#")[1]);
                                try {
                                    Double aDouble = Double.valueOf(k.split("#")[2]);
                                    model.setMj(aDouble);
                                } catch (Exception e) {
                                    model.setMj(Double.valueOf(0.0));
                                }
                                crewService.add(model);
                            }
                            i.add(1);
                        }
                    });
                    return "数据导入完成" + i.size();
//                    权限初始化
                case 4:
                    jurisdictionService.del2();
                    strings.forEach(k -> {
                        JurisdictionModel model = new JurisdictionModel();
                        model.setUuid(GetUuid.getUUID());
                        model.setJuname(k.split("#")[0]);
                        model.setUrl(k.split("#")[1]);
                        model.setParent(k.split("#")[2]);
                        jurisdictionService.add(model);
                        i.add(1);
                    });
                    return "数据导入完成" + i.size();
                default:
                    return "没有找到相关数据";
            }
        } catch (Exception e) {
            // TODO: handle exception
            return e.getMessage();
        }
        //返回json
//        return fileName;
    }

    public static void uploadFile(byte[] file, String fileName) throws Exception {
        FileOutputStream out = new FileOutputStream(fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
