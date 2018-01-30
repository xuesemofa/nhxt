package org.consume.com.util.base64.controller;

import org.consume.com.user.model.UserModel;
import org.consume.com.user.service.UserService;
import org.consume.com.util.date.Dates2;
import org.consume.com.util.excel.CreateSimpleExcelToDisk;
import org.consume.com.util.resultJson.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
public class Base64Controller {

    private final static Logger logger = LoggerFactory.getLogger(Base64Controller.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    public String test() {
        String s = "";
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                if (str.length() == 1) {
                    sb.append("0" + str);
                } else {
                    sb.append(str);
                }
            }
            s = sb.toString().toUpperCase();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return s;
    }

    @GetMapping("/test1")
    public ResponseResult<List<UserModel>> test1() {
        ResponseResult<List<UserModel>> result = new ResponseResult<>();
        List<UserModel> list = new ArrayList<>();
        result.setMessage("成功");
        result.setSuccess(true);
        result.setData(list);
        return result;
    }

    @GetMapping("/test2")
    public void test2(HttpServletResponse res) {
        try {
//            测试用例
            String fileName = CreateSimpleExcelToDisk.strToJs("中文名称",
                    "标题1,标题2",
                    "1,2][,4",
                    "[,3",
                    res);

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
