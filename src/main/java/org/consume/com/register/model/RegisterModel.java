package org.consume.com.register.model;

import java.io.Serializable;

/**
 * 注册记录
 *
 * @table register_table
 */
public class RegisterModel implements Serializable {
    //    id
    private String uuid;
    //    注册账户
    private String account;
    //    注册时间 System.currentTimeMillis();
    private long systimes;

    public RegisterModel() {
        super();
    }

    public RegisterModel(String uuid, String account, long systimes) {
        this.uuid = uuid;
        this.account = account;
        this.systimes = systimes;
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

    public long getSystimes() {
        return systimes;
    }

    public void setSystimes(long systimes) {
        this.systimes = systimes;
    }

    @Override
    public String toString() {
        return "RegisterModel{" +
                "uuid='" + uuid + '\'' +
                ", account='" + account + '\'' +
                ", systimes=" + systimes +
                '}';
    }
}
