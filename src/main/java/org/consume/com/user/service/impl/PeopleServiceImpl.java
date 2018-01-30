package org.consume.com.user.service.impl;

import org.consume.com.user.service.UserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 账户管理2
 */
@Component
public class PeopleServiceImpl implements ApplicationListener<ApplicationReadyEvent> {

    @Resource
    private UserService service;

    private static String getLocalMac(InetAddress ia) throws SocketException {
        // TODO Auto-generated method stub
        //获取网卡，获取地址
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            //字节转换为整数
            int temp = mac[i] & 0xff;
            String str = Integer.toHexString(temp);
            if (str.length() == 1) {
                sb.append("0" + str);
            } else {
                sb.append(str);
            }
        }
        return sb.toString().toUpperCase();
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        //得到IP，输出PC-201309011313/122.206.73.83
        try {
            InetAddress ia = InetAddress.getLocalHost();
            String s = getLocalMac(ia);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
