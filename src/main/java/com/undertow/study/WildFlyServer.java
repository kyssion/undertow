package com.undertow.study;

import com.magic.sso.MessageServlet;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletContainer;
import io.undertow.servlet.api.ServletInfo;

public class WildFlyServer {

    public static void main(final String[] args) throws Exception {

        // 创建ServletInfo，名字"MyServlet"必须唯一
        ServletInfo servlet1 = Servlets.servlet("MyServlet", MessageServlet.class);

        // 设置Servlet的init方法执行时需要的数据
        servlet1.addInitParam("mess age", "Hello World");

        // 绑定映射为/hello
        servlet1.addMapping("/hello");

        // 创建名字MessageServlet为的ServletInfo
        ServletInfo servlet2 = Servlets.servlet("MessageServlet", MessageServlet.class);
        servlet2.addInitParam("message", "MyServlet");
        // 绑定映射为/myservlet
        servlet2.addMapping("/qrcode");

        // 创建DeploymentInfo应用布署
        DeploymentInfo deployment = Servlets.deployment();
        // 指定ClassLoader
        deployment.setClassLoader(WildFlyServer.class.getClassLoader());
        // 应用上下文(必须与映射路径一致，否则sessionID会有问题，每次都新建)
        deployment.setContextPath("/myapp");
        // 设置布署包名
        deployment.setDeploymentName("test.war");
        // 添加ServletInfo
        deployment.addServlets(servlet1,servlet2);

        // 创建DeploymentInfo2应用布署
        DeploymentInfo deployment2 = Servlets.deployment();
        deployment2.setClassLoader(WildFlyServer.class.getClassLoader());
        deployment2.setContextPath("/myapp2");
        deployment2.setDeploymentName("test2.war");
        deployment2.addServlets(servlet1,servlet2);

        // 使用默认Servlet容器，并将布署添加至容器
        ServletContainer container = Servlets.defaultContainer();
        // 将布署添加至容器，生成布置对应的管理器
        DeploymentManager manager = container.addDeployment(deployment);
        DeploymentManager manager2 = container.addDeployment(deployment2);
        // 实施布署
        manager.deploy();
        manager2.deploy();

        // 生成路径处理器(作用是dispatch servlet)，默认返回"/*"处理器
        PathHandler path = Handlers.path();
        // 生成路径处理器，返回"/*"自动重定向到"/myapp"的处理器
//        PathHandler path = Handlers.path(Handlers.redirect("/myapp"));

        Handlers.path();
        // 启动容器，生成请求处理器
        HttpHandler myapp = manager.start();
        HttpHandler myapp2 = manager2.start();
        // 绑定映射关系
        path.addPrefixPath("/myapp", myapp);
        path.addPrefixPath("/myapp2", myapp2);

        Undertow server = Undertow.builder()
                // 绑定端口与主机
                .addHttpListener(8080, "localhost")
                // 设置分发处理器PathHandler
                .setHandler(path)
                .build();
        // 启动Server
        server.start();

    }
}
