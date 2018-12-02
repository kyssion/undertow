package com.magic.sso;

import com.magic.sso.ssohandle.DemoSSoHandle;
import com.magic.sso.ssohandle.SSOPathRoutingHandle;
import io.undertow.Undertow;
import io.undertow.util.Methods;

public class SSoMain {
    public static void main(String[] args) throws Exception {

        DemoSSoHandle demoSSoHandle = new DemoSSoHandle("/test", Methods.GET);


        SSOPathRoutingHandle handle = new SSOPathRoutingHandle();
        handle.addSSoHttpHandle(demoSSoHandle);
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(handle).build();
        server.start();
    }
}