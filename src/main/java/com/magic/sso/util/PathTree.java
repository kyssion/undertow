package com.magic.sso.util;

import com.magic.sso.ssohandle.baseHandle.SSoHttpHandle;

import java.util.HashMap;

/**
 * url 路径解析地址
 */
public class PathTree {
    private String path;
    private HashMap<String, PathTree> nextPathTree;
    private SSoHttpHandle pathEndHandle;

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

    public SSoHttpHandle getPathEndHandle() {
        return pathEndHandle;
    }

    public void setPathEndHandle(SSoHttpHandle pathEndHandle) {
        this.pathEndHandle = pathEndHandle;
    }
}
