package com.xtang.utils;

import java.util.List;

/**
 * @program: short-video-back
 * @Date: 2019/7/17 22:41
 * @Author: xTang
 * @Description:
 */
public class PagedResult {

    /**
     *当前页数
     */
    private int pageNum;

    /**
     * 总页数
     */
    private int total;

    /**
     * 总记录数
     */
    private long records;

    /**
     * 每页显示的内容
     */
    private List<?> rows;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
