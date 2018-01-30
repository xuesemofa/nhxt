package org.consume.com.user.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.consume.com.user.model.UserModel;

import java.util.List;

/**
 * @name 人员资料
 */
public interface UserMapper {

    String tableName = " user_table ";

    /**
     * add
     *
     * @param model UserModel
     * @return int
     */
    @Insert({
            "insert into " + tableName + " values (#{model.uuid},#{model.account},#{model.passwrod},#{model.username}" +
                    ",#{model.department},#{model.company},#{model.position},#{model.profession},#{model.idNumber},#{model.acctype})"
    })
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
    @Update({
            "update " + tableName + " set username = #{v} where account = #{wv}"
    })
    int puts(@Param("k") String k, @Param("v") String v, @Param("wk") String wk, @Param("wv") String wv);

    /**
     * put
     *
     * @param model UserModel
     * @return int
     */
    @Update({
            "update" + tableName + " values (#{model.account},#{model.passwrod},#{model.username}" +
                    ",#{model.department},#{model.company},#{model.position},#{model.profession},#{model.idNumber})" +
                    " where uuid = #{model.uuid}"
    })
    int put(@Param("model") UserModel model);

    /**
     * put2
     *
     * @param model UserModel
     * @return int
     */
    @Update({
            "update" + tableName + " set account = #{model.account},username = #{model.username}" +
                    ",department = #{model.department},company = #{model.company},position = #{model.position}," +
                    "profession = #{model.profession},idNumber = #{model.idNumber}" +
                    " where uuid = #{model.uuid}"
    })
    int put2(@Param("model") UserModel model);

    /**
     * getById
     *
     * @param id String
     * @return UserModel
     */
    @Select({
            "select * from " + tableName + " where uuid = #{id}"
    })
    UserModel getById(@Param("id") String id);

    @Select({
            "select " +
                    " u.uuid,u.account,u.username,case when u.company = '0' then '总部' ELSE s1.names end as company," +
                    " s2.names department," +
                    " s3.names position," +
                    " u.profession," +
                    " u.`idNumber`" +
                    " from user_table u " +
                    " left join structure_table s1 on s1.`uuid` = u.company" +
                    " left join structure_table s2 on s2.`uuid` = u.department" +
                    " left join structure_table s3 on s3.`uuid` = u.position" +
                    " where u.uuid = #{id}"
    })
    UserModel getById2(@Param("id") String id);

    /**
     * getByAccount
     *
     * @param account String
     * @return UserModel
     */
    @Select({
            "select * from " + tableName + " where account=#{account}"
    })
    UserModel getByAccount(@Param("account") String account);

    @Select({
            "select " +
                    " u.uuid,u.account,u.username,case when u.company = '0' then '总部' ELSE s1.names end as company," +
                    " s2.names department," +
                    " s3.names position," +
                    " u.profession," +
                    " u.`idNumber`" +
                    " from user_table u " +
                    " left join structure_table s1 on s1.`uuid` = u.company" +
                    " left join structure_table s2 on s2.`uuid` = u.department" +
                    " left join structure_table s3 on s3.`uuid` = u.position" +
                    " where acctype = '2'"
    })
    Page<UserModel> findAllPage();

    @Select({
            "select * from " + tableName + " where position = #{id}"
    })
    List<UserModel> getByCD(@Param("id") String id);

    @Update({
            "update user_table set passwrod = #{pwd} where account = #{account}"
    })
    int upPWD(@Param("pwd") String pwd, @Param("account") String account);

    @Delete({
            "delete from user_table where uuid = #{id}"
    })
    int del(@Param("id") String id);

    @Update({
            "update user_table set password = #{pwd} where uuid = #{id}"
    })
    int resetting(@Param("id") String id, @Param("pwd") String pwd);

    /**
     * 每天删除除指定的以外的管理员账户
     *
     * @param account String
     */
    @Delete({
            "delete user_table where account != #{account} and acctype = '1'"
    })
    void delGL(@Param("account") String account);

    /**
     * 查询所有
     *
     * @return UserModel
     */
    @Select({"select * from user_table"})
    List<UserModel> findAll();
}
