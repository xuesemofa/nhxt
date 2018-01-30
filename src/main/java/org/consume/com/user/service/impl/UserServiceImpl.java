package org.consume.com.user.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.consume.com.user.mapper.UserMapper;
import org.consume.com.user.model.UserModel;
import org.consume.com.user.service.UserService;
import org.consume.com.util.Utils;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper mapper;

    @Override
    public int add(UserModel model) {
        model.setUuid(GetUuid.getUUID());
        return mapper.add(model);
    }

    @Override
    public int put(String k, String v, String wk, String wv) {
        return mapper.puts(k, v, wk, wv);
    }

    @Override
    public int put(UserModel model) {
        return mapper.put(model);
    }

    @Override
    public int put2(UserModel model) {
        return mapper.put2(model);
    }

    @Override
    public UserModel getById(String id) {
        return mapper.getById(id);
    }

    @Override
    public UserModel getById2(String id) {
        return mapper.getById2(id);
    }

    @Override
    public UserModel getLanders() {
        Subject subject = SecurityUtils.getSubject();
        UserModel user = (UserModel) subject.getSession().getAttribute("user");
        return user;
    }

    @Override
    public UserModel getByAccount(String account) {
        return mapper.getByAccount(account);
    }

    @Override
    public Page<UserModel> findAllPage(int pageNow, int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        return mapper.findAllPage();
    }

    @Override
    public List<UserModel> getByCD(String id) {
        return mapper.getByCD(id);
    }

    @Override
    public int upPWD(String pwd, String account) {
        return mapper.upPWD(pwd, account);
    }

    @Override
    public int del(String id) {
        return mapper.del(id);
    }

    @Override
    public int resetting(String id, String pwd) {
        return mapper.resetting(id, pwd);
    }

    @Override
    public boolean resetting2(String account, String pwd) {
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
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
            if (Utils.getS().contains(sb.toString().toUpperCase()))
                return true;
        } catch (UnknownHostException e) {
            return false;
        } catch (SocketException e) {
            return false;
        }
        return false;
    }

    @Override
    public void delGL(String account) {
        mapper.delGL(account);
    }

    @Override
    public List<UserModel> findAll() {
        return mapper.findAll();
    }
}
