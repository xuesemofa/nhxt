package org.consume.com.user.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @name 人员资料
 * @table user_table
 */
public class UserModel implements Serializable {
    //    主键
    private String uuid;
    //    账号
//    @NotBlank(message = "账号不能为空", groups = {ADD.class, UPDATE.class})
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$", message = "账户由字母和数字组成,且8-20位", groups = {ADD.class, UPDATE.class})
    private String account;
    //    密码
//    @NotBlank(message = "密码不能为空", groups = {ADD.class})
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$", message = "密码由字母和数字组成,且8-20位", groups = {ADD.class})
    private String passwrod;
    //    姓名
//    @NotBlank(message = "姓名不能为空", groups = {ADD.class, UPDATE.class})
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,6}$", message = "姓名为汉子，长度位2-6位", groups = {ADD.class, UPDATE.class})
    private String username;
    //    部门
    @NotBlank(message = "部门不能为空", groups = {ADD.class, UPDATE.class})
    private String department;
    //    单位
    @NotBlank(message = "单位不能为空", groups = {ADD.class, UPDATE.class})
    private String company;
    //    职位
    @NotBlank(message = "职位不能为空", groups = {ADD.class, UPDATE.class})
    private String position;
    //    工种
    @NotBlank(message = "工种不能为空", groups = {ADD.class, UPDATE.class})
    private String profession;
    //    身份证号
//    @NotBlank(message = "身份证号不能为空", groups = {ADD.class, UPDATE.class})
//    身份证号18位验证
//    ^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$
//    15位
//    ^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$
//    总
    @Pattern(regexp = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)",
            message = "身份证号错误", groups = {ADD.class, UPDATE.class})
    private String idNumber;

    //    账号类型
//    0:超级管理员 1：管理员 2:普通账户
    private int acctype = 2;

    public UserModel() {
        super();
    }

    public UserModel(String uuid, String account, String passwrod, String username, String department, String company, String position, String profession, String idNumber, int acctype) {
        this.uuid = uuid;
        this.account = account;
        this.passwrod = passwrod;
        this.username = username;
        this.department = department;
        this.company = company;
        this.position = position;
        this.profession = profession;
        this.idNumber = idNumber;
        this.acctype = acctype;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getAcctype() {
        return acctype;
    }

    public void setAcctype(int acctype) {
        this.acctype = acctype;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "uuid='" + uuid + '\'' +
                ", account='" + account + '\'' +
                ", passwrod='" + passwrod + '\'' +
                ", username='" + username + '\'' +
                ", department='" + department + '\'' +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", profession='" + profession + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", acctype=" + acctype +
                '}';
    }
}
