package com.magic.sso.bean;


public class User {

    public static final int USER_EFFECT=1;

    private String id;
    private String userId;
    private String passwordHash;
    private String userYears;
    private String email;
    private String tell;
    private long regTime;
    private int status;

    public User(String userId, String password, String userYear, String tell, String email) {
        this.userId=userId;
        this.passwordHash=password;
        this.userYears=userYear;
        this.tell=tell;
        this.email=email;
        this.regTime=System.currentTimeMillis();
        this.status= USER_EFFECT;
    }

    public User(){
        this.regTime=System.currentTimeMillis();
        this.status= USER_EFFECT;
    }

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
        return passwordHash;
    }

    public void setPasswodHash(String passwodHash) {
        this.passwordHash = passwodHash;
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

    public long getRegTime() {
        return regTime;
    }

    public void setRegTime(long regTime) {
        this.regTime = regTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
