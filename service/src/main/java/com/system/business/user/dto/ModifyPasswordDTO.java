package com.system.business.user.dto;

public class ModifyPasswordDTO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 原始密码
     */
    private String prePassword;

    /**
     * 新密码
     */
    private String newPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrePassword() {
        return prePassword;
    }

    public void setPrePassword(String prePassword) {
        this.prePassword = prePassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
