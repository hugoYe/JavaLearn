package com.system.business.adv.customer.domain;

import com.system.common.domain.BaseDomain;

import javax.persistence.*;

/**
 * 客户表
 */
@Entity
@Table(name = "t_adv_customer")
public class CustomerDomain extends BaseDomain {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * 客户ID
     */
    @Column(name = "cust_id")
    private String custId;

    /**
     * 客户公司名称
     */
    @Column(name = "cust_name")
    private String custName;

    /**
     * 客户对接广告类型
     */
    @Column(name = "cust_type")
    private String custType;

    /**
     * 客户对接人-我司销售人员
     */
    @Column(name = "cust_sales")
    private String custSales;

    /**
     * 客户公司全称
     */
    @Column(name = "cust_company")
    private String custCompany;

    /**
     * 客户联系邮箱-支持多个填写邮箱，中间可以设定由;或者,隔开
     */
    @Column(name = "cust_email")
    private String custEmail;

    /**
     * 客户登录密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 客户回调连接
     */
    @Column(name = "postback_url")
    private String postbackUrl;

    /**
     * 收益比例
     */
    @Column(name = "proportion")
    private Integer proportion;

    /**
     * 被删除的数据: 1.已删除，0.未删除
     */
    @Column(name = "is_deleted")
    private Integer isDeleted = 0;

    /**
     * 用户角色
     */
    @Column(name = "user_role")
    private String userRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getCustSales() {
        return custSales;
    }

    public void setCustSales(String custSales) {
        this.custSales = custSales;
    }

    public String getCustCompany() {
        return custCompany;
    }

    public void setCustCompany(String custCompany) {
        this.custCompany = custCompany;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPostbackUrl() {
        return postbackUrl;
    }

    public void setPostbackUrl(String postbackUrl) {
        this.postbackUrl = postbackUrl;
    }

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
