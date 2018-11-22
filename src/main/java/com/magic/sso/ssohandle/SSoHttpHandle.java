package com.magic.sso.ssohandle;

import io.undertow.server.HttpHandler;

public abstract class SSoHttpHandle implements HttpHandler {
    private String path="";
    public SSoHttpHandle(String path){
        this.path=path;
    }

    public boolean inpath(String path){
        return this.path.startsWith(path);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
