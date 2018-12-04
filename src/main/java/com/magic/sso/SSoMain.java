package com.magic.sso;

import com.magic.sso.serverHandle.TestHandle;
import com.magic.sso.serverHandle.UserHandle;
import com.magic.sso.ssohandle.DemoSSoHandle;
import com.magic.sso.ssohandle.SSOPathRoutingHandle;
import io.undertow.Undertow;
import io.undertow.util.Methods;

public class SSoMain {

    public static void main(String[] args) throws Exception {

        DemoSSoHandle demoSSoHandle = new DemoSSoHandle("/test", Methods.GET);
        UserHandle userHandle = new UserHandle("/user", Methods.GET);

        SSOPathRoutingHandle handle = new SSOPathRoutingHandle();


        handle.addSSoHttpHandle(new TestHandle("sdf/fff/ddd","one"));
        handle.addSSoHttpHandle(new TestHandle("sdf/fff/sdf/ddd","two"));

        Undertow server = Undertow.builder()
                .addHttpListener(8888, "localhost")
                .setHandler(handle).build();
        server.start();
    }
}
