/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.magic.sso;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletInfo;

import javax.servlet.ServletException;

import static io.undertow.servlet.Servlets.defaultContainer;
import static io.undertow.servlet.Servlets.deployment;
import static io.undertow.servlet.Servlets.servlet;

/**
 * @author Stuart Douglas
 */
public class ServletServer {


    public static final String MYAPP = "/myapp222";

    public static void main(final String[] args) {
        try {
            DeploymentInfo servletBuilder = deployment()
                    .setClassLoader(ServletServer.class.getClassLoader())
                    .setContextPath(MYAPP)
                    .setDeploymentName("test.war")
                    .addServlets(
                            servlet("MessageServlet", MessageServlet.class)
                                    .addInitParam("message", "Hello World")
                                    .addMapping("/my"),
                            servlet("MyServlet", MessageServlet.class)
                                    .addInitParam("message", "MyServlet")
                                    .addMapping("/myservlet"));

            DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
            manager.deploy();

            HttpHandler servletHandler = manager.start();
            PathHandler path = Handlers.path()
                    .addPrefixPath("sdfsdfsdf", servletHandler);
            Undertow server = Undertow.builder()
                    .addHttpListener(8080, "localhost")
                    .setHandler(path)
                    .build();
            server.start();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main2(String[] args) throws ServletException {
        ServletInfo servlets = servlet("testServlet",MessageServlet.class);
        servlets.addInitParam("message","hellow");
        servlets.addMapping("one");
        DeploymentInfo deploymentInfo = deployment();
        deploymentInfo.setClassLoader(ServletServer.class.getClassLoader());
        deploymentInfo.setContextPath("/bbb");
        deploymentInfo.setDeploymentName("test").addServlets(servlets);
        DeploymentManager manager =defaultContainer().addDeployment(deploymentInfo);
        manager.deploy();


        ServletInfo servlets2 = servlet("testServlet",MessageServlet.class);
        servlets2.addInitParam("message","hellow2");
        servlets2.addMapping("one2");
        DeploymentInfo deploymentInfo2 = deployment();
        deploymentInfo2.setClassLoader(ServletServer.class.getClassLoader());
        deploymentInfo2.setContextPath("/aaa");
        deploymentInfo2.setDeploymentName("test2").addServlets(servlets2);
        DeploymentManager manager2 =defaultContainer().addDeployment(deploymentInfo2);
        manager2.deploy();

        HttpHandler servletHandler = manager.start();
        HttpHandler servletHandler2 = manager2.start();
        PathHandler path = Handlers.path()
                .addPrefixPath("myapp", servletHandler)
                .addPrefixPath("myapp2",servletHandler2);


        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(path)
                .build();
        server.start();
    }
}
