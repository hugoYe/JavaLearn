package com.system.common.form;

import com.system.common.constants.WebConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 分页form
 */
@ApiModel
public class PageForm {

    @NotNull(message = "page.page.illegal")
    @Range(min = 1, max = Integer.MAX_VALUE, message = "page.page.illegal")
    @ApiModelProperty(value = "页码", allowableValues = "range[1,infinity]", required = true)
    private Integer page;

    @NotNull(message = "page.pageSize.illegal")
    @Range(min = 1, max = WebConstants.MAX_PAGE_SIZE, message = "page.pageSize.illegal")
    @ApiModelProperty(value = "每页显示记录数", allowableValues = "range[1,infinity]", required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "排序字段", required = false)
    private String order;

    @ApiModelProperty(value = "排序顺序", required = false)
    private String orderDesc;

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
