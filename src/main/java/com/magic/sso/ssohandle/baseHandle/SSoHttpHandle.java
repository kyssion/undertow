package com.magic.sso.ssohandle.baseHandle;

import io.undertow.server.HttpHandler;
import io.undertow.util.HttpString;
import io.undertow.util.Methods;

/**
 * 服务最底层的处理层用来进行过滤的操作
 */
public abstract class SSoHttpHandle implements HttpHandler {

    private String path = "";

    //defind url method
    public HttpString method;

    public SSoHttpHandle(String path, HttpString method) throws Exception {
        if (path.startsWith("/")) {
            throw new Exception("路径匹配不能以/ 开始");
        }
        this.path = path;
        this.method = method;
    }

    public SSoHttpHandle(String path) throws Exception {
        this(path, Methods.GET);
    }

    public boolean inpath(String path) {
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
