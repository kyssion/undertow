package com.magic.sso.ssohandle;

import com.magic.sso.serverHandle.DefaultHandle;
import com.magic.sso.ssohandle.baseHandle.SSoHttpHandle;
import com.magic.sso.util.PathTree;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class SSOPathRoutingHandle implements HttpHandler {

    Logger logger = LoggerFactory.getLogger(SSOPathRoutingHandle.class);

    private HashMap<String, PathTree> rootPathtree = new HashMap<>();

    private String rootPath;

    public SSOPathRoutingHandle() {
        this("");
    }

    public SSOPathRoutingHandle(String rootPath) {
        super();
        this.rootPath = rootPath;
    }

    private void rootPathInit() {
        PathTree pathTree = new PathTree();
        pathTree.setNextPathTree(new HashMap<>());
        pathTree.setPathEndHandle(new DefaultHandle());
        pathTree.setNextPathTree(new HashMap<>());
        this.rootPathtree.put("", pathTree);
    }

    public boolean addSSoHttpHandle(SSoHttpHandle httpHandle) {
        try {
            String[] pathNode = httpHandle.getPath().split("/");
            HashMap<String, PathTree> hashMap = this.rootPathtree;
            for (String aPathNode : pathNode) {
                if (!hashMap.containsKey(aPathNode)) {
                    PathTree pathTree = new PathTree();
                    pathTree.setPath(aPathNode);
                    pathTree.setPathEndHandle(httpHandle);
                    pathTree.setNextPathTree(new HashMap<>());
                    hashMap.put(aPathNode, pathTree);
                    hashMap = pathTree.getNextPathTree();
                } else {
                    hashMap = hashMap.get(aPathNode).getNextPathTree();
                }
            }
        } catch (Exception e) {
            logger.error("添加处理节点失败:error:{}|path:{}|httpHandle:{}", e.getMessage(), httpHandle.getPath(), httpHandle.toString());
            return false;
        }
        return true;
    }

    private HttpHandler findhandle(String[] node) {
        return findhandle(node, 0, this.rootPathtree);
    }

    private HttpHandler findhandle(String[] node, int index, HashMap<String, PathTree> nowPathhash) {
        if (index + 1 < node.length && nowPathhash.containsKey(node[index + 1])){
            return findhandle(node,index+1,nowPathhash.get(node[index+1]).getNextPathTree());
        }else{
            return
        }
    }


    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        logger.info("请求传入参数:{}", exchange.getRequestURL());
        System.out.println(this.rootPath + exchange.getRequestPath());
        SSoHttpHandle httpHandle = handleMap.get(exchange.getRequestPath());

        if (httpHandle == null) {
            exchange.getResponseSender().send("error");
            return;
        }

        if (exchange.getRequestMethod().equals(httpHandle.getMethod())) {
            exchange.dispatch(httpHandle);
        }
    }
}
