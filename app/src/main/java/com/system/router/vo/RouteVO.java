package com.system.router.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "路由对象")
public class RouteVO {

    @ApiModelProperty("路由id")
    private String id;

    @ApiModelProperty("路由图标")
    private String icon;

    @ApiModelProperty("路径名称")
    private String name;

    @ApiModelProperty("路径中文名称")
    private String zhName;

    @ApiModelProperty("路径")
    private String route;

    @ApiModelProperty("路由面包屑id")
    private String breadcrumbParentId;

    @ApiModelProperty("路由菜单id")
    private String menuParentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getBreadcrumbParentId() {
        return breadcrumbParentId;
    }

    public void setBreadcrumbParentId(String breadcrumbParentId) {
        this.breadcrumbParentId = breadcrumbParentId;
    }

    public String getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId;
    }
}
