package com.magic.sso;

import com.magic.sso.serverHandle.RegisteredHandle;
import com.magic.sso.serverHandle.TestHandle;
import com.magic.sso.serverHandle.UserHandle;
import com.magic.sso.ssohandle.DemoSSoHandle;
import com.magic.sso.ssohandle.SSOPathRoutingHandle;
import io.undertow.Undertow;
import io.undertow.util.Methods;

public class SSoMain {

    public static void main(String[] args) throws Exception {

        //DemoSSoHandle demoSSoHandle = new DemoSSoHandle("/test", Methods.GET);
        UserHandle userHandle = new UserHandle("/user");
        RegisteredHandle registeredHandle = new RegisteredHandle("register");
        SSOPathRoutingHandle handle = new SSOPathRoutingHandle();

        handle.addSSoHttpHandle(userHandle);
        handle.addSSoHttpHandle(registeredHandle);

        Undertow server = Undertow.builder()
                .addHttpListener(8888, "localhost")
                .setHandler(handle).build();
        server.start();
    }
}
