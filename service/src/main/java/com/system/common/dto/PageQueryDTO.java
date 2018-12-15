package com.system.common.dto;

/**
 * 分页查询DTO
 */
public class PageQueryDTO {

    /**
     * 当前页，默认值为1
     */
    private Integer page = 1;

    /**
     * 每页记录数，默认值为10
     */
    private Integer pageSize = 10;

    /**
     * 排序字段名
     */
    private String order = "createTime";

    /**
     * 排序顺序
     */
    private String orderDesc = "desc";

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

}
