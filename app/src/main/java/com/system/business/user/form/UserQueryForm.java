package com.system.business.user.form;

import com.system.common.form.PageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel
public class UserQueryForm extends PageForm {

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "创建时间")
    private List<String> createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCreateTime() {
        return createTime;
    }

    public void setCreateTime(List<String> createTime) {
        this.createTime = createTime;
    }
}
