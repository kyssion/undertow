package com.magic.sso.bean;

public class User {

    public static final int USER_EFFECT=1;

    private String id;
    private String userId;
    private String passwordHash;
    private long userYears;
    private String email;
    private String tell;
    private long regTime;
    private int status;

    /**
     * 这三个属性映射 userLogin表
     */
    private long loginTime;
    private int loginStatus;
    private String token;

    public User(String userId, String password, long userYear, String tell, String email) {
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

    public static int getUserEffect() {
        return USER_EFFECT;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public long getUserYears() {
        return userYears;
    }

    public void setUserYears(long userYears) {
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

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
