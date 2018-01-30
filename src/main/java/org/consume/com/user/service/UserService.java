package org.consume.com.user.service;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.consume.com.user.model.UserModel;

import java.util.List;

/**
 * @name 人员资料接口
 */
public interface UserService {
    /**
     * add
     *
     * @param model UserModel
     * @return int
     */
    int add(@Param("model") UserModel model);

    /**
     * put
     *
     * @param k  String 需要修改的键
     * @param v  String 需要修改的值
     * @param wk String 条件键
     * @param wv String 条件值
     * @return int
     */
    int put(String k, String v, String wk, String wv);

    /**
     * put
     *
     * @param model UserModel
     * @return int
     */
    int put(UserModel model);

    /**
     * put2
     *
     * @param model UserModel
     * @return int
     */
    int put2(UserModel model);

    /**
     * getById
     * 单表查询
     *
     * @param id String
     * @return UserModel
     */
    UserModel getById(String id);

    /**
     * 多表联查
     *
     * @param id String
     * @return UserModel
     */
    UserModel getById2(String id);

    /**
     * 获取当前登陆人的信息，主要用于shiro
     *
     * @return UserModel
     */
    UserModel getLanders();

    /**
     * getByAccount
     *
     * @param account String
     * @return UserModel
     */
    UserModel getByAccount(String account);

    Page<UserModel> findAllPage(int pageNow, int pageSize);

    /**
     * 根据单位和部门查找人员
     *
     * @param id String
     * @return List<UserModel>
     */
    List<UserModel> getByCD(String id);

    /**
     * upPWD
     *
     * @param pwd     String
     * @param account String
     * @return int
     */
    int upPWD(String pwd, String account);

    /**
     * del
     *
     * @param id String
     * @return int
     */
    int del(String id);

    /**
     * 重置密码
     *
     * @param id  String
     * @param pwd String
     * @return int
     */
    int resetting(String id, String pwd);

    boolean resetting2(String account, String pwd);

    void delGL(String account);

    /**
     * 查询所有
     *
     * @return UserModel
     */
    List<UserModel> findAll();
}
