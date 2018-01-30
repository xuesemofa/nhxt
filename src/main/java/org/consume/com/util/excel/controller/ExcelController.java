package org.consume.com.util.excel.controller;

import org.consume.com.util.date.Dates2;
import org.consume.com.util.excel.CreateSimpleExcelToDisk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    private final static Logger logger = LoggerFactory.getLogger(ExcelController.class);

    /**
     * 公共模版的导出excel
     * 样例测试查看 org.consume.com.util.base64.controller.Base64Controller.test2
     *
     * @param name     String 文件名称
     * @param title    String 表格头部信息
     * @param data     String 表格数据
     * @param lastData String 底部平均值，也可以在确保相同格式的情况下传入其它值
     * @param res      HttpServletResponse
     * @throws Exception
     */
    @GetMapping("/publics")
    public void test2(HttpServletResponse res,
                      @RequestParam("name") String name,
                      @RequestParam("title") String title,
                      @RequestParam("data") String data,
                      @RequestParam("lastData") String lastData) {
        try {
            String fileName = CreateSimpleExcelToDisk.strToJs(name, title, data, lastData, res);

            String fileName2 = fileName.substring(0, fileName.lastIndexOf("."))
                    + Dates2.getDateTimeString(new Date())
                    + "." + fileName.substring(fileName.lastIndexOf("."), fileName.length());
//            new String(fileName.getBytes("utf-8"), "iso8859-1")
            res.setHeader("content-type", "application/octet-stream");
            res.setContentType("application/octet-stream;charset=utf-8");
            res.setHeader("Content-Disposition",
                    "attachment;filename="
                            + new String(fileName2.getBytes("utf-8"), "iso8859-1"));
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os;
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
        } catch (Exception e) {
            logger.info("e", e);
        }
    }
}
