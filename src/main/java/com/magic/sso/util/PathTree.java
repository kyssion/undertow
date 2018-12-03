package com.magic.sso.util;

import io.undertow.server.HttpHandler;

import java.util.HashMap;

public class PathTree {
    private String path;
    private HashMap<String,PathTree> nextPathTree;
    private HttpHandler pathEndHandle;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HashMap<String, PathTree> getNextPathTree() {
        return nextPathTree;
    }

    public void setNextPathTree(HashMap<String, PathTree> nextPathTree) {
        this.nextPathTree = nextPathTree;
    }

    public HttpHandler getPathEndHandle() {
        return pathEndHandle;
    }

    public void setPathEndHandle(HttpHandler pathEndHandle) {
        this.pathEndHandle = pathEndHandle;
    }
}
