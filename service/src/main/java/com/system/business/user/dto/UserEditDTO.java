package com.system.business.user.dto;

public class UserEditDTO {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 收益比例
     */
    private Integer incomeRate;

    /**
     * 公司名称
     */
    private String company;

    /**
     * 原始密码
     */
    private String currentPassword;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 确认密码
     */
    private String confirmPassword;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(Integer incomeRate) {
        this.incomeRate = incomeRate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
