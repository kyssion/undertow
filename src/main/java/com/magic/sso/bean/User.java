package com.magic.sso.bean;

import com.magic.sso.util.simpleSql.SqlParams;

public class User implements SqlParams {
    private String id;
    private String userId;
    private String passwodHash;
    private String userYears;
    private String email;
    private String tell;
    private String regTime;
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswodHash() {
        return passwodHash;
    }

    public void setPasswodHash(String passwodHash) {
        this.passwodHash = passwodHash;
    }

    public String getUserYears() {
        return userYears;
    }

    public void setUserYears(String userYears) {
        this.userYears = userYears;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String[] getParams() {
        String[] param = new String[]{
                this.userId,this.passwodHash,this.userYears,this.email,this.tell,this.regTime,String.valueOf(this.status)
        };
        return param;
    }
}
