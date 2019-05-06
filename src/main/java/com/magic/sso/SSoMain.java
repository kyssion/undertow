package com.magic.sso;

import com.magic.sso.dao.Item;
import com.magic.sso.dao.TestMapper;
import com.magic.sso.serverHandle.RegisteredHandle;
import com.magic.sso.serverHandle.UserHandle;
import com.magic.sso.ssohandle.SSOPathRoutingHandle;
import com.magic.sso.util.MybatisUtil;
import io.undertow.Undertow;
import org.apache.ibatis.session.SqlSession;

public class SSoMain {
    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        TestMapper item = sqlSession.getMapper(TestMapper.class);
        Item[] items = item.select(new int[]{
                1,2,3
        });
    }
    public static void main2(String[] args) throws Exception {

        UserHandle userHandle = new UserHandle("user");
        RegisteredHandle registeredHandle = new RegisteredHandle("register");

        SSOPathRoutingHandle handle = new SSOPathRoutingHandle();

        handle.addSSoHttpHandle(userHandle);
        handle.addSSoHttpHandle(registeredHandle);

        Undertow server = Undertow.builder().addHttpListener(8888, "localhost")
                .setHandler(handle).build();
        server.start();
    }
}
