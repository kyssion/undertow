package com.magic.sso.ssohandle;

import com.magic.sso.except.BaseExcept;
import com.magic.sso.serverHandle.DefaultHandle;
import com.magic.sso.ssohandle.baseHandle.SSoHttpHandle;
import com.magic.sso.util.PathTree;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.Methods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class SSOPathRoutingHandle implements HttpHandler {

    Logger logger = LoggerFactory.getLogger(SSOPathRoutingHandle.class);

    private PathTree rootPathtree;

    private String rootPath;

    public SSOPathRoutingHandle() {
        this("");
    }

    public SSOPathRoutingHandle(String rootPath) {
        super();
        this.rootPath = rootPath;
        rootPathInit();
    }

    /**
     * 初始化根root
     *
     * @throws Exception
     */
    private void rootPathInit() {
        PathTree pathTree = new PathTree();
        pathTree.setNextPathTree(new HashMap<>());
        try {
            pathTree.setPathEndHandle(new DefaultHandle("", Methods.GET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        pathTree.setNextPathTree(new HashMap<>());
        pathTree.setPath("");
        this.rootPathtree = pathTree;
    }

    /**
     * 添加相关的信息到 httphandle中
     *
     * @param httpHandle
     * @return
     */
    public boolean addSSoHttpHandle(SSoHttpHandle httpHandle) {
        try {
            String[] pathNode = httpHandle.getPath().split("/");
            PathTree itemPath = this.rootPathtree;
            for (String aPathNode : pathNode) {
                if (!itemPath.getNextPathTree().containsKey(aPathNode)) {
                    PathTree pathTree = new PathTree();
                    pathTree.setPath(aPathNode);
                    pathTree.setPathEndHandle(httpHandle);
                    pathTree.setNextPathTree(new HashMap<>());
                    itemPath.getNextPathTree().put(aPathNode, pathTree);
                    itemPath = pathTree;
                } else {
                    itemPath = itemPath.getNextPathTree().get(aPathNode);
                }
            }
        } catch (Exception e) {
            logger.error("添加处理节点失败:error:{}|path:{}|httpHandle:{}", e.getMessage(), httpHandle.getPath(), httpHandle.toString());
            return false;
        }
        return true;
    }

    /**
     * 遍历树查找指定的节点的处理器,使用最长匹配
     *
     * @param node
     * @return
     */
    private SSoHttpHandle findhandle(String[] node) {
        return findhandle(node, 0, this.rootPathtree);
    }

    private SSoHttpHandle findhandle(String[] node, int index, PathTree nowPath) {
        if (index + 1 < node.length && nowPath.getNextPathTree().containsKey(node[index + 1])) {
            return findhandle(node, index + 1, nowPath.getNextPathTree().get(node[index + 1]));
        } else {
            return nowPath.getPathEndHandle();
        }
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json;charset=UTF-8");
        logger.info("请求传入参数:{}", exchange.getRequestURL());
        String[] node = (this.rootPath + exchange.getRequestPath()).split("/");
        SSoHttpHandle httpHandle = this.findhandle(node);
        if (httpHandle == null) {
            exchange.getResponseSender().send("error");
            return;
        }
        if (exchange.getRequestMethod().equals(httpHandle.getMethod())) {
            exchange.dispatch(httpHandle);
        }
    }
}
