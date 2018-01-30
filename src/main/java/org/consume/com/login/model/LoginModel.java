package org.consume.com.login.model;

import javax.validation.constraints.Pattern;

public class LoginModel {

    //    @NotBlank(message = "账户不能为空")
//    @Size(message = "密码长度为8-16", min = 8, max = 16)
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$", message = "密码由(a-zA-Z0-9)组成,长度8-20")
    private String username;

    //    @NotBlank(message = "密码不能为空，并且不能包含空格")
//    @Size(message = "密码长度为8-16", min = 8, max = 16)
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$", message = "密码由(a-zA-Z0-9)组成,长度8-20")
    private String password;

    private String yzm;

    public LoginModel() {
        super();
    }

    public LoginModel(String username, String password, String yzm) {
        this.username = username;
        this.password = password;
        this.yzm = yzm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", yzm='" + yzm + '\'' +
                '}';
    }
}
