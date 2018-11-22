package com.magic.sso;

import com.magic.sso.ssohandle.SSOPathRoutingHandle;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;

public class SSoMain {
    public static void main(String[] args) {


        HttpHandler handle = new SSOPathRoutingHandle();
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(handle).build();
        server.start();
    }
}
