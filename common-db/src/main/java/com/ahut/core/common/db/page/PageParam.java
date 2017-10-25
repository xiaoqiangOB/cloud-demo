package com.ahut.core.common.db.page;

import java.io.Serializable;

/**
 * Created by c2292 on 2017/10/24.
 */
public class PageParam implements Serializable{

    private static final long serialVersionUID = 6822360981407081853L;
    private int pageNum;//当前页数
    private int numPerPage;//每页记录数

    public PageParam(int pageNum, int numPerPage) {
        super();
        this.pageNum = pageNum;
        this.numPerPage = numPerPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }
}
