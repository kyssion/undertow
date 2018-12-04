package com.magic.sso.util.response;

public class Page {

    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 总条目数
     */
    private int totalItemNum;
    /**
     * 当前页码
     */
    private int currentPageNum;
    /**
     * 每页大小
     */
    private int pageSize;
    /**
     * 每页最大条目
     */
    private final int MAX_PAGE_SIZE = 200;
    /**
     * 总页数
     */
    private int totalPageNum;
    /**
     * 是否有上一页
     */
    private boolean prePage;
    /**
     * 是否有下一页
     */
    private boolean nextPage;

    public static int getDefaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    }

    public int getTotalItemNum() {
        return totalItemNum;
    }

    public void setTotalItemNum(int totalItemNum) {
        this.totalItemNum = totalItemNum;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getMAX_PAGE_SIZE() {
        return MAX_PAGE_SIZE;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public boolean isPrePage() {
        return prePage;
    }

    public void setPrePage(boolean prePage) {
        this.prePage = prePage;
    }

    public boolean isNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }
}
