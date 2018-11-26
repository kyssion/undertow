package com.magic.sso.serverHandle;

import com.magic.sso.ssohandle.baseHandle.SSoHttpHandle;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class TockenHandle extends SSoHttpHandle {

    public TockenHandle(String path, HttpString method) throws Exception {
        super(path, method);
    }

    public TockenHandle(String path) throws Exception {
        super(path);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

    }
}
