package com.system.business.adv.advertiser.domain;

import com.system.common.domain.BaseDomain;

import javax.persistence.*;

/**
 * 广告主表
 */
@Entity
@Table(name = "t_advertiser")
public class AdvertiserDomain extends BaseDomain {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * 广告主ID
     */
    @Column(name = "adv_id")
    private String advId;

    /**
     * 广告主名称
     */
    @Column(name = "adv_name")
    private String advName;

    /**
     * 广告类型
     */
    @Column(name = "adv_type")
    private String advType;

    /**
     * 广告主对接人-我司销售人员
     */
    @Column(name = "adv_sales")
    private String advSales;

    /**
     * 广告主公司全称
     */
    @Column(name = "adv_company")
    private String advCompany;

    /**
     * 广告主联系人
     */
    @Column(name = "adv_contact")
    private String advContact;

    /**
     * 广告主联系方式
     */
    @Column(name = "adv_phone")
    private String advPhone;

    /**
     * 广告主联系邮箱-支持多个填写邮箱，中间可以设定由;或者,隔开
     */
    @Column(name = "adv_email")
    private String advEmail;

    /**
     * 被删除的数据: 1.已删除，0.未删除
     */
    @Column(name = "is_deleted")
    private Integer isDeleted = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdvId() {
        return advId;
    }

    public void setAdvId(String advId) {
        this.advId = advId;
    }

    public String getAdvName() {
        return advName;
    }

    public void setAdvName(String advName) {
        this.advName = advName;
    }

    public String getAdvType() {
        return advType;
    }

    public void setAdvType(String advType) {
        this.advType = advType;
    }

    public String getAdvSales() {
        return advSales;
    }

    public void setAdvSales(String advSales) {
        this.advSales = advSales;
    }

    public String getAdvCompany() {
        return advCompany;
    }

    public void setAdvCompany(String advCompany) {
        this.advCompany = advCompany;
    }

    public String getAdvContact() {
        return advContact;
    }

    public void setAdvContact(String advContact) {
        this.advContact = advContact;
    }

    public String getAdvPhone() {
        return advPhone;
    }

    public void setAdvPhone(String advPhone) {
        this.advPhone = advPhone;
    }

    public String getAdvEmail() {
        return advEmail;
    }

    public void setAdvEmail(String advEmail) {
        this.advEmail = advEmail;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
