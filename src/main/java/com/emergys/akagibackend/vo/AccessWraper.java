package com.emergys.akagibackend.vo;

import java.io.Serializable;

public class AccessWraper implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String code;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AccessWraper{" +
                "email='" + email + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
