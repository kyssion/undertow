package com.magic.sso.ssohandle;

import io.undertow.server.handlers.resource.ResourceHandler;

import java.util.concurrent.ConcurrentHashMap;

public class SSoResourceHttpHandle extends SSOPathRoutingHandle{

    private ConcurrentHashMap<String, ResourceHandler> resourceHandler=
            new ConcurrentHashMap<>();

    public SSoResourceHttpHandle(String rootPath,ConcurrentHashMap<String, ResourceHandler> resourceHandler) {
        super(rootPath);
        this.resourceHandler.putAll(resourceHandler);
    }
    public SSoResourceHttpHandle(ConcurrentHashMap<String, ResourceHandler> resourceHandler) {
        super();
        this.resourceHandler = resourceHandler;
        this.resourceHandler.putAll(resourceHandler);
    }

    public SSoResourceHttpHandle(String[] resoucePath) {
        super();
    }

    public SSoResourceHttpHandle(String rootPath,String[] resoucePath) {
        super(rootPath);

    }

}
