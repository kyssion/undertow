package com.magic.sso.ssohandle;

import io.undertow.server.HttpHandler;
import io.undertow.util.HttpString;
import io.undertow.util.Methods;

public abstract class SSoHttpHandle implements HttpHandler {

    private String path="";

    //defind url method
    public HttpString method;

    public SSoHttpHandle(String path,HttpString method){
        this.path=path;
        this.method = method;
    }
    public SSoHttpHandle(String path){
        this(path,Methods.GET);
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

    public HttpString getMethod() {
        return method;
    }

    public void setMethod(HttpString method) {
        this.method = method;
    }
}
