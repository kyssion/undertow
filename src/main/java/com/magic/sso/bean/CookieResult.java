package com.magic.sso.bean;

import java.util.List;

public class CookieResult {

    private String cookeName;
    private String cookeValue;
    private String[] urlList;

    public String getCookeName() {
        return cookeName;
    }

    public void setCookeName(String cookeName) {
        this.cookeName = cookeName;
    }

    public String getCookeValue() {
        return cookeValue;
    }

    public void setCookeValue(String cookeValue) {
        this.cookeValue = cookeValue;
    }

    public String[] getUrlList() {
        return urlList;
    }

    public void setUrlList(String[] urlList) {
        this.urlList = urlList;
    }
}
