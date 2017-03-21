package com.wenjuan.bean;

import com.wenjuan.utils.ConfigUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 用于分页
 */
public class PageBean {
    /**
     * 当前页数
     */
    private int currPageCount;
    /**
     * 总项数
     */
    private int totalCount;
    /**
     * 每页项数
     */
    private int itemPerPage = ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE;
    /**
     * 分页后的项
     */
    private Object[] items;


    /**
     * 不分页
     *
     * @param items
     */
    public PageBean(Object[] items) {
        this.currPageCount = 1;
        this.totalCount = items.length;
        this.itemPerPage = this.totalCount;
        this.items = items;
    }

    /**
     * 不分页
     *
     * @param items
     */
    public PageBean(List<?> items) {
        this(items.toArray());
    }

    /**
     * 分页
     *
     * @param currPageCount 当前页数，每页项数为ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE
     * @param items
     */
    public PageBean(int currPageCount, Object[] items) {
        this(currPageCount, ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE, items);
    }

    /**
     * 分页
     *
     * @param currPageCount 当前页数，每页项数为ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE
     * @param items
     */
    public PageBean(int currPageCount, List<?> items) {
        this(currPageCount, ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE, items);
    }

    /**
     * 分页
     *
     * @param currPageCount 当前页数
     * @param itemPerPage   每页项数
     * @param items
     */
    public PageBean(int currPageCount, int itemPerPage, List<?> items) {
        this.itemPerPage = itemPerPage;
        this.currPageCount = currPageCount;
        this.totalCount = items.size();
        if (this.totalCount == itemPerPage || this.totalCount == 0) {
            this.items = items.toArray();
        } else {
            int totalPageCount = totalCount / itemPerPage + (totalCount % itemPerPage == 0 ? 0 : 1);
            if (currPageCount < 1 || currPageCount > totalPageCount) {
                currPageCount = 1;
            }
            this.items = items.subList((currPageCount - 1) * this.itemPerPage, Math.min(currPageCount * this.itemPerPage, totalCount) - 1).toArray();
        }
    }

    /**
     * 分页
     *
     * @param currPageCount 当前页数
     * @param itemPerPage   每页项数，当此项为items的长度时，不分页
     * @param items
     */
    public PageBean(int currPageCount, int itemPerPage, Object[] items) {
        this.itemPerPage = itemPerPage;
        this.currPageCount = currPageCount;
        this.totalCount = items.length;
        if (itemPerPage == this.totalCount || this.totalCount == 0) {
            this.items = items;
        } else {
            int totalPageCount = totalCount / itemPerPage + (totalCount % itemPerPage == 0 ? 0 : 1);
            if (currPageCount < 1 || currPageCount > totalPageCount) {
                currPageCount = 1;
            }
            this.items = Arrays.copyOfRange(items, (currPageCount - 1) * itemPerPage, Math.min(currPageCount * itemPerPage, totalCount) - 1);
        }
    }

    public int getCurrPageCount() {
        return currPageCount;
    }

    public void setCurrPageCount(int currPageCount) {
        this.currPageCount = currPageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public Object[] getItems() {
        return items;
    }

    public void setItems(Object[] items) {
        this.items = items;
    }
}
